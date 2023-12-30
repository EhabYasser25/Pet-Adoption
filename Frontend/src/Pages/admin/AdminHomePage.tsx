import { SetStateAction, useState } from 'react';
import { FaHome, FaPaw } from 'react-icons/fa';
import './AdminHomePage.css';
import SheltersList from "../../Components/admin/shelters/ShelterList";
import AddShelterForm from "../../Components/admin/shelters/AddShelterForm";
import NavBarAdmin from "../../Components/admin/shelters/NavbarAdmin";
import StaffList from '../../Components/admin/staff/StaffList';


const AdminHomePage = () => {
  // Use the Section type for the activeSection state
  const [activeSection, setActiveSection] = useState<Section>('shelters');
  const [searchBy, setSearchBy] = useState('shelter name'); // Default search by Shelter
  const [searchQuery, setSearchQuery] = useState('');
  const [inputValue, setInputValue] = useState(''); // State to hold the input value
  const [selectedShelterId, setSelectedShelterId] = useState<string | null>(null);
  
  // Define a type for the valid section keys
  type Section = 'shelters' | 'addShelter' | 'viewStaff';

  // Define a function to return the sections with props
  const getSectionComponent = (section: Section, searchQuery: string, searchBy: string) => {
    switch (section) {
      case 'shelters':
        return <SheltersList 
        searchQuery={searchQuery} 
        searchBy={searchBy} 
        onViewStaff={handleViewStaff}
        />;
      case 'addShelter':
        return <AddShelterForm />;
      case 'viewStaff':
        console.log(selectedShelterId)
        return selectedShelterId ? <StaffList shelterId={selectedShelterId} /> : null;
      default:
        return null;
    }
  };

  const handleViewStaff = (shelterId: string) => {
    setSelectedShelterId(shelterId);
    setActiveSection('viewStaff');
  };

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
