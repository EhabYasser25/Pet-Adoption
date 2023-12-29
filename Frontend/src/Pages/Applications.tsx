import React, { useState, useEffect } from 'react';
import { Card, Button, Row, Col, Container } from 'react-bootstrap';
import {httpRequest} from "../Controller/HttpProxy";

interface Application {
    id: number;
    userId: number;
    petId: number;
    shelterId: number;
    message: string;
    timeStamp: string;
    status: string;
}

const Applications: React.FC = () => {
    const [applications, setApplications] = useState<Application[]>([]);

    useEffect(() => {

        httpRequest('GET', '/staff/view/applications')
          .then((response) => {
            console.log(response.data)
              setApplications(response.data)
          })
          .catch((error) => {
              console.log(error.response.data.message)
          })
    }, []);

    return (
      <Container style={{ marginTop: '20px' }}>
          <Row xs={1} md={2} lg={3} className="g-4">
              {applications.map((application) => (
                <Col key={application.id}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Application #{application.id}</Card.Title>
                            <Card.Text>
                                User ID: {application.userId}<br />
                                Pet ID: {application.petId}<br />
                                Shelter ID: {application.shelterId}<br />
                                Message: {application.message}<br />
                                TimeStamp: {new Date(application.timeStamp).toLocaleString()}<br />
                                Status: {application.status}
                            </Card.Text>
                            <Button variant="primary">View Details</Button>
                        </Card.Body>
                    </Card>
                </Col>
              ))}
          </Row>
      </Container>
    );
};

export default Applications;
