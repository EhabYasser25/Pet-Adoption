// AddEmployeeModal.tsx

import axios from 'axios';
import React, { useState } from 'react';
import { Input, Button, Stack,  } from "@mui/material";
import CloudUploadIcon from '@mui/icons-material/CloudUpload';

interface AddEmployeeModalProps {
  show: boolean;
  onClose: () => void;
  shelterId: string;
  onAdd: (employeeData: any) => void; 
}

const AddEmployeeModal: React.FC<AddEmployeeModalProps> = ({ show, onClose, shelterId, onAdd }) => {
  const initialEmployeeState = {
    username: '',
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    middleName: '',
    birthdate: '',
    gender: '',
    phoneNo: ''
  };

  const [employeeData, setEmployeeData] = useState(initialEmployeeState);
  const [tempImageUrl, setTempImageUrl] = useState('');
  const [imageFile, setImageFile] = useState(null)
  const [uploadedFileName, setUploadedFileName] = useState(null);
  const [isUploading, setIsUploading] = useState(false);
  
  const VisuallyHiddenStyle ={
    clip: 'rect(0 0 0 0)',
    clipPath: 'inset(50%)',
    height: 1,
    overflow: 'hidden',
    position: 'absolute',
    bottom: 0,
    left: 0,
    whiteSpace: 'nowrap',
    width: 1,
}

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setEmployeeData({ ...employeeData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const staffMemberDTO = {
      staffDetails: { ...employeeData },
      shelterId
    };
    onAdd(staffMemberDTO);
    // Reset the form fields after submission
    setEmployeeData(initialEmployeeState);
    console.log(employeeData)
    onClose();
  };

  const uploadImage = async () => {
    if (!(imageFile instanceof File)) {
      console.error("The imageFile is not an instance of File.");
      return;
    }
  
    setIsUploading(true); 
    
    const formData = new FormData();
    formData.append("file", imageFile);
    formData.append("upload_preset", "ml_default");
    formData.append("api_key", "941432731188379");
  
    // Log the file data
    for (let [key, value] of formData.entries()) {
      console.log(key, value);
    }
  
    try {
      const uploadResponse = await axios.post(
        "https://api.cloudinary.com/v1_1/dvnf3jmrz/image/upload",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        }
      );
      setIsUploading(false); // Stop loading on success
      console.log(uploadResponse.data.secure_url + " before..")
      return uploadResponse.data;
    } catch (error) {
      if (error.response) {
        console.error(error.response.data);
        console.error(error.response.status);
        console.error(error.response.headers);
      } else if (error.request) {
        console.error(error.request);
      } else {
        console.error('Error', error.message);
      }
      throw error;
    }
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files[0]) {
      const fileName = e.target.files[0].name;
      setUploadedFileName(fileName);
      console.log("Selected file:", e.target.files[0]);
      setTempImageUrl(URL.createObjectURL(e.target.files[0]));
      setImageFile(e.target.files[0]);
    } else {
      console.error("No file selected");
    }
  };
  

  if (!show) return null;
  return (
    <div className="modal-backdrop" onClick={onClose}>
      <div className="modal" onClick={(e) => e.stopPropagation()}>
        <form onSubmit={handleSubmit}>
          <h2>Add Employee</h2>
          {/* Include form fields for all properties */}
          <input type="text" name="username" placeholder="Username" value={employeeData.username} onChange={handleChange} />
          <input type="email" name="email" placeholder="Email" value={employeeData.email} onChange={handleChange} />
          <input type="password" name="password" placeholder="Password" value={employeeData.password} onChange={handleChange} />
          <input type="text" name="firstName" placeholder="First Name" value={employeeData.firstName} onChange={handleChange} />
          <input type="text" name="middleName" placeholder="Middle Name" value={employeeData.middleName} onChange={handleChange} />
          <input type="text" name="lastName" placeholder="Last Name" value={employeeData.lastName} onChange={handleChange} />
          <input type="date" name="birthdate" placeholder="Birthdate" value={employeeData.birthdate} onChange={handleChange} />
          <select name="gender" value={employeeData.gender} onChange={handleChange}>
            <option value="">Select Gender</option>
            <option value="MALE">Male</option>
            <option value="FEMALE">Female</option>
          </select>
          <input type="tel" name="phoneNo" placeholder="Phone Number" value={employeeData.phoneNo} onChange={handleChange} />
          {/* <Button component="label" sx={{ margin: '1vh' }} variant="contained" startIcon={<CloudUploadIcon />}>
            Upload Image
            <Input
                sx={VisuallyHiddenStyle}
                type="file"
                onChange={handleFileChange}
                inputProps={{ multiple: true }} 
            />
          </Button>
          {uploadedFileName && (
            <div>
                {uploadedFileName}
            </div>
          )} */}
          <div className="modal-actions">
            <button type="submit">Add</button>
            <button type="button" onClick={onClose}>Cancel</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AddEmployeeModal;
