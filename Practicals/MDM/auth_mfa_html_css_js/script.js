const MAX_FAILED_ATTEMPTS = 5;
const LOCKOUT_MS = 10 * 60 * 1000;
const OTP_VALID_MS = 60 * 1000;

let otpTimer = null;
let pendingUsername = null;
let currentOtp = null;
let otpExpiresAt = null;

const $ = (id) => document.getElementById(id);

function getUsers() {
  return JSON.parse(localStorage.getItem("authUsers") || "{}");
}

function saveUsers(users) {
  localStorage.setItem("authUsers", JSON.stringify(users));
}

async function hashPassword(password) {
  const data = new TextEncoder().encode(password);
  const hashBuffer = await crypto.subtle.digest("SHA-256", data);
  return [...new Uint8Array(hashBuffer)]
    .map(b => b.toString(16).padStart(2, "0"))
    .join("");
}

function showMessage(text, type = "success") {
  const box = $("message");
  box.textContent = text;
  box.className = `message ${type}`;
}

function clearMessage() {
  $("message").className = "message hidden";
  $("message").textContent = "";
}

function showView(viewId) {
  document.querySelectorAll(".view").forEach(v => v.classList.add("hidden"));
  $(viewId).classList.remove("hidden");
  clearMessage();
  window.scrollTo({ top: 0, behavior: "smooth" });
}

function passwordRules(password) {
  return {
    length: password.length >= 8,
    upper: /[A-Z]/.test(password),
    lower: /[a-z]/.test(password),
    digit: /\d/.test(password),
    special: /[^A-Za-z0-9]/.test(password)
  };
}

function updatePolicyUI(password) {
  const rules = passwordRules(password);

  $("ruleLength").classList.toggle("valid", rules.length);
  $("ruleUpper").classList.toggle("valid", rules.upper);
  $("ruleLower").classList.toggle("valid", rules.lower);
  $("ruleDigit").classList.toggle("valid", rules.digit);
  $("ruleSpecial").classList.toggle("valid", rules.special);
}

function isStrongPassword(password) {
  const r = passwordRules(password);
  return Object.values(r).every(Boolean);
}

function generateOtp() {
  currentOtp = Math.floor(100000 + Math.random() * 900000).toString();
  otpExpiresAt = Date.now() + OTP_VALID_MS;

  $("demoOtp").textContent = currentOtp;
  startOtpTimer();
}

function startOtpTimer() {
  clearInterval(otpTimer);

  const tick = () => {
    const remaining = Math.max(0, otpExpiresAt - Date.now());

    if (remaining === 0) {
      $("otpExpiry").textContent = "OTP expired. Generate a new OTP.";
      clearInterval(otpTimer);
      return;
    }

    const seconds = Math.ceil(remaining / 1000);
    $("otpExpiry").textContent = `OTP expires in ${seconds} second(s)`;
  };

  tick();
  otpTimer = setInterval(tick, 500);
}

function getLockRemaining(user) {
  if (!user.lockedUntil) return 0;
  return Math.max(0, user.lockedUntil - Date.now());
}

function clearExpiredLock(user) {
  if (user.lockedUntil && Date.now() >= user.lockedUntil) {
    user.lockedUntil = 0;
    user.failedAttempts = 0;
    return true;
  }
  return false;
}

/* Navigation */
document.querySelectorAll("[data-view]").forEach(button => {
  button.addEventListener("click", () => showView(button.dataset.view));
});

/* Registration */
$("registerPassword").addEventListener("input", (e) => {
  updatePolicyUI(e.target.value);
});

$("registerForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const username = $("registerUsername").value.trim();
  const password = $("registerPassword").value;

  if (!username) {
    showMessage("Username is required.", "error");
    return;
  }

  if (!isStrongPassword(password)) {
    showMessage("Password does not satisfy the password policy.", "error");
    return;
  }

  const users = getUsers();

  if (users[username]) {
    showMessage("Username already exists. Please choose another username.", "error");
    return;
  }

  const passwordHash = await hashPassword(password);

  users[username] = {
    passwordHash,
    failedAttempts: 0,
    lockedUntil: 0
  };

  saveUsers(users);

  $("registerForm").reset();
  updatePolicyUI("");

  showView("loginView");
  showMessage("Registration successful. You can now log in.", "success");
});

/* Login */
$("loginForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const username = $("loginUsername").value.trim();
  const password = $("loginPassword").value;

  const users = getUsers();
  const user = users[username];

  if (!user) {
    showMessage("Invalid username or password.", "error");
    return;
  }

  if (clearExpiredLock(user)) {
    saveUsers(users);
  }

  const remainingLock = getLockRemaining(user);

  if (remainingLock > 0) {
    const minutes = Math.ceil(remainingLock / 60000);
    showMessage(`Account is locked. Try again in about ${minutes} minute(s).`, "error");
    return;
  }

  const enteredHash = await hashPassword(password);

  if (enteredHash !== user.passwordHash) {
    user.failedAttempts += 1;

    if (user.failedAttempts >= MAX_FAILED_ATTEMPTS) {
      user.lockedUntil = Date.now() + LOCKOUT_MS;
      saveUsers(users);

      showMessage(
        "Too many failed attempts. Account locked for 10 minutes.",
        "error"
      );
      return;
    }

    saveUsers(users);

    const remainingAttempts = MAX_FAILED_ATTEMPTS - user.failedAttempts;
    showMessage(
      `Invalid username or password. ${remainingAttempts} attempt(s) remaining.`,
      "error"
    );
    return;
  }

  // Password is correct: reset failures and start MFA.
  user.failedAttempts = 0;
  user.lockedUntil = 0;
  saveUsers(users);

  pendingUsername = username;
  generateOtp();
  $("otpInput").value = "";
  showView("otpView");
  showMessage("Password verified. Complete MFA using the OTP.", "success");
});

/* OTP / MFA */
$("otpForm").addEventListener("submit", (e) => {
  e.preventDefault();

  if (!pendingUsername || !currentOtp) {
    showMessage("No active MFA challenge.", "error");
    return;
  }

  if (Date.now() > otpExpiresAt) {
    showMessage("OTP has expired. Generate a new OTP.", "error");
    return;
  }

  const enteredOtp = $("otpInput").value.trim();

  if (enteredOtp !== currentOtp) {
    showMessage("Invalid OTP. MFA verification failed.", "error");
    return;
  }

  sessionStorage.setItem("authenticatedUser", pendingUsername);
  sessionStorage.setItem("mfaVerified", "true");

  $("dashboardUser").textContent = pendingUsername;
  clearInterval(otpTimer);
  currentOtp = null;

  showView("dashboardView");
  showMessage("MFA successful. Protected dashboard unlocked.", "success");
});

$("resendOtpBtn").addEventListener("click", () => {
  if (!pendingUsername) return;
  generateOtp();
  $("otpInput").value = "";
  showMessage("A new OTP has been generated.", "success");
});

/* Logout */
$("logoutBtn").addEventListener("click", () => {
  sessionStorage.removeItem("authenticatedUser");
  sessionStorage.removeItem("mfaVerified");

  pendingUsername = null;
  currentOtp = null;
  clearInterval(otpTimer);

  $("loginForm").reset();
  showView("loginView");
  showMessage("Logout successful. Session cleared.", "success");
});

/* Protect dashboard on page reload */
window.addEventListener("DOMContentLoaded", () => {
  const user = sessionStorage.getItem("authenticatedUser");
  const mfa = sessionStorage.getItem("mfaVerified");

  if (user && mfa === "true") {
    $("dashboardUser").textContent = user;
    showView("dashboardView");
  } else {
    showView("homeView");
  }
});
