import { Container } from "react-bootstrap";
import "./App.css";

// import SignIn from "./Pages/SignIn";
// import UserHomePage from "./Pages/user/UserHomePage.tsx";
// import Applications from "./Pages/Applications.tsx";
// import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
// import UserHomePage from "./Pages/user/UserHomePage";
import PetGridSearch from "./Components/searchAndFilter/PetGridSearch";
import SearchScreen from "./Components/user/SearchScreen";

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Applications from "./Pages/Applications";
import { SignIn } from "./Pages/SignIn";
import UserHomePage from "./Pages/user/UserHomePage";
import AdminHomePage from "./Pages/admin/AdminHomePage";
import {SignUp} from "./Pages/SignUp";
import StaffHomePage from "./Pages/staff/StaffHomaPage";
import AddPetForm from "./Components/staff/AddPetForm";
import EditPetForm from "./Components/staff/EditPetForm";

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
                    <Route path="/" element={<SignIn />}></Route>
                    <Route path="/signin" element={<SignIn />}></Route>
                    <Route path="/signup" element={<SignUp />}></Route>

                    <Route path="/user/homePage" element={<UserHomePage/>}></Route>
                    <Route path="/userSearch" element={<SearchScreen/>}></Route>
                    
                    <Route path="user/applications" element={<Applications/>}></Route>
                    <Route path="/admin/dashboard" element={<AdminHomePage />}></Route>
                    <Route path="/staff/dashboard" element={<StaffHomePage />}></Route>
                    <Route path="/staff/add/pet" element={<AddPetForm />}></Route>
                    <Route path="/staff/edit/pet" element={<EditPetForm petDTO={{ name: 'max', isSterilized: true }}/>}></Route>
                    <Route path="/staff/applications" element={<Applications />}></Route>
                </Routes>
            </Router>
        </Container>
    );
}

export default App;


