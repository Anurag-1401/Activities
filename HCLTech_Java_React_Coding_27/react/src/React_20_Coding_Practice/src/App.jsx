import Accordion from "./components/Accordion";
import ChangeColorButton from "./components/ChangeColorButton";
import CharacterCounter from "./components/CharacterCounter";
import DebouncedSearch from "./components/DebouncedSearch";
import DigitalClock from "./components/DigitalClock";
import DropdownMenu from "./components/DropdownMenu";
import FetchUsers from "./components/FetchUsers";
import Modal from "./components/Modal";
import MultiStepForm from "./components/MultiStepForm";
import Pagination from "./components/Pagination";
import ParentChild from "./components/ParentChild";
import PasswordStrength from "./components/PasswordStrength";
import ProductFilter from "./components/ProductFilter";
import RandomNumber from "./components/RandomNumber";
import RegistrationForm from "./components/RegistrationForm";
import ShoppingCart from "./components/ShoppingCart";
import SortableTable from "./components/SortableTable";
import Tabs from "./components/Tabs";
import TodoApp from "./components/TodoApp";
import ToggleSwitch from "./components/ToggleSwitch";
export default function App() {
  return <main className="container">
    <h1>React Coding Practice — 20 Exercises</h1>
    <p>Beginner and intermediate examples.</p>
      <Accordion />
      <ChangeColorButton />
      <CharacterCounter />
      <DebouncedSearch />
      <DigitalClock />
      <DropdownMenu />
      <FetchUsers />
      <Modal />
      <MultiStepForm />
      <Pagination />
      <ParentChild />
      <PasswordStrength />
      <ProductFilter />
      <RandomNumber />
      <RegistrationForm />
      <ShoppingCart />
      <SortableTable />
      <Tabs />
      <TodoApp />
      <ToggleSwitch />
  </main>;
}
