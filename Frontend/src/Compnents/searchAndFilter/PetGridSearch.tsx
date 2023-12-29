// @ts-ignore
import React from 'react'
import  './PetGridSearch.css';

const PetGridSearch = () => {
    const pets:Array<any> = [
            { image: 'src\\assets\\img.png', name: 'Fluffy', description: 'A fluffy white cat.' },
            { image: 'src\\assets\\img.png', name: 'Buddy', description: 'A playful dog.' },
            { image: 'src\\assets\\img.png', name: 'Fluffy', description: 'A fluffy white cat.' },
            { image: 'src\\assets\\img.png', name: 'Whiskers', description: 'A curious kitten.' },
            { image: 'src\\assets\\img.png', name: 'Rex', description: 'A loyal German Shepherd.' },
            { image: 'src\\assets\\img.png', name: 'Rex', description: 'A loyal German Shepherd.' },
    ];
    return (
        <div className="pet-grid">
            {pets.map((pet) => (
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