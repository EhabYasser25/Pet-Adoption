package com.example.Backend.service.searchAndFilter;

import com.example.Backend.enums.Gender;

public class ShelterQueryBuilder {
    StringBuilder query;

    public ShelterQueryBuilder() {
        this.query = new StringBuilder("SELECT * FROM shelter WHERE ");
    }

    void cityCriteria(String city) {
        query.append("location_city = '").append(city).append("' AND ");
    }

    void countryCriteria(String country) {
        query.append("location_country = '").append(country).append("' AND ");
    }

    void nameCriteria(String name) {
        query.append("name = '").append(name).append("' AND ");
    }


    public String build() {
        query.delete(query.length() - 4, query.length());
        return query.toString();
    }
}
