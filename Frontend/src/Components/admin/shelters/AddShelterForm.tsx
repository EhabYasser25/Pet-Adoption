import React, { useEffect, useState } from 'react';
import './AddShelterForm.css';
import { httpRequest } from '../../../Controller/HttpProxy';

const AddShelterForm = () => {
  const [shelter, setShelter] = useState({
    name: '',
    address: '',
    locationCountry: '',
    locationCity: ''
  });
  const [countries, setCountries] = useState([]);
  const [cities, setCities] = useState([]);

  useEffect(() => {
    // Fetch countries when the component mounts
    httpRequest("GET", "/location/all-countries")
      .then((response) => {
        setCountries(response.data);
      })
      .catch(error => console.error("Failed to fetch countries", error));
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setShelter(prevShelter => ({
      ...prevShelter,
      [name]: value
    }));

    // If the country changes, fetch the corresponding cities
    if (name === 'locationCountry') {
      httpRequest("GET", `/location/${value}`)
        .then((response) => {
          setCities(response.data);
        })
        .catch(error => console.error(`Failed to fetch cities for country ${value}`, error));
    }
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    httpRequest("POST", "/admin/save-shelter", shelter)
      .then(response => {
        if(response.data) alert("Shelter Saved Successfully");
        else alert("Encountered Problem Saving Shelter");
      })
      .catch(error => console.error("Failed to save shelter", error));
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
          <option value="">Select Country</option>
          {countries.map((country, index) => (
            <option key={index} value={country}>{country}</option>
          ))}
        </select>
      </div>
      <div className="admin-form-group">
        <label htmlFor="locationCity">City</label>
        <select
          id="locationCity"
          name="locationCity"
          value={shelter.locationCity}
          onChange={handleChange}
          disabled={!shelter.locationCountry}
        >
          <option value="">Select City</option>
          {cities.map((city, index) => (
            <option key={index} value={city}>{city}</option>
          ))}
        </select>
      </div>
      <button className="admin-button" type="submit">Add Shelter</button>
    </form>
  );
};

export default AddShelterForm;
