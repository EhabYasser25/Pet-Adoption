import { Container } from "react-bootstrap";
import SignIn from "./Pages/SignIn.tsx";
import UserHomePage from "./Pages/UserHomePage.tsx";
import Applications from "./Pages/Applications.tsx"; // Assuming this is a .tsx file
import { Routes, Route } from 'react-router-dom';
import HomePage from "./Pages/admin/home-page.tsx"; // Assuming this is a .tsx file

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
