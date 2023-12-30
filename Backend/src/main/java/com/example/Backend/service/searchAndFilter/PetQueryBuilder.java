package com.example.Backend.service.searchAndFilter;


import com.example.Backend.enums.Gender;

public class PetQueryBuilder {

    StringBuilder query;
    int pageNumber;
    int pageSize;

    public PetQueryBuilder(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.query = new StringBuilder("SELECT image,description,name FROM pet WHERE ");
    }

    void isVaccinatedCriteria(boolean isVaccinated) {
        query.append("is_vaccinated =").append(isVaccinated).append(" AND ");
    }

    void isHouseTrainedCriteria(boolean isHouseTrained) {
        query.append("is_house_trained =").append(isHouseTrained).append(" AND ");
    }

    void isSterilizedCriteria(boolean isSterilized) {
        query.append("is_sterilized =").append(isSterilized).append(" AND ");
    }

    void breedCriteria(String breed) {
        query.append("breed=").append(breed).append(" AND ");
    }

    void speciesCriteria(String species) {
        query.append("species =").append(species).append(" AND ");
    }

    void cityCriteria(String city) {
        query.append("city =").append(city).append(" AND ");
    }

    void countryCriteria(String country) {
        query.append("country =").append(country).append(" AND ");
    }

    void genderCriteria(Gender gender) {
        query.append("gender =").append(gender).append(" AND ");
    }

    void shelterIDCriteria(int shelterId) {
        query.append("shelter_id =").append(shelterId).append(" AND ");
    }

    void nameCriteria(String name) {
        query.append("name =").append(name).append(" AND ");
    }
    void sortCriteria(String sortCriteria,String order){
        query.append("ORDER BY ").append(sortCriteria).append(" ").append(order);
    }
    void endSelect(){
        query.delete(query.length() - 4, query.length());
    }

    public String build() {
        query.append("LIMIT ").append(this.pageSize).append(" OFFSET ").append(pageNumber);
        return query.toString();
    }
}
