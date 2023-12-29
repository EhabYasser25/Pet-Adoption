import { Container } from "react-bootstrap";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Applications from "./Pages/Applications";
import { SignIn } from "./Pages/SignIn";
import UserHomePage from "./Pages/user/UserHomePage";
import AdminHomePage from "./Pages/admin/AdminHomePage";
import StaffHomePage from "./Pages/staff/StaffHomaPage";
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
                    <Route path="/staff/dashboard" element={<StaffHomePage />}></Route>
                </Routes>
            </Router>
        </Container>
    );
}

export default App;

    // You can manage user authentication state here if needed
  // const [loggedIn, setLoggedIn] = useState(false);

//   return (
//       <div className="App">
//           <Navbar />
//           <div className="content">
//               <UserHomePage />
//           </div>
//       </div>
//   );
// };
//
// export default App;

