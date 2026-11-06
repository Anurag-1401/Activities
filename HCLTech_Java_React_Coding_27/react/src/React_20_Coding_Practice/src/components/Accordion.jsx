import { useState } from "react";

const q = [["What is React?", "A JavaScript library for user interfaces."], ["What is JSX?", "HTML-like syntax used in JavaScript."], ["What is useState?", "A Hook for component state."]];

export default function Accordion() { 
    const [open, S] = useState(-1); 
    return <section className="exercise">
        <h2>5. Accordion</h2>
        {
        q.map(
            ([title, body], i) =>
                 <div key={title}>
                    <button onClick={() => S(open === i ? -1 : i)}>
                        {title} {open === i ? "−" : "+"}
                        </button>
                        {open === i && <p>{body}</p>}
                        </div>)
                        }
                        </section> 
        }