import React, { useState } from 'react';
import './EditShelterModal.css';

// Define the props for the EditShelterModal component

const EditShelterModal = ({ show, onClose, pet, onSave }) => {
    const [editedName, setEditedName] = useState(pet.name);
    const [isVaccinated, setIsVaccinated] = useState(pet.isVaccinated);
    const [isSterilized, setIsSterilized] = useState(pet.isSterilized);
    const [isHouseTrained, setisHouseTrained] = useState(pet.isHouseTrained);
      
    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
      e.preventDefault();
      onSave({ ...shelter, name: editedName, address: editedAddress });
    };
  
    if (!show) return null;

  return (
    <div className="modal-backdrop">
      <div className="modal">
        <form onSubmit={handleSubmit}>
          <h2>Edit Shelter</h2>

          {/* Name input */}
          <label htmlFor="name">Name:</label>
          <input 
            type="text" 
            id="name" 
            value={editedName} 
            onChange={(e) => setEditedName(e.target.value)} 
          />

          {/* Address input */}
          <label htmlFor="address">Address:</label>
          <input 
            type="text" 
            id="address" 
            value={editedAddress} 
            onChange={(e) => setEditedAddress(e.target.value)} 
          />

          {/* Action buttons */}
          <div className="modal-actions">
            <button type="submit">Save</button>
            <button type="button" onClick={onClose}>Cancel</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default EditShelterModal;