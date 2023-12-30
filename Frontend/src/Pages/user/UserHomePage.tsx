import {useState, useEffect} from "react";
import {Link, useNavigate} from "react-router-dom";
import Navbar from "../../Components/user/Navbar";
import { httpRequest } from "../../Controller/HttpProxy";
import './UserHomeStyle.css'
const UserHomePage = () => {
    const speciesOptions = [
        { value: 'dog', label: 'Dog' },
        { value: 'cat', label: 'Cat' },
        { value: 'cet', label: 'Cet' },
        { value: 'Dat', label: 'Cat' },
        // Add more options as needed
    ];
    const locationOptions = [
        { value: 'location1', label: 'Location 1' },
        { value: 'location2', label: 'Location 2' },
        {value: 'Egypt', label: 'Egypt'},
        // Add more options as needed
    ];
    const [species, setSpecies] = useState([]);
    const [cities, setCities] = useState([]);
    const [ countries, setCountries] = useState([]);

    const [specie, setSpecie] = useState([]);
    const [city, setCity] = useState([]);
    const [ country, setCountry] = useState([]);
    const [ isCountryNotSelected, setIsCountryNotSelected] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {

        const fetchCountries = async () => {    
          httpRequest("GET","fetch/countries").then((response) => {
            const responseData = response.data
            setCountries(responseData);
          })
          .catch((error) => {
            console.log(error)
            alert(error.response.data.message)
          });
        };

        const fetchSpecies = async () => {    
            httpRequest("GET","fetch/species").then((response) => {
              const responseData = response.data
              setCities(responseData);
            })
            .catch((error) => {
              console.log(error)
              alert(error.response.data.message)
            });
          };
    
        fetchCountries();
        fetchSpecies();

      }, []);
    

    const handleCountrySelection = (event:any) => {
        setCountry(event.target.value);
        const fetchCities = async () => {   
            setIsCountryNotSelected(true); 
            await httpRequest("GET","fetch/cities",null,country).then((response) => {
              const responseData = response.data
              setCities(responseData);
              setIsCountryNotSelected(false);
            })
            .catch((error) => {
              console.log(error)
              alert(error.response.data.message)
            });
          };
    
        fetchCities();
        // Logic to handle species search
    };
    const handleSpeciesSearch = (event:any) => {
        setSpecies(event.target.value);
        // Logic to handle species search
    };

    const handleCitiesSearch = (event:any) => {
        setCities(event.target.value);
        // Logic to handle location search

        // 
    };
    const handleLocationSearch = (event:any) => {
        setCities(event.target.value);
        // Logic to handle location search

        // 
    };

    

    const handleSearch = () => {
        console.log(species,countries,cities);
        // Logic to set search for specific animal
        const searchDTO ={
            species:species,
            country:country,
            city:city,
        };
        httpRequest("POST","user/search",searchDTO).then((response) => {
            const responseData = response.data;
            const passedDTO ={
                path: '/userSearch',
                initialSearchResult: responseData,
                species:specie,
                country:country,
                city:city,

            };
            navigate('/userSearch',{ state: passedDTO });
            
          })
          .catch((error) => {
            console.log(error)
            alert(error.response.data.message)
          });
        };

        // Navigate to SearchAndFilterScreen

   

    return (
        <div className="content">
        <Navbar/>
        <div className="home-page">
            <div className="search-component">
                <div className="search-section">
                    <select
                        className="select-bar"
                        value={species}
                        onChange={(event:any) => {
                            setSpecie(event.target.value);
                          }}
                    >
                        <option value="" >Select Species</option>
                        {species.map((option) => (
                            <option key={option.value} value={option.value}>
                                {option.label}
                            </option>
                        ))}
                    </select>
                    <select
                        className="select-bar"
                        onChange={handleCountrySelection}
                    >
                        <option value="species" >Select Country</option>
                        {countries.map((option) => (
                            <option key={option.value} value={option.value}>
                                {option.label}
                            </option>
                        ))}
                    </select>
                    <select
                        className="select-bar"
                        onChange={(event:any) => {
                            setCity(event.target.value);
                          }}
                        disabled={isCountryNotSelected}
                    >
                        <option value="" >Select City</option>
                        {cities.map((option) => (
                            <option key={option.value} value={option.value}>
                                {option.label}
                            </option>
                        ))}
                    </select>
                    <button className="search-button" onClick={()=>handleSearch() }><i className="fa fa-search"></i></button>
                </div>
            </div>
            <div className="shortcut-buttons-container">
                {/* <button className="shortcut-buttons" onClick={() => handleSearch()}>	🐶</button>
                <button className="shortcut-buttons" onClick={() => handleSearch()}>&#128008;</button>
                <button className="shortcut-buttons" onClick={() => handleSearch()}>&#128038;</button> */}

            </div>
        </div>
        </div>
    );
}

export default UserHomePage;