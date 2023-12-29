import React, { useState } from 'react';
import { Button } from "@mui/material";
import { httpRequest } from "../../Controller/HttpProxy";
import { useNavigate } from "react-router-dom";
import { PetDTO } from '../../DTO/PetDTO';

const EditPetForm = ({ petDTO }) => {
  const [petData, setPetData] = useState({ ...petDTO } as PetDTO);

  const [submitted, setSubmitted] = useState(false);
  const [error, setError] = useState(false);

  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value, type, checked } = e.target;
    setPetData({ ...petData, [name]: type === 'checkbox' ? checked : value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (Object.values(petData).some(field => field === '' || field === null)) {
      setError(true);
    } else {
      setError(false);
      setSubmitted(true);
      httpRequest('POST', '/staff/edit/pet', petData)
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
    <div style={formStyle}>
      <h2 style={{ textAlign: 'center' }}>Edit Pet Details</h2>
      {submitted && <div style={{ color: 'green' }}>Pet updated successfully!</div>}
      {error && <div style={{ color: 'red' }}>Please fill in all fields.</div>}
      <form onSubmit={handleSubmit}>
        <div style={inputStyle}>
          <label style={labelStyle}>Pet Name</label>
          <input
            type="text"
            name="name"
            value={petData.name}
            onChange={handleInputChange}
            placeholder={petData.name}
            style={{ width: '100%', padding: '10px' }}
          />
        </div>
        <div style={inputStyle}>
          <label style={labelStyle}>Is Sterilized</label>
          <input
            type="checkbox"
            name="isSterilized"
            checked={petData.isSterilized}
            onChange={handleInputChange}
          />
        </div>
        <div style={inputStyle}>
          <label style={labelStyle}>Is Vaccinated</label>
          <input
            type="checkbox"
            name="isVaccinated"
            checked={petData.isVaccinated}
            onChange={handleInputChange}
          />
        </div>
        <div style={inputStyle}>
          <label style={labelStyle}>Is House Trained</label>
          <input
            type="checkbox"
            name="isHouseTrained"
            checked={petData.isHouseTrained}
            onChange={handleInputChange}
          />
        </div>
        <button type="submit" className="form-button" style={{
          padding: '10px 20px',
          backgroundColor: '#007bff',
          color: 'white',
          border: 'none',
          borderRadius: '5px',
          cursor: 'pointer'
        }}>
          Edit Pet
        </button>
      </form>
    </div>
  );
};

export default EditPetForm;
