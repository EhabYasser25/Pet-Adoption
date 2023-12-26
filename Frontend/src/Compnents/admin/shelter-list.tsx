import { useState, useEffect } from 'react';
import './shelter-list.css';
import './shetler.css'
import '../../DTO/shelter-type'
import AdminShelter from './shelter';

const SheltersList = () => {
    const [shelters, setShelters] = useState<ShelterType[]>([
        {
            id: '1',
            name: 'That is large dummy shelter name and yet not large enough',
            address: '123 Main St, City A and yet another dummy large address and still not enough',
        }, {
          id: '1',
          name: 'That is large dummy shelter name and yet not large enough',
          address: '123 Main St, City A and yet another dummy large address and still not enough',
        }, {
          id: '1',
          name: 'That is large dummy shelter name and yet not large enough',
          address: '123 Main St, City A and yet another dummy large address and still not enough',
        }, {
          id: '1',
          name: 'That is large dummy shelter name and yet not large enough',
          address: '123 Main St, City A and yet another dummy large address and still not enough',
        }, {
          id: '1',
          name: 'That is large dummy shelter name and yet not large enough',
          address: '123 Main St, City A and yet another dummy large address and still not enough',
        }, {
          id: '1',
          name: 'That is large dummy shelter name and yet not large enough',
          address: '123 Main St, City A and yet another dummy large address and still not enough',
        }, {
          id: '1',
          name: 'That is large dummy shelter name and yet not large enough',
          address: '123 Main St, City A and yet another dummy large address and still not enough',
        }, {
          id: '1',
          name: 'That is large dummy shelter name and yet not large enough',
          address: '123 Main St, City A and yet another dummy large address and still not enough',
        }, {
          id: '1',
          name: 'That is large dummy shelter name and yet not large enough',
          address: '123 Main St, City A and yet another dummy large address and still not enough',
        }, {
          id: '1',
          name: 'That is large dummy shelter name and yet not large enough',
          address: '123 Main St, City A and yet another dummy large address and still not enough',
        }, {
          id: '1',
          name: 'That is large dummy shelter name and yet not large enough',
          address: '123 Main St, City A and yet another dummy large address and still not enough',
        }, {
          id: '1',
          name: 'That is large dummy shelter name and yet not large enough',
          address: '123 Main St, City A and yet another dummy large address and still not enough',
        }, {
          id: '1',
          name: 'That is large dummy shelter name and yet not large enough',
          address: '123 Main St, City A and yet another dummy large address and still not enough',
        }, {
            id: '1',
            name: 'That is large dummy shelter name and yet not large enough',
            address: '123 Main St, City A and yet another dummy large address and still not enough',
        },

      ]);  const [currentPage, setCurrentPage] = useState(1);
  const sheltersPerPage = 13;

  useEffect(() => {
    // Fetch the list of shelters from the backend
    const fetchShelters = async () => {
      // Replace with your actual fetch call
      const response = await fetch('/api/shelters');
      const data = await response.json();
      setShelters(data);
    };

    fetchShelters();
  }, []);

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
            key={`${shelter.id}-${index}`} // Unique key for each shelter
            {...shelter}
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
