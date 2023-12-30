import React, {useEffect, useState} from "react";
import {Container, Pagination} from "react-bootstrap";
import { FaSearch, FaFilter } from "react-icons/fa";
import StaffNavbar from "../../Components/staff/StaffNavbar";
import PetCard from "../../Components/staff/PetCard";
import {PetDTO} from "../../DTO/PetDTO";
import PetGridSearch from "../../Components/searchAndFilter/PetGridSearch";
import { httpRequest } from '../../Controller/HttpProxy';
const PETS_PER_PAGE = 20;

const StaffHomePage = () => {
  const [pets, setPets] = useState<PetDTO[]>([]);
  const [currentPage, setCurrentPage] = useState(1);

  const totalPages = Math.ceil(pets.length / PETS_PER_PAGE);

  const handlePageChange = (pageNumber: number) => {
    setCurrentPage(pageNumber);
  };

  const petsToShow = pets.slice(
    (currentPage - 1) * PETS_PER_PAGE,
    currentPage * PETS_PER_PAGE
  );

  useEffect(() => {

    const dto ={

    }

    const fetchPets = async () => {   
      
      await httpRequest("POST","staff/search",dto).then((response) => {
         const responseData = response.data;
        console.log(responseData);
        setPets(responseData);
        
      })
      .catch((error) => {
        console.log(error)
        alert(error.response.message)
      });
    };

    fetchPets();

  }, []);

 
  return (
    <Container style={{backgroundColor: "white", color: "blue", padding: "20px"}}>
      <StaffNavbar/>
      <div style={{margin: "20px 0", display: "flex", justifyContent: "center", alignItems: "center"}}>
        <input
          type="text"
          placeholder="Search by name"
          style={{
            padding: "10px",
            borderRadius: "5px",
            border: "1px solid blue",
            width: "300px"
          }}
        />
        <button style={{background: "none", border: "none", cursor: "pointer"}}>
          <FaSearch style={{color: "blue"}}/>
        </button>
        <div style={{width: "30px", height: "30px"}}></div>
        <input
          type="text"
          placeholder="Filter by specie"
          style={{
            padding: "10px",
            borderRadius: "5px",
            border: "1px solid blue",
          }}
        />
        <button style={{background: "none", border: "none", cursor: "pointer"}}>
          <FaFilter style={{color: "blue"}}/>
        </button>
      </div>
     <PetGridSearch searchResult={pets}/>
      
      <Pagination>
        {[...Array(totalPages).keys()].map((page) => (
          <Pagination.Item
            key={page + 1}
            active={page + 1 === currentPage}
            onClick={() => handlePageChange(page + 1)}
          >
            {page + 1}
          </Pagination.Item>
        ))}
      </Pagination>
    </Container>
  );
};

export default StaffHomePage;