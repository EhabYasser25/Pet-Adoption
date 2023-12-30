// @ts-ignore
import React from 'react'
import  './PetGridSearch.css';
import { useState, useEffect } from 'react';


const PetGridSearch = ( { searchResult }: { searchResult: any } ) => {
   

    return (
        <div className="pet-grid">
            {searchResult.map((pet:any) => (
                     <div className="pet-content">
                         <img src={pet.image} alt={pet.name} />
                         <h3>{pet.name}</h3>
                         <p>{pet.description}</p>
                     </div>
            ))}
           
        </div>
        
    );
};

export default PetGridSearch