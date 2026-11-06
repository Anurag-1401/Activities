import { useMemo, useState } from "react";

export default function SearchableList() {
  const [query, setQuery] = useState("");
  const [items] = useState(["Java", "JavaScript", "React", "Spring Boot", "SQL", "Python"]);
  const filtered = useMemo(
    () => items.filter(item => item.toLowerCase().includes(query.trim().toLowerCase())),
    [items, query]
  );
  return <section>
    <h2>Searchable List</h2>
    <input value={query} onChange={e => setQuery(e.target.value)}
      placeholder="Search technology..." />
    {filtered.length
      ? <ul>{filtered.map(item => <li key={item}>{item}</li>)}</ul>
      : <p>No matching items found.</p>}
  </section>;
}