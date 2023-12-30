import { IoIosPeople } from 'react-icons/io';
import { FaPen } from 'react-icons/fa';
import { FaTrash } from "react-icons/fa";
import './AdminShelter.css';
import { useState } from 'react';
import EditShelterModal from './EditShelterModal';
import React from 'react';
import { httpRequest } from '../../Controller/HttpProxy';

type AdminShelterProps = ShelterType & {
  onEdit?: (shelter: ShelterType) => void;
};

const AdminShelter: React.FC<AdminShelterProps> = ({ id, name, address, locationCountry, locationCity, onEdit }) => {
  const [showEditModal, setShowEditModal] = useState(false);

  // Example: Fetching shelter details
  const handleFetchDetails = async () => {
    try {
      // Replace with your API call
      // const response = await fetch(`/api/shelter/${id}`);
      // const data = await response.json();
      // Handle the fetched data (e.g., navigate to detail view or update state)
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const handleEditClick = () => {
    setShowEditModal(true);
  };
  

    // Function to handle saving the edited shelter
    const handleSave = async (updatedShelter: ShelterType) => {
      try {
        const response = await httpRequest('POST', `/admin/modify-shelter`, updatedShelter);
        console.log('Update Response:', response);
        onEdit(updatedShelter); // Call the passed onEdit function with the updated shelter
        setShowEditModal(false)
      } catch (error) {
        console.error('Error updating shelter:', error);
      }
    };

  
  // Example: Handling delete action
  const handleDelete = () => {
    // Logic to handle delete (e.g., show confirmation dialog)
  };
  
  return(
    <div className="admin-shelter">
      <div className="admin-shelter-name">
        <p>{name}</p>
      </div>
      <div className="admin-shelter-address">
        <p>{address}</p>
      </div>
      <div className="admin-shelter-icon" onClick={handleFetchDetails}>
        <IoIosPeople style={{ fontSize: '30px', cursor: 'pointer' }} />
      </div>
      <div className="admin-shelter-icon" onClick={handleEditClick}>
        <FaPen style={{ fontSize: '20px', cursor: 'pointer', color: 'blue'}} />
      </div>
      <div className="admin-shelter-icon" onClick={handleDelete}>
        <FaTrash style={{ fontSize: '20px', cursor: 'pointer', color: 'red'}} />
      </div>
      <EditShelterModal
        show={showEditModal}
        onClose={() => setShowEditModal(false)}
        shelter={{ id, name, address, locationCountry, locationCity }}
        onSave={handleSave} // Pass handleSave as onSave
      />
    </div>
  );
}


export default AdminShelter;
