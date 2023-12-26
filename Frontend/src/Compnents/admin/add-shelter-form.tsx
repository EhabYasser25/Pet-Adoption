import React, { useState } from 'react';
import './add-shelter-form.css';

const AddShelterForm = () => {
  const [shelter, setShelter] = useState({
    name: '',
    address: '',
    country: '',
    city: ''
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
    // Submit logic here
  };

  return (
    <form className="add-shelter-form" onSubmit={handleSubmit}>
      <div className="form-group">
        <label htmlFor="name">Shelter Name</label>
        <input
          type="text"
          id="name"
          name="name"
          value={shelter.name}
          onChange={handleChange}
        />
      </div>
      <div className="form-group">
        <label htmlFor="address">Shelter Address</label>
        <input
          type="text"
          id="address"
          name="address"
          value={shelter.address}
          onChange={handleChange}
        />
      </div>
      <div className="form-group">
        <label htmlFor="country">Country</label>
        <select
          id="country"
          name="country"
          value={shelter.country}
          onChange={handleChange}
        >
          {/* List countries or use a library/package to populate this */}
          <option value="">Select Country</option>
          <option value="USA">Country 1</option>
          <option value="Canada">Country 2</option>
          {/* Add more countries */}
        </select>
      </div>
      <div className="form-group">
        <label htmlFor="city">City</label>
        <input
          type="text"
          id="city"
          name="city"
          value={shelter.city}
          onChange={handleChange}
        />
      </div>
      <button type="submit">Add Shelter</button>
    </form>
  );
};

export default AddShelterForm;