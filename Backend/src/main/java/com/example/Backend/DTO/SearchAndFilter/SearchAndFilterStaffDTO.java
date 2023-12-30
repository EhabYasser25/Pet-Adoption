package com.example.Backend.DTO.SearchAndFilter;

import lombok.Data;

@Data
public class SearchAndFilterStaffDTO {
        private String species;
        private String breed ;
        private String name;

        private int pageNumber;
}

