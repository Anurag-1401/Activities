import { useState } from "react";

export default function ShowHide() {
  const [visible, setVisible] = useState(false);
  return <section>
    <h2>Show / Hide Password</h2>
    <input type={visible ? "text" : "password"} placeholder="Enter password" />
    <button onClick={() => setVisible(v => !v)}>{visible ? "Hide" : "Show"}</button>
  </section>;
}