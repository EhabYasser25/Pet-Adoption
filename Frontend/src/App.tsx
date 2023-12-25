import { Container } from "react-bootstrap";
import "./App.css";
import SignIn from "./Pages/SignIn";
import UserHomePage from "./Pages/UserHomePage";
import Applications from "./Pages/Applications.tsx";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
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
