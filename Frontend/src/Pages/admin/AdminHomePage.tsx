import { SetStateAction, useState } from 'react';
import { FaHome, FaPaw } from 'react-icons/fa';
import './AdminHomePage.css';
import NavBarAdmin from '../../Compnents/admin/NavbarAdmin.tsx';
import AddShelterForm from '../../Compnents/admin/AddShelterForm.tsx';
import SheltersList from '../../Compnents/admin/ShelterList.tsx';

// Define a type for the valid section keys
type Section = 'shelters' | 'addShelter';

// Define a type for the sections data with the above keys
type SectionsData = {
  [key in Section]: JSX.Element;
};

// Define your sectionsData with the corresponding type
const sectionsData: SectionsData = {
  shelters: <SheltersList/>,
  addShelter: <AddShelterForm />,
};

const HomePage = () => {
  // Use the Section type for the activeSection state
  const [activeSection, setActiveSection] = useState<Section>('shelters');
  const [searchBy, setSearchBy] = useState('shelter name'); // Default search by Shelter
  const handleSearchByChange = (event: { target: { value: SetStateAction<string>; }; }) => {
    setSearchBy(event.target.value);
  };

  const placeholderText = `Search by ${searchBy}...`;

  return (
    <div className="admin-homepage">
      <NavBarAdmin />

      <div className="amdin-search-bar">
        <input type="text" placeholder={placeholderText} />
        <select value={searchBy} onChange={handleSearchByChange}>
          <option value="shelter name">Shelter name</option>
          <option value="helter address">Shelter address</option>
          {/* Add more options as needed */}
        </select>
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
          {/* TypeScript now understands that activeSection can only be a key from sectionsData */}
          {sectionsData[activeSection]}
        </section>
      </div>
    </div>
  );
};

export default HomePage;