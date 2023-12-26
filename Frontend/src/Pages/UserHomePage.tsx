import {useState} from "react";
import Navbar from "../Compnents/user/navbar.tsx";
import "../Compnents/user/navbar.css"

const UserHomePage = () => {
    const speciesOptions = [
        { value: 'dog', label: 'Dog' },
        { value: 'cat', label: 'Cat' },
        // Add more options as needed
    ];
    const locationOptions = [
        { value: 'location1', label: 'Location 1' },
        { value: 'location2', label: 'Location 2' },
        {value: 'Egypt', label: 'Egypt'},
        // Add more options as needed
    ];
    const [species, setSpecies] = useState('');
    const [location, setLocation] = useState('');

    const handleSpeciesSearch = (event:any) => {
        setSpecies(event.target.value);
        // Logic to handle species search
    };

    const handleLocationSearch = (event:any) => {
        setLocation(event.target.value);
        // Logic to handle location search
    };

    const handleSearch = () => {
        console.log(species,location);
        // Logic to set search for specific animal

    };
    return (
        <div className="content">
        <Navbar/>
        <div className="home-page">
            <div className="search-component">
                <div className="search-section">
                    <select
                        className="select-bar"
                        value={species}
                        onChange={handleSpeciesSearch}
                    >
                        <option value="" disabled hidden>Select Species</option>
                        {speciesOptions.map((option) => (
                            <option key={option.value} value={option.value}>
                                {option.label}
                            </option>
                        ))}
                    </select>
                    <select
                        className="select-bar"
                        value={location}
                        onChange={handleLocationSearch}
                    >
                        <option value="" disabled hidden>Select Location</option>
                        {locationOptions.map((option) => (
                            <option key={option.value} value={option.value}>
                                {option.label}
                            </option>
                        ))}
                    </select>
                    <button className="search-button" onClick={()=>handleSearch() }><i className="fa fa-search"></i></button>
                </div>
            </div>
            <div className="shortcut-buttons-container">
                <button className="shortcut-buttons" onClick={() => handleSearch()}>	🐶</button>
                <button className="shortcut-buttons" onClick={() => handleSearch()}>&#128008;</button>
                <button className="shortcut-buttons" onClick={() => handleSearch()}>&#128038;</button>

            </div>
        </div>
        </div>
    );
}

export default UserHomePage;