import React, { useState } from 'react';
import './EditShelterModal.css';


// Define the props for the EditShelterModal component
interface EditShelterModalProps {
    show: boolean;
    onClose: () => void;
    shelter: ShelterType;
    onSave: (shelter: ShelterType) => void;
  }

const EditShelterModal: React.FC<EditShelterModalProps> = ({ show, onClose, shelter, onSave }) => {
    const [editedName, setEditedName] = useState(shelter.name);
    const [editedAddress, setEditedAddress] = useState(shelter.address);

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
            <label htmlFor="name">Name:</label>
            <input 
                type="text" 
                id="name" 
                value={editedName} 
                onChange={(e) => setEditedName(e.target.value)} 
            />

            <label htmlFor="address">Address:</label>
            <input 
                type="text" 
                id="address" 
                value={editedAddress} 
                onChange={(e) => setEditedAddress(e.target.value)} 
            />

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
