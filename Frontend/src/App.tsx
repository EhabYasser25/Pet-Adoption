import { Routes, Route } from "react-router-dom";
import { Container } from "react-bootstrap";
import "./App.css";

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
        {/* <Route path="/signin" element={<SignIn />}></Route> */}
      </Routes>
    </Container>
  );
}

export default App;
