import React, { useState } from 'react';
import './EditShelterModal.css';
import "./SearchAndFilter.css";
// Define the props for the EditShelterModal component

const EditPetProfileModal = ({ show, pet, onSave}) => { //onClose and onSave
    const [editedName, setEditedName] = useState(pet.name);
    const [isVaccinated, setIsVaccinated] = useState(pet.isVaccinated);
    const [isSterilized, setIsSterilized] = useState(pet.isSterilized);
    const [isHouseTrained, setisHouseTrained] = useState(pet.isHouseTrained);
      
    const handleSubmit = () => {

      // call the on save method with all the values
    //   e.preventDefault();
    const dto={
      name:editedName,
      isVaccinated:isVaccinated,
      isSterilized:isSterilized,
      isHouseTrained:isHouseTrained,
    };
    onSave(dto)
    //   onSave({ ...shelter, name: editedName, address: editedAddress });
    };
  
    if (!show) return null;

  return (
    <div className="modal-backdrop">
      <div className="modal">
        <form onSubmit={handleSubmit}>
          <h2>Edit Pet Profile</h2>

          {/* Name input */}
          <label htmlFor="name">Name:</label>
          <input 
            type="text" 
            id="name" 
            value={editedName} 
            onChange={(e) => setEditedName(e.target.value)} 
          />

<div className="criteria-box">
      <div className="checkbox-container">
        <input
          type="checkbox"
          checked={isHouseTrained}
          onChange={() => setisHouseTrained(!isHouseTrained)}
          className="checkbox-input"
        />
        <div className="checkbox-label">House-trained</div>
      </div>
      <div className="checkbox-container">
        <input
          type="checkbox"
          checked={isVaccinated}
          onChange={() => setIsVaccinated(!isVaccinated)}
          className="checkbox-input"
        />
        <div className="checkbox-label">vaccinated</div>
      </div>
      <div className="checkbox-container">
        <input
          type="checkbox"
          checked={isSterilized}
          onChange={() => setIsSterilized(!isSterilized)}
          className="checkbox-input"
        />
        <div className="checkbox-label">sterilized</div>
      </div>
      
    </div> 
    <button className="buttonSave" >
      SAVE
    </button>
          {/* <div className="modal-actions">
            <button type="submit">Save</button>
            <button type="button" onClick={onClose}>Cancel</button>
          </div> */}
        </form>
      </div>
    </div>
  );
};

export default EditPetProfileModal;