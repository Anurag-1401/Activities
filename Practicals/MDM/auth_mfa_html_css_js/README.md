# Authentication + MFA Front-End Project

## Files

- index.html
- style.css
- script.js

## Features

1. Basic web application
2. Password policy
3. Password hashing using Web Crypto API (SHA-256 demonstration)
4. Login password verification
5. Failed-attempt counter
6. Account lock after 5 failures for 10 minutes
7. 6-digit OTP
8. OTP expiry after 60 seconds
9. MFA-protected dashboard
10. Logout and session clearing

## Run

No server is required for the demo.

Just open `index.html` in a modern browser.

Recommended:
- Chrome
- Edge
- Firefox

## Practical demonstration

1. Open `index.html`.
2. Click Create Account.
3. Register using a password such as:
   `Krishna@123`
4. Go to Login.
5. Enter correct credentials.
6. The MFA screen displays a demo OTP.
7. Enter the OTP.
8. Protected Dashboard appears.
9. Click Logout.
10. Try the wrong password five times to demonstrate account lockout.

## Important security note

This is a front-end educational demonstration only. It does NOT provide production-grade authentication because all application state is stored in the browser. Real applications must perform password hashing and verification on a trusted backend, use a proper database, secure cookies, HTTPS, CSRF protection, rate limiting, secure MFA/passkeys, and server-side authorization.
