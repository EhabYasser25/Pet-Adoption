import { useState, useEffect } from 'react';
import './ShelterList.css';
import './AdminShelter.css'
import '../../DTO/shelter-type'
import AdminShelter from './AdminShelter';
import { httpRequest } from '../../Controller/HttpProxy';

const SheltersList = () => {
  const [shelters, setShelters] = useState<ShelterType[]>([]);
  const [currentPage, setCurrentPage] = useState(1);
  const sheltersPerPage = 13;

  useEffect(() => {
    // Fetch shelters
    const fetchShelters = async () => {
      const response = await httpRequest("GET", '/admin/shelters').then ((response) => {
        setShelters(response.data);
      }).catch((error) => {
        console.log(error)
      })
      
    };

    fetchShelters();
  }, []);

  const updateShelterInList = (updatedShelter: ShelterType) => {
    setShelters(prevShelters =>
      prevShelters.map(shelter =>
        shelter.id === updatedShelter.id ? updatedShelter : shelter
      )
    );
  };

  const handleDeleteShelter = (shelterId: string) => {
    setShelters(prevShelters => prevShelters.filter(shelter => shelter.id !== shelterId));
  };

  // Get current shelters
  const indexOfLastShelter = currentPage * sheltersPerPage;
  const indexOfFirstShelter = indexOfLastShelter - sheltersPerPage;
  const currentShelters = shelters.slice(indexOfFirstShelter, indexOfLastShelter);

  // Change page
  const paginate = (pageNumber: number) => setCurrentPage(pageNumber);

  // Total number of pages
  const pageNumbers = [];
  for (let i = 1; i <= Math.ceil(shelters.length / sheltersPerPage); i++) {
    pageNumbers.push(i);
  }

 return (
    <div>
      <div className="admin-shelters-grid">
        {currentShelters.map((shelter, index) => (
          <AdminShelter
            key={`${shelter.id}-${index}`}
            {...shelter}
            onEdit={updateShelterInList}
            onDelete={handleDeleteShelter} // Pass the delete function to AdminShelter
          />
        ))}
      </div>

      <div className="admin-pagination">
        {pageNumbers.map(number => (
          <button
            key={number}
            className={number === currentPage ? 'active' : ''}
            onClick={() => paginate(number)}
          >
            {number}
          </button>
        ))}
      </div>
    </div>
  );
};

export default SheltersList;
