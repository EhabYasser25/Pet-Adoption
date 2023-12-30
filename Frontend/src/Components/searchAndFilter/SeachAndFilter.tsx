import React from 'react'
import {useState, useEffect} from "react";
import "./SearchAndFilter.css";
import Pagination from './Pagination';
import { httpRequest } from '../../Controller/HttpProxy';
const SeachAndFilter = ({
  updateSearchResult,
  species,
  city,
  country
}: {
  updateSearchResult: any;
  species: any;
  city: any;
  country: any;
}) => {

  const [breed, setBreed] = useState("");
  const [gender, setGender] = useState("");
  const [isVaccinated, setIsVaccinated] = useState(false);
  const [isSterilized, setIsSterilized] = useState(false);
  const [isHouseTrained, setisHouseTrained] = useState(false);
  const [sortCriteria, setSortCriteria] = useState("")
  const [currentPage, setCurrentPage] = useState(0);
  const [currPageSize, setCurrPageSize] = useState(10);
  const [sortCriteriaOrder, setsortCriteriaOrder] = useState("");
  const [breedsList, setBreedsList] = useState([]);
  useEffect(() => {

    const fetchBreeds = async () => {    
      await httpRequest("GET","fetch/breed",null,{species:species}).then((response) => {
         const responseData = response.data;
        console.log(responseData);
        setBreedsList(responseData);
        setCurrPageSize(responseData.length);
      })
      .catch((error) => {
        console.log(error)
        alert(error.response.message)
      });
    };

    fetchBreeds();

  }, []);
    
  const onPageChange = (pageNumber:any) => {
      setCurrentPage(pageNumber);
      applySearchAndFilterCriteria();
    };

  
  
  const pets:Array<any> = [
    { image: 'src\\assets\\img.png', name: 'Fluffy', description: 'A fluffy white cat.' },
    { image: 'src\\assets\\img.png', name: 'Buddy', description: 'A playful dog.' },
    { image: 'src\\assets\\img.png', name: 'Fluffy', description: 'A fluffy white cat.' },
    // { image: 'src\\assets\\img.png', name: 'Whiskers', description: 'A curious kitten.' },
    // { image: 'src\\assets\\img.png', name: 'Rex', description: 'A loyal German Shepherd.' },
    // { image: 'src\\assets\\img.png', name: 'Rex', description: 'A loyal German Shepherd.' },
];
  const applySearchAndFilterCriteria = async () => {
    

    const SearchAndFilterUserDTO = {
      species:species,
      breed:breed,
      gender:gender,
      city:city,
      country:country,
      isSterilized:isSterilized,
      isVaccinated:isVaccinated,
      isHouseTrained:isHouseTrained,
      pageNumber:currentPage,
      sortCriteria:sortCriteria,
      sortCriteriaOrder:sortCriteriaOrder,

    };

    console.log("sent data ", SearchAndFilterUserDTO);

    await httpRequest('POST', 'user/search', SearchAndFilterUserDTO)
      .then((response) => {
        const responseData = response.data
        console.log("returned data");
        console.log(responseData);
        updateSearchResult(responseData);
        setCurrPageSize(responseData.length);
        console.log(responseData)
      })
      .catch((error) => {
        console.log(error)
        alert(error.response.data.message)
      });
 

    

    // sent DTO to backend

    // get result

    //update search result
    
    // updateSearchResult(pets)
    
    // update the currPageSize
  };

  // we need to get all data when page loads
  const breadOptions = [
    'Dog' ,'Cat' , 'Cet' 
  ];
 
  return (
    <aside className="search-main-content" >
    <div className="criteria-box">
      <h5 className="select-title">Breed</h5>
      <select className="select" aria-label="Default select example" onChange={(event:any) => {
        setBreed( event.target.value);
  }}>
        <option value="" selected>Sort by...</option>
        {breedsList.map((option) => (
          <option value={option}>
           {option}
            </option>
    ))}
      </select>
      </div>
      <div className="criteria-box">
      <h5 className="select-title">Sort</h5>
      <select className="select" aria-label="Default select example" onChange={(event:any) => {
          if (event.target.value == "age1"){
            setsortCriteriaOrder("ASC");
            setSortCriteria("age");
          } 
          if (event.target.value == "age2"){
            setsortCriteriaOrder("DESC");
            setSortCriteria("age");
          } 
          else{
            setSortCriteria(event.target.value);
          }
          
        }}>
        <option value="" selected>Sort by...</option>
        <option value="age1">Age ascending</option>
        <option value="age2">Age descending</option>
        <option value="newest">Most recent</option>
      </select>
      </div>
      <div className="criteria-box">
      <h5 className="select-title">Gender</h5>
      <select  className="select" aria-label="Default select example" onChange={(event:any) => {
          setGender(event.target.value);
        }}>
        <option value="" selected>filter by gender</option>
        <option value="FEMALE">FEMALE</option>
        <option value="MALE">MALE</option>
      </select>
      </div>
      <div className="criteria-box">
      <h5 className="select-title">Filter criteria</h5>
      <div className="checkbox-container">
        <input
          type="checkbox"
          checked={isHouseTrained}
          onChange={() => setisHouseTrained(!isHouseTrained)}
          className="checkbox-input"
        />
        <div className="checkbox-label">House-trained</div>
      </div>
      <div className="checkbox-container">
        <input
          type="checkbox"
          checked={isVaccinated}
          onChange={() => setIsVaccinated(!isVaccinated)}
          className="checkbox-input"
        />
        <div className="checkbox-label">vaccinated</div>
      </div>
      <div className="checkbox-container">
        <input
          type="checkbox"
          checked={isSterilized}
          onChange={() => setIsSterilized(!isSterilized)}
          className="checkbox-input"
        />
        <div className="checkbox-label">sterilized</div>
      </div>
      
    </div> 
    <button className="button" onClick={applySearchAndFilterCriteria }>
      Apply
    </button>
    <Pagination currentPage={currentPage} currPageSize={currPageSize} onPageChange={onPageChange} />
  </aside>
  )
}

export default SeachAndFilter