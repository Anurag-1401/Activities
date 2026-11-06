import { useState } from "react";

export default function RemoveDuplicates() {
  const [items, setItems] = useState(["Java", "React", "Java", "SQL", "React"]);
  return <section>
    <h2>Remove Duplicates</h2>
    <ul>{items.map((item, i) => <li key={`${item}-${i}`}>{item}</li>)}</ul>
    <button onClick={() => setItems(current => [...new Set(current)])}>
      Remove duplicates
    </button>
  </section>;
}