import { Routes, Route } from "react-router-dom";
import { Container } from "react-bootstrap";
import { SignIn } from "./Pages/SignIn";
import { SignUp } from "./Pages/SignUp";
import "./App.css";
import StaffHomePage from "./Pages/staff/StaffHomaPage";
import AddPetForm from "./Components/staff/AddPetForm";
import UserHomePage from "./Pages/user/UserHomePage";
import Applications from "./Pages/Applications";
import AdminHomePage from "./Pages/admin/AdminHomePage";

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
              <Route path="/signup" element={<SignUp />}></Route>
              <Route path="user/applications" element={<Applications/>}></Route>
              <Route path="/admin/dashboard" element={<AdminHomePage />}></Route>
              <Route path="/staff/dashboard" element={<StaffHomePage />}></Route>
              <Route path="/staff/addpet" element={<AddPetForm />}></Route>
              <Route path="/staff/applications" element={<Applications />}></Route>
          </Routes>
      </Container>
    );
}

export default App;