import { SetStateAction, useState } from 'react';
import { FaHome, FaPaw } from 'react-icons/fa';
import './AdminHomePage.css';
import SheltersList from "../../Components/admin/ShelterList";
import AddShelterForm from "../../Components/admin/AddShelterForm";
import NavBarAdmin from "../../Components/admin/NavbarAdmin";

// Define a type for the valid section keys
type Section = 'shelters' | 'addShelter';

// Define a function to return the sections with props
const getSectionComponent = (section: Section, searchQuery: string, searchBy: string) => {
  switch (section) {
    case 'shelters':
      return <SheltersList searchQuery={searchQuery} searchBy={searchBy} />;
    case 'addShelter':
      return <AddShelterForm />;
    default:
      return null;
  }
};

const AdminHomePage = () => {
  // Use the Section type for the activeSection state
  const [activeSection, setActiveSection] = useState<Section>('shelters');
  const [searchBy, setSearchBy] = useState('shelter name'); // Default search by Shelter
  const [searchQuery, setSearchQuery] = useState('');
  const [inputValue, setInputValue] = useState(''); // State to hold the input value

  const handleSearchByChange = (event: { target: { value: SetStateAction<string>; }; }) => {
    setSearchBy(event.target.value);
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(event.target.value);
  };

  const handleSearch = () => {
    setSearchQuery(inputValue);
  };

  const placeholderText = `Search by ${searchBy}...`;

  return (
    <div className="admin-homepage">
      <NavBarAdmin />

      <div className="amdin-search-bar">
        <input
          type="text"
          placeholder={placeholderText}
          value={inputValue}
          onChange={handleInputChange}
        />
        <select value={searchBy} onChange={handleSearchByChange} className="admin-search-dropdown-list">
          <option value="shelter name">Shelter name</option>
          <option value="shelter country">Shelter country</option>
          <option value="shelter city">Shelter city</option>
        </select>
        <button onClick={handleSearch}>Search</button>
      </div>

      <div className="admin-main-content">
        <aside className="amdin-sidebar">
          <ul>
            <li onClick={() => setActiveSection('shelters')}>
              <FaHome className="admin-icon" /> Shelters
            </li>
            <li onClick={() => setActiveSection('addShelter')}>
              <FaPaw className="admin-icon" /> Add Shelter
            </li>
          </ul>
        </aside>

        <section className="admin-content">
          {getSectionComponent(activeSection, searchQuery, searchBy)}
        </section>
      </div>
    </div>
  );
};

export default AdminHomePage;
