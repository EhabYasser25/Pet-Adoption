import {Navbar} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import {clearCurrentSession} from "../../CurrentSession";

const StaffNavbar = () => {
  const navigate = useNavigate();

  const handleAddPetsClick = () => {
    navigate('/staff/addpet');
  };

  const handleApplicationsClick = () => {
    navigate('/staff/applications')
  };

  const handleLogoutClick = () => {
    clearCurrentSession()
    navigate('/signin')
  };

  return (
    <Navbar
      style={{
        backgroundColor: "blue",
        color: "white",
        padding: "10px",
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        borderRadius: "10px",
      }}
      sticky="top"
    >
      <h1>Staff Dashboard</h1>
      <div style={{ display: "flex", gap: "10px" }}>
        <button
          style={{
            backgroundColor: "blue",
            color: "white",
            border: "none",
            borderRadius: "5px",
            cursor: "pointer",
            fontSize: "1.2rem"
          }}
          onClick={handleAddPetsClick}
        >
          Add Pets
        </button>
        <button
          style={{
            backgroundColor: "blue",
            color: "white",
            border: "none",
            borderRadius: "5px",
            cursor: "pointer",
            fontSize: "1.2rem"
          }}
          onClick={handleApplicationsClick}
        >
          Applications
        </button>
      </div>
      <button
        style={{
          padding: "10px 15px",
          backgroundColor: "blue",
          color: "white",
          border: "none",
          borderRadius: "5px",
          cursor: "pointer"
        }}
        onClick={handleLogoutClick}
      >
        Log Out
      </button>
    </Navbar>
  );
}

export default StaffNavbar;