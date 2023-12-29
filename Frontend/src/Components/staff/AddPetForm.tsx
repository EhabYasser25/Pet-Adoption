import React, { useState } from 'react';

const AddPetForm = () => {
  const [petData, setPetData] = useState({
    specie: '',
    breed: '',
    name: '',
    birthdate: '',
    gender: '',
    vaccination: false,
    image: null
  });

  const [submitted, setSubmitted] = useState(false);
  const [error, setError] = useState(false);

  const handleInputChange = (e) => {
    const { name, value, type, checked } = e.target;
    setPetData({ ...petData, [name]: type === 'checkbox' ? checked : value });
  };

  const handleImageChange = (e) => {
    setPetData({ ...petData, image: e.target.files[0] });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (Object.values(petData).some(field => field === '' || field === null)) {
      setError(true);
    } else {
      setError(false);
      setSubmitted(true);
      // TODO: Add API call or other logic to handle form submission
    }
  };

  const formStyle = {
    maxWidth: '600px',
    margin: '0 auto',
    padding: '20px',
    backgroundColor: '#f7f7f7',
    borderRadius: '10px',
    boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)'
  };

  const labelStyle = {
    fontWeight: 'bold',
    marginBottom: '5px'
  };

  const inputStyle = {
    marginBottom: '15px'
  };

  return (
    <div style={{
      maxWidth: '600px',
      margin: '0 auto',
      padding: '20px',
      backgroundColor: '#f7f7f7',
      borderRadius: '10px',
      boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)'
    }}>
      <h2 style={{ textAlign: 'center' }}>Add New Pet</h2>
      {submitted && <div style={{ color: 'green' }}>Pet added successfully!</div>}
      {error && <div style={{ color: 'red' }}>Please fill in all fields.</div>}
      <form onSubmit={handleSubmit}>
        <div style={{marginBottom: '15px'}}>
          <label style={{
            fontWeight: 'bold',
            marginBottom: '5px'
          }}>Pet Name</label>
          <input
            type="text"
            name="name"
            value={petData.name}
            onChange={handleInputChange}
            placeholder="Enter pet name"
            style={{ width: '100%', padding: '10px' }}
          />
        </div>
        <div style={{marginBottom: '15px'}}>
          <label style={labelStyle}>Pet Specie</label>
          <select
            name="type"
            value={petData.specie}
            onChange={handleInputChange}
            style={{ width: '100%', padding: '10px' }}
          >
            <option>Choose...</option>
            <option>Dog</option>
            <option>Cat</option>
            <option>Bird</option>
            <option>Other</option>
          </select>
        </div>
        <div style={{marginBottom: '15px'}}>
          <label style={labelStyle}>Breed</label>
          <input
            type="text"
            name="breed"
            value={petData.breed}
            onChange={handleInputChange}
            placeholder="Enter breed (if known)"
            style={{ width: '100%', padding: '10px' }}
          />
        </div>
        <div style={{ marginBottom: '15px' }}>
          <label style={{
            fontWeight: 'bold',
            marginBottom: '5px'
          }}>Birthdate</label>
          <input
            type="date"
            name="birthdate"
            value={petData.birthdate}
            onChange={handleInputChange}
            style={{ width: '100%', padding: '10px' }}
          />
        </div>
        <div style={{ marginBottom: '15px' }}>
          <label style={{
            fontWeight: 'bold',
            marginBottom: '5px'
          }}>Gender</label>
          <select
            name="gender"
            value={petData.gender}
            onChange={handleInputChange}
            style={{width: '100%', padding: '10px'}}
          >
            <option value="">Choose...</option>
            <option value="MALE">Male</option>
            <option value="FEMALE">Female</option>
            <option value="NO_GENDER">No Gender</option>
          </select>
        </div>
        <div style={{marginBottom: '15px' }}>
          <label style={{
            fontWeight: 'bold',
            marginBottom: '5px'
          }}>
            <input
              type="checkbox"
              name="vaccination"
              checked={petData.vaccination}
              onChange={handleInputChange}
            />
            Vaccinated
          </label>
        </div>
        <div style={{ marginBottom: '15px' }}>
          <label style={{
            fontWeight: 'bold',
            marginBottom: '5px'
          }}>Image</label>
          <input
            type="file"
            name="image"
            onChange={handleImageChange}
            style={{ width: '100%', padding: '10px' }}
          />
        </div>
        <button type="submit" style={{
          padding: "10px 15px",
          backgroundColor: "blue",
          color: "white",
          border: "none",
          borderRadius: "5px",
          cursor: "pointer",
        }}>
          Add Pet
        </button>
      </form>
    </div>
  );
};

export default AddPetForm;
