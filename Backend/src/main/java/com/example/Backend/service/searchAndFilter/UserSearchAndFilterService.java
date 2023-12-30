package com.example.Backend.service.searchAndFilter;

import com.example.Backend.DAO.Pet.PetSummaryDAO;
import com.example.Backend.DTO.SearchAndFilter.SearchAndFilterUserDTO;
import com.example.Backend.enums.Gender;
import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.PetSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserSearchAndFilterService {
    @Autowired
    private PetSummaryDAO petSummaryDAO;

    private static final int PAGE_SIZE = 20;
    
    Page<PetSummary> searchAndFilter(SearchAndFilterUserDTO searchAndFilterUserDTO){
        PetQueryBuilder query=new PetQueryBuilder(searchAndFilterUserDTO.getPageNumber(),PAGE_SIZE);
        if(searchAndFilterUserDTO.isHouseTrained()) query.isHouseTrainedCriteria(searchAndFilterUserDTO.isHouseTrained());
        if(searchAndFilterUserDTO.isSterilized()) query.isSterilizedCriteria(searchAndFilterUserDTO.isSterilized());
        if(searchAndFilterUserDTO.isVaccinated()) query.isVaccinatedCriteria(searchAndFilterUserDTO.isVaccinated());
        if(searchAndFilterUserDTO.getBreed()!=null) query.breedCriteria(searchAndFilterUserDTO.getBreed());
        if(searchAndFilterUserDTO.getCountry()!=null) query.countryCriteria(searchAndFilterUserDTO.getCountry());
        if(searchAndFilterUserDTO.getCity()!=null) query.cityCriteria(searchAndFilterUserDTO.getCity());
        if(searchAndFilterUserDTO.getSpecies()!=null) query.speciesCriteria(searchAndFilterUserDTO.getSpecies());
        if(searchAndFilterUserDTO.getGender()!=null) query.genderCriteria(searchAndFilterUserDTO.getGender());

        petSummaryDAO.getPetSummaryByQuery(query.build());


        return null;
    }
    Page<PetSummary> sortPets(String sortCriteria){

        return null;
    }
    Pet viewPet(int perId){
        return null;
    }

}
