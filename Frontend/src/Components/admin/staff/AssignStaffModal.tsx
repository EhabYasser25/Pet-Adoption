// AddEmployeeModal.tsx

import React, { useState } from 'react';

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
