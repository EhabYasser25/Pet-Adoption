import React, { useEffect, useState } from 'react';
import './StaffList.css'; // Make sure to create a corresponding CSS file
import { httpRequest } from '../../../Controller/HttpProxy';
import EditStaffModal from './StaffEditModal';
import AddEmployeeModal from './AssignEmployeeModal';

interface User {
  id: number;
  firstName: string;
  middleName: string;
  lastName: string;
  fullName: string;
  username: string;
  password: string;
  email: string;
  image: string;
  phoneNo: string; 
  gender: string;
  birthdate: string;
  role: string;
}

interface StaffListProps {
  shelterId: string;
}

const StaffList: React.FC<StaffListProps> = ({ shelterId }) => {
  const [staffMembers, setStaffMembers] = useState<User[]>([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [editingStaff, setEditingStaff] = useState<User | null>(null);
  const [showEditModal, setShowEditModal] = useState(false);
  const [showAddModal, setShowAddModal] = useState(false);

  const staffPerPage = 6; // 2 rows * 4 columns

  const handleAddEmployeeClick = () => {
    setShowAddModal(true);
  };

  const handleAddEmployee = (employeeData: any) => {
    // API call to add employee
    // Optionally update the staff list state after adding
  };


  useEffect(() => {
    httpRequest("GET", `/admin/shelters/${shelterId}/staff`)
      .then(response => {
        setStaffMembers(response.data);
        console.log(response.data)
      })
      .catch(error => {
        console.error("Failed to fetch staff", error);
      });
  }, [shelterId]);

  const handleEditClick = (staffMember: User) => {
    setEditingStaff(staffMember);
    setShowEditModal(true); // This line should show the modal
  };

  const closeEditModal = () => {
    setShowEditModal(false);
  };

 // StaffList component
 const handleSave = async (updatedStaff: User) => {
    try {
      const response = await httpRequest('POST', '/admin/modify-staff', updatedStaff);
      if (response.data) {
        console.log('Staff updated successfully');
        alert('Staff updated successfully');
  
        // Update local state only if the update is successful
        setStaffMembers(prevStaffMembers =>
          prevStaffMembers.map(staff => 
            staff.id === updatedStaff.id ? updatedStaff : staff
          )
        );
  
        closeEditModal();
      } else {
        console.error('Failed to update staff');
        alert('Failed to update staff');
      }
    } catch (error) {
      console.error('Error updating staff:', error);
      alert('Error updating staff');
    }
  };
  

  const handleDelete = async (staffId: number) => {
    try {
      const response = await httpRequest('DELETE', `/admin/staff/delete/${staffId}`);
      if (response.data) {
        console.log('Staff deleted successfully');
        setStaffMembers(prevStaffMembers => prevStaffMembers.filter(staff => staff.id !== staffId));
        alert('Staff deleted successfully')
      } else {
        console.error('Failed to delete staff');
        alert('Failed to delete staff')
      }
    } catch (error) {
      console.error('Error deleting staff:', error);
      alert('Error deleting staff')
    }
  };
  
  // Calculate the number of pages
  const pageCount = Math.ceil(staffMembers.length / staffPerPage);
  
  // Get current staff members
  const indexOfLastStaff = currentPage * staffPerPage;
  const indexOfFirstStaff = indexOfLastStaff - staffPerPage;
  const currentStaff = staffMembers.slice(indexOfFirstStaff, indexOfLastStaff);

  // Change page handler
  const paginate = (pageNumber: number) => setCurrentPage(pageNumber);

  return (
    <div className="staff-list">
    <button className="add-employee-button" onClick={handleAddEmployeeClick}>Add Employee</button>
        {showAddModal && (
        <AddEmployeeModal
            show={showAddModal}
            onClose={() => setShowAddModal(false)}
            shelterId={shelterId}
            onAdd={handleAddEmployee}
        />
        )}
      <div className="staff-cards-container">
        {currentStaff.map((staff) => (
          <div className="staff-card" key={staff.id}>
            <img src="https://res.cloudinary.com/dvnf3jmrz/image/upload/v1703766127/OIP_4_brupxn.jpg" alt={staff.username} className="staff-image" />
            <div className="staff-info">
              <h3>{`${staff.firstName} ${staff.middleName ? staff.middleName + ' ' : ''}${staff.lastName}`}</h3>
              <p data-label="Username:">{staff.username}</p>
              <p data-label="Full Name:">{staff.fullName}</p>
              <p data-label="Email:">{staff.email}</p>
              <p data-label="Phone:">{staff.phoneNo}</p>
              <p data-label="Gender:">{staff.gender}</p>
              <p data-label="Birthdate:">{staff.birthdate}</p>
              <button onClick={() => handleEditClick(staff)}>Edit</button>
              <button className="delete-button" onClick={() => handleDelete(staff.id)}>Delete</button>
            </div>
          </div>
        ))}
      </div>

      {showEditModal && editingStaff && (
        <EditStaffModal
          show={showEditModal}
          onClose={closeEditModal}
          staff={editingStaff}
          onSave={handleSave}
        />
      )}

      <div className="pagination">
        {[...Array(pageCount)].map((_, i) => (
          <button key={i} onClick={() => paginate(i + 1)}>
            {i + 1}
          </button>
        ))}
      </div>
    </div>
  );
};

export default StaffList;
