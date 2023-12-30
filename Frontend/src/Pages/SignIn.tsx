import { faEnvelope, faLock } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useState } from "react";
import { Container } from "react-bootstrap";
import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import {Link, useNavigate} from "react-router-dom";
import { httpRequest } from "../Controller/HttpProxy";
import { clearCurrentSession, setJwtToken } from "../CurrentSession";
import { LoginRequestDTO } from "../DTO/LoginRequestDTO";
import { GenericResponseDTO } from "../DTO/GenericResponseDTO";

export function SignIn() {
  const [validated, setValidated] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [leavedEmail, setLeavedEmail] = useState(false);
  const [leavedPassword, setLeavedPassword] = useState(false);
  const navigate = useNavigate();

  const isValidEmail = (email: string) => {
    return true
  };

  const isValidPassword = (password: string | any[]) => {
    return password.length >= 8;
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.stopPropagation();
    }

    event.preventDefault();

    const loginRequestDTO: LoginRequestDTO = {
      usernameOrEmail: email,
      password: password,
    };

    clearCurrentSession();

    httpRequest('POST', 'auth/login', loginRequestDTO)
      .then((response) => {
        const responseData = response.data
        alert(responseData)
        setJwtToken(responseData)
        setValidated(true)
        navigate('/user/homePage')
        console.log(responseData)
      })
      .catch((error) => {
        console.log(error)
        alert(error.response.data.message)
      })
  };

  return (
    <div
        style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            minHeight: "100vh",
            backgroundSize: "cover",
            backgroundPosition: "center",
        }}
    >
      <Form
        style={{
          width: "30%",
          backgroundColor: "#f0f0f0",
          color: "#121212",
          padding: "20px",
          borderRadius: "8px",
          boxShadow: "0 0 10px rgba(0,0,0,0.1)",
        }}
        noValidate
        validated={validated}
        onSubmit={handleSubmit}
      >
        <h2 style={{textAlign: "center", marginBottom: "20px"}}>Sign In</h2>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomEmail">
            <Form.Label>Username</Form.Label>
            <Container fluid style={{padding: 0}}>
              <Row>
                <Col md={1} style={{paddingTop: 8}}>
                  <FontAwesomeIcon
                    icon={faEnvelope}
                    style={{
                      color: "#0000FF",
                      fontSize: "20px",
                      marginRight: "10px",
                    }}
                  />
                </Col>
                <Col md={11}>
                  <Form.Control
                    required
                    type="text"
                    placeholder="Enter your username or email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    onBlur={() => setLeavedEmail(true)}
                    isInvalid={leavedEmail && !isValidEmail(email)}
                  />
                </Col>
              </Row>
            </Container>
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomPassword">
            <Form.Label>Password</Form.Label>
            <Container fluid style={{padding: 0}}>
              <Row>
                <Col md={1} style={{paddingTop: 8}}>
                  <FontAwesomeIcon
                    icon={faLock}
                    style={{
                      color: "#0000FF",
                      fontSize: "20px",
                      marginRight: "10px",
                    }}
                  />
                </Col>
                <Col md={11}>
                  <Form.Control
                    required
                    type="password"
                    placeholder="Enter your password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    onBlur={() => setLeavedPassword(true)}
                    isInvalid={leavedPassword && !isValidPassword(password)}
                  />
                </Col>
              </Row>
            </Container>
          </Form.Group>
        </Row>
        <div
          className="mb-1"
          style={{display: "flex", justifyContent: "center"}}
        >
          <Button
            type="submit"
            disabled={!isValidEmail(email) || !isValidPassword(password)}
            style={{
              width: "100%",
              backgroundColor: "#0000FF",
              borderColor: "#0000FF",
              transition: "background-color 0.3s",
            }}
            onMouseEnter={(e) =>
              (e.currentTarget.style.backgroundColor =
                "rgba(0, 0, 255, 0.4)")
            }
            onMouseLeave={(e) =>
              (e.currentTarget.style.backgroundColor = "#0000FF")
            }
          >
            Submit
          </Button>
        </div>
        <div style={{display: "flex", justifyContent: "center"}}>
          <p style={{paddingRight: "10px"}}>Don't have an account? </p>
          <Link to="/signup">Sign Up</Link>
        </div>
      </Form>
    </div>
  );
}
