package com.example.Backend.DTO.SearchAndFilter;

import lombok.Data;

@Data
public class AdminSearchAndFilterDTO {
    private String shelterName;
    private String city;
    private String country;

    private int pageNumber;
}
