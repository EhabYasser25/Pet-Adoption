import React, { useState } from 'react';
import './AddShelterForm.css';
import { httpRequest } from '../../Controller/HttpProxy';

const AddShelterForm = () => {
  const [shelter, setShelter] = useState({
    name: '',
    address: '',
    locationCountry: '',
    locationCity: ''
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setShelter({
      ...shelter,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log(shelter);
    httpRequest("POST", "/admin/save-shelter", shelter).then((response) => {
      if(response.data) alert("Shelter Saved Successfully")
      else alert("Encountered Problem Saving Shelter")
    });
  };
  
  return (
    <form className="admin-add-shelter-form" onSubmit={handleSubmit}>
      <div className="admin-form-group">
        <label htmlFor="name">Shelter Name</label>
        <input
          type="text"
          id="name"
          name="name"
          value={shelter.name}
          onChange={handleChange}
        />
      </div>
      <div className="admin-form-group">
        <label htmlFor="address">Shelter Address</label>
        <input
          type="text"
          id="address"
          name="address"
          value={shelter.address}
          onChange={handleChange}
        />
      </div>
      <div className="admin-form-group">
        <label htmlFor="locationCountry">Country</label>
        <select
          id="locationCountry"
          name="locationCountry"
          value={shelter.locationCountry}
          onChange={handleChange}
        >
          {/* List countries or use a library/package to populate this */}
          <option value="">Select Country</option>
          <option value="Country">Country 1</option>
          <option value="Country 2">Country 2</option>
        </select>
      </div>
      <div className="admin-form-group">
        <label htmlFor="locationCity">City</label>
        <input
          type="text"
          id="locationCity"
          name="locationCity"
          value={shelter.locationCity}
          onChange={handleChange}
        />
      </div>
      <button className="admin-button" type="submit">Add Shelter</button>
    </form>
  );
};

export default AddShelterForm;
