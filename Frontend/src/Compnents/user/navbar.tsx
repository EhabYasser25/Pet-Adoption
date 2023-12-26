import { NavLink } from "react-bootstrap";
import { Link } from "react-router-dom";
import React from "react";
import "./navbar.css"

const Navbar = () => {
  const userName = "mariam gerges";

  const handleMouseEnter = (e: React.MouseEvent<HTMLElement>) => {
    const target = e.currentTarget as HTMLElement;
    target.style.color = "#917600"; // Change color on mouse enter
  };

  const handleMouseLeave = (e: React.MouseEvent<HTMLElement>) => {
    const target = e.currentTarget as HTMLElement;
    target.style.color = "#faab02"; // Change back to original color on mouse leave
  };

  const handleLogout = () => {
    console.log("logout");
    // Add logic for logout action
  };

  return (
    <nav className="navbar-user">
      <h1>
        <span className="paw-print">🐾</span>Paws and claws
      </h1>
      <div className="links">
        <Link to="/signin">About</Link>
        <Link to="/">Home</Link>
        <Link to="/user/applications">Applications</Link>
        <span style={{ color: "white" }}>{userName}</span>
        <NavLink>
          <i
            className="fas fa-bell"
            style={{
              color: "#faab02",
              fontSize: 25,
            }}
            onMouseEnter={handleMouseEnter}
            onMouseLeave={handleMouseLeave}
          ></i>
        </NavLink>
        <NavLink onClick={handleLogout}>Logout</NavLink>
      </div>
    </nav>
  );
};

export default Navbar;
