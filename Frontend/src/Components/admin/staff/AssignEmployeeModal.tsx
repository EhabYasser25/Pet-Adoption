// AddEmployeeModal.tsx

import React, { useState } from 'react';
// import './AddEmployeeModal.css'; // Create corresponding CSS

interface AddEmployeeModalProps {
  show: boolean;
  onClose: () => void;
  shelterId: string;
  onAdd: (employeeData: any) => void; // Define a proper type for employee data
}

const AddEmployeeModal: React.FC<AddEmployeeModalProps> = ({ show, onClose, shelterId, onAdd }) => {
  const [employeeData, setEmployeeData] = useState({
    // Define the fields you need, e.g., name, role, email, etc.
    name: '',
    role: '',
    email: '',
    // other fields
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmployeeData({ ...employeeData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    onAdd({ ...employeeData, shelterId });
    onClose();
  };

  if (!show) return null;

  return (
    <div className="modal-backdrop" onClick={onClose}>
      <div className="modal" onClick={(e) => e.stopPropagation()}>
        <form onSubmit={handleSubmit}>
          <h2>Add Employee</h2>
          {/* Input fields for employee data */}
          <label htmlFor="name">Name:</label>
          <input type="text" id="name" name="name" value={employeeData.name} onChange={handleChange} />
          {/* Add other input fields here */}
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
