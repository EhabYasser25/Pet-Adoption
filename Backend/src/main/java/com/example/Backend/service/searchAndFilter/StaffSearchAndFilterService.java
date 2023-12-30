package com.example.Backend.service.searchAndFilter;

import com.example.Backend.DAO.Pet.PetDAO;
import com.example.Backend.DAO.Pet.PetSummaryDAO;
import com.example.Backend.DAO.user.StaffDAO;
import com.example.Backend.DAO.user.UserDAO;
import com.example.Backend.DTO.SearchAndFilter.StaffSearchAndFilterDTO;
import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.PetSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffSearchAndFilterService {
    @Autowired
    private PetSummaryDAO petSummaryDAO;
    @Autowired
    PetDAO petDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    StaffDAO staffDAO;

    private static final int PAGE_SIZE = 20;

    public List<PetSummary> searchAndFilter(StaffSearchAndFilterDTO staffSearchAndFilterDTO,String userName){
        int userId=userDAO.getUserIdByUserName(userName);
        PetQueryBuilder query=new PetQueryBuilder(staffSearchAndFilterDTO.getPageNumber(),PAGE_SIZE);
        query.shelterIDCriteria(staffDAO.getShelterIdByUserID(userId));
        if(staffSearchAndFilterDTO.getBreed()!=null) query.breedCriteria(staffSearchAndFilterDTO.getBreed());
        if(staffSearchAndFilterDTO.getSpecies()!=null) query.speciesCriteria(staffSearchAndFilterDTO.getSpecies());
        if(staffSearchAndFilterDTO.getName()!=null) query.nameCriteria(staffSearchAndFilterDTO.getName());
        query.endSelect();
        System.out.println(query);
        return petSummaryDAO.getPetSummaryByQuery(query.build());
    }

    public Pet viewPet(int perId){
        return petDAO.getById(perId);
    }
}
