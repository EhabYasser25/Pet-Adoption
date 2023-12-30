package com.example.Backend.service.searchAndFilter;

import com.example.Backend.DAO.Pet.PetDAO;
import com.example.Backend.DAO.Pet.PetSummaryDAO;
import com.example.Backend.DTO.SearchAndFilter.UserSearchAndFilterDTO;
import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.PetSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
        if(userSearchAndFilterDTO.getBreed()!=null && !userSearchAndFilterDTO.getBreed().equals("")) query.breedCriteria(userSearchAndFilterDTO.getBreed());
        if(userSearchAndFilterDTO.getCountry()!=null && !Objects.equals(userSearchAndFilterDTO.getCountry(), "")) query.countryCriteria(userSearchAndFilterDTO.getCountry());
        if(userSearchAndFilterDTO.getCity()!=null && !Objects.equals(userSearchAndFilterDTO.getCity(), "")) query.cityCriteria(userSearchAndFilterDTO.getCity());
        if(userSearchAndFilterDTO.getSpecies()!=null && !Objects.equals(userSearchAndFilterDTO.getSpecies(), "")) query.speciesCriteria(userSearchAndFilterDTO.getSpecies());
        if(userSearchAndFilterDTO.getGender()!=null && !Objects.equals(userSearchAndFilterDTO.getGender(), "")) query.genderCriteria(userSearchAndFilterDTO.getGender());
        query.endSelect();
        if(userSearchAndFilterDTO.getSortCriteria()!=null && !Objects.equals(userSearchAndFilterDTO.getSortCriteria(), "")) query.sortCriteria(userSearchAndFilterDTO.getSortCriteria(), userSearchAndFilterDTO.getOrder());

        List<PetSummary> result =petSummaryDAO.getPetSummaryByQuery(query.build());
        for(PetSummary pet:result){
            pet.setImageUrl(getPetImageUrl(pet.getId()));
        }
        return result;

    }
    private String getPetImageUrl(int petId) {
        // Replace this with your logic to generate image URLs
        return "http://localhost:8080/api/pet/img?id=" + petId;
    }

    public Pet viewPet(int perId){
        return petDAO.getById(perId);
    }

}
