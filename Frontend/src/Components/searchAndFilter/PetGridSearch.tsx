// @ts-ignore
import React from 'react'
import  './PetGridSearch.css';
import { useState, useEffect } from 'react';
import Pagination from './Pagination';
import EditPetProfileModal from './PetProfile';
const PetGridSearch = ( { searchResult }: { searchResult: any } ) => {
   
    const [isEditModalShown, setIsEditModalShown] = useState(false);

    
    const handlePetClick = (pet:any) => {
        //call the backend and get all of the profile then turn the modal on
        setIsEditModalShown(true);

    };

    const editProfile = async(dto:any)=>{

        //call backend
        

        setIsEditModalShown(false);


    }

    return (
        <div className="pet-grid">
            {searchResult.map((pet:any) => (
                     <div className="pet-content" onClick={() => handlePetClick(pet)}>
                         <img src={pet.image} alt={pet.name} />
                         <h3>{pet.name}</h3>
                         <p>{pet.description}</p>
                         <EditPetProfileModal show={isEditModalShown} pet={pet} onSave={editProfile}></EditPetProfileModal>
                     </div>
            ))}
           
        </div>
        
        
    );
};

export default PetGridSearch