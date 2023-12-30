import React, { useState } from 'react';
import './StaffEditModal.css';

// Define a TypeScript interface for Staff
interface StaffType {
    id: number;
    firstName: string;
    middleName: string | null; // Middle name could be null if not provided
    lastName: string;
    fullName: string; // Calculated in the application, not stored in the database
    username: string;
    password: string;
    email: string;
    phoneNo: string;
    gender: string;
    birthdate: string; // LocalDate mapped to a string format (e.g., 'YYYY-MM-DD')
    role: string;
  }

// Define the props for the EditStaffModal component
interface EditStaffModalProps {
  show: boolean;
  onClose: () => void;
  staff: StaffType;  // Assuming you have a StaffType
  onSave: (staff: StaffType) => void;
}

const EditStaffModal: React.FC<EditStaffModalProps> = ({ show, onClose, staff, onSave }) => {
    const [editedUserName, setEditedUserName] = useState(staff.username);
    const [editedRole, setEditedRole] = useState(staff.role);
    const [editedFirstName, setEditedFirstName] = useState(staff.firstName);
    const [editedMiddleName, setEditedMiddleName] = useState(staff.middleName);
    const [editedLastName, setEditedLastName] = useState(staff.lastName);
    const [editedFullName, setEditedFullName] = useState(staff.fullName);
    const [editedEmail, setEditedEmail] = useState(staff.email);
    const [editedPhoneNo, setEditedPhoneNo] = useState(staff.phoneNo);
    const [editedBirthdate, setEditedBirthdate] = useState(staff.birthdate);

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        onSave({
            ...staff,
            username: editedUserName,
            firstName: editedFirstName,
            middleName: editedMiddleName,
            lastName: editedLastName,
            fullName: editedFullName,
            email: editedEmail,
            phoneNo: editedPhoneNo,
            birthdate: editedBirthdate,
        });      
        onClose(); // Close the modal after saving
    };
  
    if (!show) return null;
    return (
        <div className="modal-backdrop" onClick={onClose}> {/* Close modal when backdrop is clicked */}
          <div className="modal" onClick={(e) => e.stopPropagation()}> {/* Prevent click inside modal from closing it */}
            <form onSubmit={handleSubmit}>
              <h2>Edit Staff</h2>
      
              {/* Name input */}
              <label htmlFor="name">Name:</label>
              <input 
                type="text" 
                id="name" 
                value={editedUserName} 
                onChange={(e) => setEditedUserName(e.target.value)} 
              />
      
              {/* First Name input */}
              <label htmlFor="firstName">First Name:</label>
              <input 
                type="text" 
                id="firstName" 
                value={editedFirstName} 
                onChange={(e) => setEditedFirstName(e.target.value)} 
              />

               {/* Middle Name input */}
               <label htmlFor="middleName">Middle Name:</label>
              <input 
                type="text" 
                id="middleName" 
                value={editedMiddleName} 
                onChange={(e) => setEditedMiddleName(e.target.value)} 
              />
      
              {/* Last Name input */}
              <label htmlFor="lastName">Last Name:</label>
              <input 
                type="text" 
                id="lastName" 
                value={editedLastName} 
                onChange={(e) => setEditedLastName(e.target.value)} 
              />
      
              {/* Email input */}
              <label htmlFor="email">Email:</label>
              <input 
                type="email" 
                id="email" 
                value={editedEmail} 
                onChange={(e) => setEditedEmail(e.target.value)} 
              />
      
              {/* Phone Number input */}
              <label htmlFor="phoneNo">Phone Number:</label>
              <input 
                type="tel" 
                id="phoneNo" 
                value={editedPhoneNo} 
                onChange={(e) => setEditedPhoneNo(e.target.value)} 
              />
      
              {/* Birthdate input */}
              <label htmlFor="birthdate">Birthdate:</label>
              <input 
                type="date" 
                id="birthdate" 
                value={editedBirthdate} 
                onChange={(e) => setEditedBirthdate(e.target.value)} 
              />
      
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

export default EditStaffModal;
