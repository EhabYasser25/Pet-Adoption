import { Container } from "react-bootstrap";
import "./App.css";
import SignIn from "./Pages/SignIn";
import UserHomePage from "./Pages/user/UserHomePage.tsx";
import Applications from "./Pages/Applications.tsx";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AdminHomePage from "./Pages/admin/AdminHomePage.tsx";
// import React from "react";

function App() {
    return (
        <Container
            fluid
            style={{
                paddingLeft: 0,
                paddingRight: 0,
                marginLeft: 0,
                marginRight: 0,
            }}
        >
            <Router>
                <Routes>
                    <Route path="/" element={<UserHomePage/>}></Route>
                    <Route path="/signin" element={<SignIn />}></Route>
                    <Route path="user/applications" element={<Applications/>}></Route>
                    <Route path="/admin/dashboard" element={<AdminHomePage />}></Route>
                </Routes>
            </Router>
        </Container>
    );
}

export default App;