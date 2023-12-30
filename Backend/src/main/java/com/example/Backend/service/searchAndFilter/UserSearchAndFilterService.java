package com.example.Backend.service.searchAndFilter;

import com.example.Backend.DAO.Pet.PetDAO;
import com.example.Backend.DAO.Pet.PetSummaryDAO;
import com.example.Backend.DTO.SearchAndFilter.UserSearchAndFilterDTO;
import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.PetSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSearchAndFilterService {
    @Autowired
    private PetSummaryDAO petSummaryDAO;
    @Autowired
    PetDAO petDAO;

    private static final int PAGE_SIZE = 20;
    
    public List<PetSummary> searchAndFilter(UserSearchAndFilterDTO userSearchAndFilterDTO){
        PetQueryBuilder query=new PetQueryBuilder(userSearchAndFilterDTO.getPageNumber(),PAGE_SIZE);
        if(userSearchAndFilterDTO.isHouseTrained()) query.isHouseTrainedCriteria(userSearchAndFilterDTO.isHouseTrained());
        if(userSearchAndFilterDTO.isSterilized()) query.isSterilizedCriteria(userSearchAndFilterDTO.isSterilized());
        if(userSearchAndFilterDTO.isVaccinated()) query.isVaccinatedCriteria(userSearchAndFilterDTO.isVaccinated());
        if(userSearchAndFilterDTO.getBreed()!=null) query.breedCriteria(userSearchAndFilterDTO.getBreed());
        if(userSearchAndFilterDTO.getCountry()!=null) query.countryCriteria(userSearchAndFilterDTO.getCountry());
        if(userSearchAndFilterDTO.getCity()!=null) query.cityCriteria(userSearchAndFilterDTO.getCity());
        if(userSearchAndFilterDTO.getSpecies()!=null) query.speciesCriteria(userSearchAndFilterDTO.getSpecies());
        if(userSearchAndFilterDTO.getGender()!=null) query.genderCriteria(userSearchAndFilterDTO.getGender());
        query.endSelect();
        if(userSearchAndFilterDTO.getSortCriteria()!=null) query.sortCriteria(userSearchAndFilterDTO.getSortCriteria(), userSearchAndFilterDTO.getOrder());

        return petSummaryDAO.getPetSummaryByQuery(query.build());

    }

    public Pet viewPet(int perId){
        return petDAO.getById(perId);
    }

}
