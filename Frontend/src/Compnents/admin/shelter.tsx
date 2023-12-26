import { Link } from 'react-router-dom';
import { IoIosPeople } from 'react-icons/io';
import { FaPen } from 'react-icons/fa';
import { FaTrash } from "react-icons/fa";

const Shelter = ({ id, name, address }: ShelterType) => {
  // Handler for editing
  const handleEdit = () => {
    // Add your logic for editing here
    console.log(`Edit Shelter with ID: ${id}`);
  };

  // Handler for deletion
  const handleDelete = () => {
    // Add your logic for deletion here
    console.log(`Delete Shelter with ID: ${id}`);
    // Confirm deletion dialog can be added here
  };

  return (
    <div className="shelter">
      <div className="shelter-name">
        <p>{name}</p>
      </div>
      <div className="shelter-address">
        <p>{address}</p>
      </div>
      <div className="shelter-icon">
        <Link to={`/admin/employees/${id}`}>
          <IoIosPeople style={{ fontSize: '30px', cursor: 'pointer' }} />
        </Link>
      </div>
      <div className="shelter-icon" onClick={handleEdit}>
        <FaPen style={{ fontSize: '20px', cursor: 'pointer', color: 'blue'}} />
      </div>
      <div className="shelter-icon" onClick={handleDelete}>
        <FaTrash style={{ fontSize: '20px', cursor: 'pointer', color: 'red'}} />
      </div>
    </div>
  );
};

export default Shelter;
