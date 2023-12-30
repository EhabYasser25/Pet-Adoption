
import PetGridSearch from "../searchAndFilter/PetGridSearch";
import SeachAndFilter from "../searchAndFilter/SeachAndFilter";
import "./SearchAndFilter.css";
import Navbar from "./Navbar";
import {useEffect, useState} from "react";
import { useLocation } from 'react-router-dom';
const SearchScreen = () => {

    const location = useLocation();
  const initialSearchResult = location.state;
  console.log(initialSearchResult);
    console.log("initial search result",initialSearchResult);
    const [searchResult, setSearchResult] = useState( initialSearchResult.initialSearchResult
      /*  [
            { image: 'src\\assets\\img.png', name: 'Fluffy', description: 'A fluffy white cat.' },
            { image: 'src\\assets\\img.png', name: 'Buddy', description: 'A playful dog.' },
            { image: 'src\\assets\\img.png', name: 'Fluffy', description: 'A fluffy white cat.' },
            { image: 'src\\assets\\img.png', name: 'Whiskers', description: 'A curious kitten.' },
            { image: 'src\\assets\\img.png', name: 'Rex', description: 'A loyal German Shepherd.' },
            { image: 'src\\assets\\img.png', name: 'Rex', description: 'A loyal German Shepherd.' },
        ]*/
    );

    // initialize it with what is passed to this screen too
    const [searchAndFilterDTO, setSearchAndFilterDTO] = useState();

    // Function in SearchAndFilter that updates the search result
  const updateSearchResult = (result:any) => {
    setSearchResult(result);
  };

  useEffect(() => {
    console.log(initialSearchResult);
  },[]);

  

  const pets:Array<any> = [
    { image: 'src\\assets\\img.png', name: 'Fluffy', description: 'A fluffy white cat.' },
    { image: 'src\\assets\\img.png', name: 'Buddy', description: 'A playful dog.' },
    { image: 'src\\assets\\img.png', name: 'Fluffy', description: 'A fluffy white cat.' },
    { image: 'src\\assets\\img.png', name: 'Whiskers', description: 'A curious kitten.' },
    { image: 'src\\assets\\img.png', name: 'Rex', description: 'A loyal German Shepherd.' },
    { image: 'src\\assets\\img.png', name: 'Rex', description: 'A loyal German Shepherd.' },
];
    return (
    <div >
        <Navbar/>
        <SeachAndFilter updateSearchResult={updateSearchResult} species={initialSearchResult.species}
            city={initialSearchResult.city}
            country={initialSearchResult.country}/>
        <PetGridSearch searchResult={searchResult}   />
    </div>
    )
  }
  
  export default SearchScreen;