// Pagination.js
import React from 'react';
import "./Pagination.css"

const Pagination = ({ currentPage,currPageSize, onPageChange }:{ currentPage:any, currPageSize:any,onPageChange:any}) => {
  
  return (
    <div >
      
        {currentPage > 1 && (
            <button onClick={() => onPageChange(currentPage - 1)} className={'active'}>
            {"<"}
            </button>
        )}
        <button  className={'buttonSmall'} disabled={true}>
          {currentPage}
        </button>
        {currPageSize == 10 && (
        <button  onClick={() => onPageChange(currentPage+1)} className={'active'}>
            {">"}
        </button>
        )}
      
    </div>
  );
};

export default Pagination;
