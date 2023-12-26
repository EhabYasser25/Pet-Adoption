import { Link } from 'react-router-dom';
import { IoIosPeople } from 'react-icons/io';
import { FaPen } from 'react-icons/fa';
import { FaTrash } from "react-icons/fa";


const Shelter = ({ id, name, address }: ShelterType) => (
  <div className="admin-shelter">
    <div className="admin-shelter-name">
      <p>{name}</p>
    </div>
    <div className="admin-shelter-address">
      <p>{address}</p>
    </div>
    <div className="admin-shelter-icon">
      {/* Link with IoIosPeople icon */}
      <Link to={`/admin/employees/${id}`}>
        <IoIosPeople style={{ fontSize: '30px', cursor: 'pointer' }} />
      </Link>
    </div>
    <div className="admin-shelter-icon">
      {/* FaPen icon */}
      <FaPen style={{ fontSize: '20px', cursor: 'pointer', color: 'blue'}} />
    </div>
    <div className="admin-shelter-icon">
      {/* FaPen icon */}
      <FaTrash style={{ fontSize: '20px', cursor: 'pointer', color: 'red'}} />
    </div>
  </div>
);

export default Shelter;
