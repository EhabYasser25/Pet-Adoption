import React, { useState } from 'react';
import './EditShelterModal.css';

// Example static data. Replace with your actual data or API calls.
const countries = ['Country', 'Chad', 'Country 3'];
const cities = {
  'Country': ['Alexandria', 'City 1-2'],
  'Chad': ['Abeche', 'City 2-2'],
  'Country 3': ['City 3-1', 'City 3-2'],
};

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
    const [editedCountry, setEditedCountry] = useState(shelter.locationCountry); // Using shelter.locationCountry
    const [editedCity, setEditedCity] = useState(shelter.locationCity); // Using shelter.locationCity
  
    const handleCountryChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
      setEditedCountry(e.target.value);
      setEditedCity(''); // Reset city when country changes
    };
  
    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
      e.preventDefault();
      onSave({ ...shelter, name: editedName, address: editedAddress, locationCountry: editedCountry, locationCity: editedCity });
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

        {/* Country dropdown */}
        <label htmlFor="country">Country:</label>
          <select id="country" value={editedCountry} onChange={handleCountryChange}>
            <option value="">Select Country</option>
            {countries.map(country => (
              <option key={country} value={country}>{country}</option>
            ))}
          </select>

          {/* City dropdown */}
          <label htmlFor="city">City:</label>
          <select id="city" value={editedCity} onChange={(e) => setEditedCity(e.target.value)}>
            <option value="">Select City</option>
            {cities[editedCountry]?.map(city => (
              <option key={city} value={city}>{city}</option>
            ))}
          </select>

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
