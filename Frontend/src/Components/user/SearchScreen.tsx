
import PetGridSearch from "../searchAndFilter/PetGridSearch";
import SeachAndFilter from "../searchAndFilter/SeachAndFilter";
import "./SearchAndFilter.css";
import Navbar from "./Navbar";
import {useState} from "react";
const SearchScreen = (/*{ initialSearchResult }:{ initialSearchResult :any}*/) => {

    const [searchResult, setSearchResult] = useState( /*initialSearchResult*/
        [
            { image: 'src\\assets\\img.png', name: 'Fluffy', description: 'A fluffy white cat.' },
            { image: 'src\\assets\\img.png', name: 'Buddy', description: 'A playful dog.' },
            { image: 'src\\assets\\img.png', name: 'Fluffy', description: 'A fluffy white cat.' },
            { image: 'src\\assets\\img.png', name: 'Whiskers', description: 'A curious kitten.' },
            { image: 'src\\assets\\img.png', name: 'Rex', description: 'A loyal German Shepherd.' },
            { image: 'src\\assets\\img.png', name: 'Rex', description: 'A loyal German Shepherd.' },
        ]
    );

    // initialize it with what is passed to this screen too
    const [searchAndFilterDTO, setSearchAndFilterDTO] = useState();

    // Function in SearchAndFilter that updates the search result
  const updateSearchResult = (result:any) => {
    setSearchResult(result);
  };

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
        <SeachAndFilter updateSearchResult={updateSearchResult} species={"cats"}
            city={"Alexandria"}
            country={"Egypt"}/>
        <PetGridSearch searchResult={searchResult}   />
    </div>
    )
  }
  
  export default SearchScreen;