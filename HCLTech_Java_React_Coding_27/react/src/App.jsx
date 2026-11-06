import Counter from "./Counter";
import ShowHide from "./ShowHide";
import RemoveDuplicates from "./RemoveDuplicates";
import ComponentShowcase from "./ComponentShowcase";
import SearchableList from "./SearchableList";

export default function App() {
  return <main>
    <h1>HCLTech React Coding Practice</h1>
    <Counter /><hr />
    <ShowHide /><hr />
    <RemoveDuplicates /><hr />
    <ComponentShowcase /><hr />
    <SearchableList />
  </main>;
}