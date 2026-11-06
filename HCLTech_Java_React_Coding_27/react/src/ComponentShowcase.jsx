function ProfileCard({ name, role, skills }) {
  return <article className="profile-card">
    <h3>{name}</h3><p>{role}</p>
    <ul>{skills.map(skill => <li key={skill}>{skill}</li>)}</ul>
  </article>;
}

export default function ComponentShowcase() {
  const people = [
    { name: "Asha", role: "Frontend Developer", skills: ["React", "CSS"] },
    { name: "Ravi", role: "Java Developer", skills: ["Java", "Spring Boot"] },
    { name: "Neha", role: "Database Developer", skills: ["SQL", "PostgreSQL"] }
  ];
  return <section>
    <h2>Component Showcase</h2>
    <div className="card-grid">
      {people.map(person => <ProfileCard key={person.name} {...person} />)}
    </div>
  </section>;
}