import React, { useState } from "react";
import { Form, Button, Col, Row, Container } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import "react-phone-number-input/style.css";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCalendarDays, faEnvelope, faFileSignature, faGlobe, faLock, faUser } from "@fortawesome/free-solid-svg-icons";
import { httpRequest } from "../Controller/HttpProxy";
import {clearCurrentSession, setJwtToken} from "../CurrentSession";
import { RegistrationRequestDTO } from "../Controller/DTO/authentication/RegistrationRequestDTO";
import { GenericResponseDTO } from "../Controller/DTO/GenericResponseDTO";

export function SignUp() {
  const [validated, setValidated] = useState(false);
  const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [birthDate, setBirthDate] = useState(null);
  const [nationality, setNationality] = useState("");
  const [leavedEmail, setLeavedEmail] = useState(false);
  const [leavedUsername, setLeavedUsername] = useState(false);
  const [leavedPassword, setLeavedPassword] = useState(false);
  const [leavedConfirmPassword, setLeavedConfirmPassword] = useState(false);
  const [leavedFirstName, setLeavedFirstName] = useState(false);
  const [leavedLastName, setLeavedLastName] = useState(false);
  const [leavedBirthDate, setLeavedBirthDate] = useState(false);
  const [leavedNationality, setLeavedNationality] = useState(false);
  const navigate = useNavigate();

  const isValidEmail = (email: string) => {
    return /^[^\s@]+@[^\s@]+\.com$/.test(email);
  };

  const isValidUsername = (username: string) => {
    return /^[a-zA-Z0-9_-]+$/.test(username);
  };

  const isValidPassword = (password: string | any[]) => {
    return password.length >= 8;
  };

  const isPasswordMatch = (password: string, confirmPassword: string) => {
    return confirmPassword.length >= 8 && password === confirmPassword;
  };

  const isValidFirstName = (firstName: string) => {
    return /^[a-zA-Z]+$/.test(firstName);
  };

  const isValidLastName = (lastName: string) => {
    return /^[a-zA-Z]+$/.test(lastName);
  };

  const isValidBirthDate = (birthDate: Date) => {
    return birthDate != null;
  };

  const isValidNationality = (nationality: string) => {
    return /^[a-zA-Z]+$/.test(nationality);
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.stopPropagation();
    }

    event.preventDefault();

    const registrationRequestDTO: RegistrationRequestDTO = {
      email: email,
      userName: username,
      password: password,
      firstName: firstName,
      lastName: lastName,
      birthDate: birthDate,
      nationality: nationality
    }

    clearCurrentSession();

    httpRequest("POST", "auth/register", registrationRequestDTO)
      .then((response) => {
        const responseData = response.data as GenericResponseDTO;
        alert(responseData.message)
        setJwtToken(responseData.data)
        setValidated(true)
        navigate('/')
        console.log(responseData)
      })
      .catch((error) => {
        console.log(error)
        alert()
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
        <h2 style={{ textAlign: "center", marginBottom: "20px" }}>Sign Up</h2>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomEmail">
            <Form.Label>Email</Form.Label>
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
                    placeholder="Enter your email"
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
          <Form.Group as={Col} controlId="validationCustomUsername">
            <Form.Label>Username</Form.Label>
            <Container fluid style={{padding: 0}}>
              <Row>
                <Col md={1} style={{paddingTop: 8}}>
                  <FontAwesomeIcon
                    icon={faUser}
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
                    placeholder="Enter your username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    onBlur={() => setLeavedUsername(true)}
                    isInvalid={leavedUsername && !isValidUsername(username)}
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
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomConfirmPassword">
            <Form.Label>Confirm Password</Form.Label>
            <Container fluid style={{ padding: 0 }}>
              <Row>
                <Col md={1} style={{ paddingTop: 8 }}>
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
                    placeholder="Confirm your password"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    onBlur={() => setLeavedConfirmPassword(true)}
                    isInvalid={leavedConfirmPassword && !isPasswordMatch(password, confirmPassword)}
                  />
                  <Form.Control.Feedback type="invalid"></Form.Control.Feedback>
                </Col>
              </Row>
            </Container>
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomFirstName">
            <Form.Label>First Name</Form.Label>
            <Container fluid style={{padding: 0}}>
              <Row>
                <Col md={1} style={{paddingTop: 8}}>
                  <FontAwesomeIcon
                    icon={faFileSignature}
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
                    placeholder="Enter your first name"
                    value={firstName}
                    onChange={(e) => setFirstName(e.target.value)}
                    onBlur={() => setLeavedFirstName(true)}
                    isInvalid={leavedFirstName && !isValidFirstName(firstName)}
                  />
                </Col>
              </Row>
            </Container>
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomLastName">
            <Form.Label>Last Name</Form.Label>
            <Container fluid style={{padding: 0}}>
              <Row>
                <Col md={1} style={{paddingTop: 8}}>
                  <FontAwesomeIcon
                    icon={faFileSignature}
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
                    placeholder="Enter your last name"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                    onBlur={() => setLeavedLastName(true)}
                    isInvalid={leavedLastName && !isValidLastName(lastName)}
                  />
                </Col>
              </Row>
            </Container>
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationCustomBirthDate">
            <Form.Label>Birth Date</Form.Label>
            <Container fluid style={{ padding: 0 }}>
              <Row>
                <Col md={1} style={{paddingTop: 8}}>
                  <FontAwesomeIcon
                    icon={faCalendarDays}
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
                    type="date"
                    value={birthDate}
                    onChange={(e) => setBirthDate(e.target.value)}
                    onBlur={() => setLeavedBirthDate(true)}
                    isInvalid={leavedBirthDate && !isValidBirthDate(birthDate)}
                  />
                </Col>
              </Row>
            </Container>
          </Form.Group>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="validationNationality">
            <Form.Label>Nationality</Form.Label>
            <Container fluid style={{ padding: 0 }}>
              <Row>
                <Col md={1} style={{paddingTop: 8}}>
                  <FontAwesomeIcon
                    icon={faGlobe}
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
                    placeholder="Enter your nationality"
                    value={nationality}
                    onChange={(e) => setNationality(e.target.value)}
                    onBlur={() => setLeavedNationality(true)}
                    isInvalid={leavedNationality && !isValidNationality(nationality)}
                  />
                </Col>
              </Row>
            </Container>
          </Form.Group>
        </Row>
        <div
          className="mb-1"
          style={{ display: "flex", justifyContent: "center" }}
        >
          <Button
            type="submit"
            disabled={
              !isValidEmail(email) ||
              !isValidUsername(username) ||
              !isValidPassword(password) ||
              !isPasswordMatch(password, confirmPassword) ||
              !isValidFirstName(firstName) ||
              !isValidLastName(lastName) ||
              !isValidBirthDate(birthDate) ||
              !isValidNationality(nationality)
            }
            style={{
              width: "100%",
              borderColor: "#0000FF",
              backgroundColor: "#0000FF",
              transition: "background-color 0.3s",
            }}
            onMouseEnter={(e) =>
              (e.currentTarget.style.backgroundColor =
                "rgba(0, 0, 192, 0.4)")
            }
            onMouseLeave={(e) =>
              (e.currentTarget.style.backgroundColor = "#0000FF")
            }
          >
            Submit
          </Button>
        </div>
        <div
          style={{ width: "100%", display: "flex", justifyContent: "center" }}
        >
          <p style={{ paddingRight: "10px" }}>Have already an account? </p>
          <Link to="/signin">Sign In</Link>
        </div>
      </Form>
    </div>
  );
}