package com.example.Backend.service.searchAndFilter;

import com.example.Backend.DAO.ShelterDAO;
import com.example.Backend.DTO.SearchAndFilter.AdminSearchAndFilterDTO;
import com.example.Backend.model.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSearchAndFilterService {
    @Autowired
    private ShelterDAO shelterDAO;

    public List<Shelter> searchForShelter(AdminSearchAndFilterDTO adminSearchAndFilterDTO){
        ShelterQueryBuilder query=new ShelterQueryBuilder();
        if(adminSearchAndFilterDTO.getShelterName()!=null) query.nameCriteria(adminSearchAndFilterDTO.getShelterName());
        if(adminSearchAndFilterDTO.getCountry()!=null) query.countryCriteria(adminSearchAndFilterDTO.getCountry());
        if(adminSearchAndFilterDTO.getCity()!=null) query.cityCriteria(adminSearchAndFilterDTO.getCity());
        System.out.println(query.toString());
        return shelterDAO.getShelterByQuery(query.build());
    }
    public Shelter viewShelterProfile(int shelterId){
        return shelterDAO.getShelterById(shelterId);
    }
}
