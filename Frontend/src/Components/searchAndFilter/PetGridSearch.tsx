// @ts-ignore
import React from 'react'
import  './PetGridSearch.css';
import { useState, useEffect } from 'react';
import Pagination from './Pagination';
import EditPetProfileModal from './PetProfile';
import { httpRequest } from '../../Controller/HttpProxy';
import { Alert } from 'react-bootstrap';
import EditPetForm from '../staff/EditPetForm';
const PetGridSearch = ( { searchResult }: { searchResult: any } ) => {
   
    const [isEditModalShown, setIsEditModalShown] = useState(false);

    
    const handlePetClick = async (pet:any) => {
        //call the backend and get all of the profile then turn the modal on
        setIsEditModalShown(true);

        


    };

    const editProfile = async(pet:any)=>{

        //call backend
        
        const petData = {
            name:pet.name,
            isVaccinated:pet.isVaccinated,
            isSterilized:pet.isSterilized,
            isHouseTrained:pet.isHouseTrained,
        }
        httpRequest('POST', '/staff/edit/pet', petData)
        .then((response) => {
          console.log(response.data)
          alert(response.data)
        })
        .catch((error) => {
          console.log(error.response.data.message)
        })

        setIsEditModalShown(false);


    }

    return (
        <div className="pet-grid">
            {searchResult.map((pet:any) => (
                     <div className="pet-content" onClick={() => handlePetClick(pet)}>
                         <img src={pet.image} alt={pet.name} />
                         <h3>{pet.name}</h3>
                         <p>{pet.description}</p>
                         <EditPetForm petDTO={pet}/>
                     </div>
            ))}
           
        </div>
        
        
    );
};

export default PetGridSearch