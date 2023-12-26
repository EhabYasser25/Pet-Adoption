import { Container } from "react-bootstrap";
import "./App.css";
import SignIn from "./Pages/SignIn";
import UserHomePage from "./Pages/UserHomePage";
import Applications from "./Pages/Applications"; // Assuming this is a .tsx file
import { Routes, Route } from 'react-router-dom';
import HomePage from "./Pages/admin/AdminHomePage"; // Assuming this is a .tsx file

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
            <Routes>
                <Route path="/" element={<UserHomePage/>}></Route>
                <Route path="/signin" element={<SignIn />}></Route>
                <Route path="/user/applications" element={<Applications/>}></Route>
                <Route path="/admin/dashboard" element={<HomePage />}></Route>
            </Routes>
        </Container>
    );
}

export default App;