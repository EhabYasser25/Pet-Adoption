import { useState, useEffect } from 'react';
import './ShelterList.css';
import './AdminShelter.css'
import '../../DTO/shelter-type'
import AdminShelter from './AdminShelter';
import { httpRequest } from '../../Controller/HttpProxy';

interface SheltersListProps {
  searchQuery: string;
  searchBy: string;
}

const SheltersList: React.FC<SheltersListProps> = ({ searchQuery, searchBy }) => {
  const [shelters, setShelters] = useState<ShelterType[]>([]);
  const [currentPage, setCurrentPage] = useState(1);
  const sheltersPerPage = 13;

  useEffect(() => {
    console.log(searchBy)
    console.log(searchQuery)
    // Define the function to fetch shelters based on search criteria
    const fetchShelters = async () => {
      let endpoint = '/admin/shelters';
      if (searchQuery) {
        endpoint += `?searchBy=${encodeURIComponent(searchBy)}&searchQuery=${encodeURIComponent(searchQuery)}`;
      }
      
      const response = await httpRequest("GET", endpoint);
      // Assuming your API returns an array of shelters
      setShelters(response.data);
    };

    fetchShelters();
  }, [searchQuery, searchBy]); // Re-fetch when search parameters change
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
