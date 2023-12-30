import React, { useState } from 'react';
import { Input, Button, Stack,  } from "@mui/material";
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import axios from "axios";
import {httpRequest} from "../../Controller/HttpProxy";
import {useNavigate} from "react-router-dom";

const AddPetForm = () => {
  const [petData, setPetData] = useState({
    name: '',
    species: '',
    breed: '',
    birthDate: '',
    gender: '',
    isSterilized: false,
    isVaccinated: false,
    isHouseTrained: false,
    image: null
  });

  const [submitted, setSubmitted] = useState(false);
  const [error, setError] = useState(false);
  const [tempImageUrl, setTempImageUrl] = useState('');
  const [uploadedFileName, setUploadedFileName] = useState(null);
  const [isUploading, setIsUploading] = useState(false);

  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value, type, checked } = e.target;
    setPetData({ ...petData, [name]: type === 'checkbox' ? checked : value });
  };

  const handleImageChange = (e) => {
    setPetData({ ...petData, image: e.target.files[0] });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    httpRequest('POST', '/staff/add/pet', petData)
      .then((response) => {
        console.log(response.data)
        navigate('/staff/dashboard')
      })
      .catch((error) => {
        console.log(error.response.data.message)
      })
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
      <h2 style={{ textAlign: 'center' }}>Add New Pet</h2>
      {submitted && <div style={{ color: 'green' }}>Pet added successfully!</div>}
      {error && <div style={{ color: 'red' }}>Please fill in all fields.</div>}
      <form onSubmit={handleSubmit}>
        <div style={inputStyle}>
          <label style={labelStyle}>Pet Name</label>
          <input
            type="text"
            name="name"
            value={petData.name}
            onChange={handleInputChange}
            placeholder="Enter pet name"
            style={{ width: '100%', padding: '10px' }}
          />
        </div>
        <div style={inputStyle}>
          <label style={labelStyle}>Species</label>
          <input
            type="text"
            name="species"
            value={petData.species}
            onChange={handleInputChange}
            placeholder="Enter species"
            style={{ width: '100%', padding: '10px' }}
          />
        </div>
        <div style={inputStyle}>
          <label style={labelStyle}>Breed</label>
          <input
            type="text"
            name="breed"
            value={petData.breed}
            onChange={handleInputChange}
            placeholder="Enter breed"
            style={{ width: '100%', padding: '10px' }}
          />
        </div>
        <div style={inputStyle}>
          <label style={labelStyle}>Birthdate</label>
          <input
            type="date"
            name="birthDate"
            value={petData.birthDate}
            onChange={handleInputChange}
            style={{ width: '100%', padding: '10px' }}
          />
        </div>
        <div style={inputStyle}>
          <label style={labelStyle}>Gender</label>
          <select
            name="gender"
            value={petData.gender}
            onChange={handleInputChange}
            style={{ width: '100%', padding: '10px' }}
          >
            <option value="">Choose...</option>
            <option value="MALE">Male</option>
            <option value="FEMALE">Female</option>
            <option value="NO_GENDER">No Gender</option>
          </select>
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
        <button type="submit" className="form-button" disabled={isUploading} style={{
          padding: '10px 20px',
          backgroundColor: '#007bff',
          color: 'white',
          border: 'none',
          borderRadius: '5px',
          cursor: 'pointer'
        }}>
          Add Pet
        </button>
        {isUploading && (
          <div className="loading-indicator">
            Processing, please wait...
          </div>
        )}
      </form>
    </div>
  );
};

export default AddPetForm;
