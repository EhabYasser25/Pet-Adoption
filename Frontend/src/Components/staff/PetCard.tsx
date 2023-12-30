import {Card} from "react-bootstrap";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {PetDTO} from "../../DTO/PetDTO";

const PetCard = ({ pet }: { pet: PetDTO }) => {
  // Function to format the date in a more readable format
  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleDateString();
  };

  // Function to format the timestamp into a date and time
  const formatDateTime = (timestamp) => {
    return new Date(timestamp).toLocaleString();
  };

  return (
    <Card style={{ width: '100%', minHeight: '20vh', margin: '10px' }}>
      <Row noGutters>
        {/* Pet image */}
        <Col md={2}>
          <Card.Img
            style={{ width: '100%', height: '20vh', objectFit: 'contain' }}
            src={`data:image/jpeg;base64,${pet.image}`}
            alt={pet.name}
          />
        </Col>
        {/* Pet details */}
        <Col md={9}>
          <Card.Body style={{ overflowY: 'auto', maxHeight: '20vh' }}>
            <Card.Title><h1>{pet.name}</h1></Card.Title>
            <Card.Text><strong>Species:</strong> {pet.specie}</Card.Text>
            <Card.Text><strong>Breed:</strong> {pet.breed}</Card.Text>
            <Card.Text><strong>Birthdate:</strong> {formatDate(pet.birthdate)}</Card.Text>
            <Card.Text><strong>Gender:</strong> {pet.gender}</Card.Text>
            <Card.Text><strong>Vaccinated:</strong> {pet.vaccination ? 'Yes' : 'No'}</Card.Text>
            <Card.Text><strong>Released:</strong> {formatDateTime(pet.releaseTimeStamp)}</Card.Text>
            {/* Other details as needed */}
          </Card.Body>
        </Col>
      </Row>
    </Card>
  );
};

export default PetCard;