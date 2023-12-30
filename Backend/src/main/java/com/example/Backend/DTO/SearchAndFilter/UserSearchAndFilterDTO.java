package com.example.Backend.DTO.SearchAndFilter;

import com.example.Backend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.WithBy;

@Data
@AllArgsConstructor
@Builder
public class UserSearchAndFilterDTO {


    private String species;
    private String breed;
    private String gender;
    private String city;
    private String country;
    private boolean isSterilized;
    private boolean isVaccinated;
    private boolean isHouseTrained;


    private int pageNumber;

    private String sortCriteria;
    private String order="DESC";


}
