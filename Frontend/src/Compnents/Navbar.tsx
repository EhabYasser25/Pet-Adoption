import {NavLink} from "react-bootstrap";
import {Link, useNavigate} from "react-router-dom";
import React from "react";

const Navbar = () => {
    const navigate = useNavigate();
    const userName="mariam gerges"
    return (
    <nav className="navbar">
        <h1>  <span className="paw-print">🐾</span>Paws and claws</h1>
        <div className="links">
            <Link to="/signin">   About</Link>
            <Link to="/">Home</Link>
            <Link to="/user/applications"> Applications </Link>
            <span style={{ color: "white" }}>{userName}</span>
            <NavLink>
                <i
                    className="fas fa-bell"
                    style={{
                        color: "#faab02",
                        fontSize: 25,
                    }}
                    onMouseEnter={(e:React.MouseEvent<HTMLElement>) => {
                        e.target.style.color = "#917600"; // Change color on mouse enter
                    }}
                    onMouseLeave={(e) => {
                        e.target.style.color = "#faab02"; // Change back to original color on mouse leave
                    }}
                ></i>
            </NavLink>
            <NavLink onClick={()=>console.log("logout")}>Logout</NavLink>
        </div>
    </nav>
    );
}

export default Navbar;