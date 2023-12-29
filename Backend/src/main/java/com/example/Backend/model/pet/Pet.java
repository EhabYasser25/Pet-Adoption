package com.example.Backend.model.pet;

import com.example.Backend.emum.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Pet {
    private int id;
    private int speciesId;
    private String name;
    private Date birthDate;
    private Gender gender;
    private boolean isSterilized;
    private boolean isVaccinated;
    private boolean isHouseTrained;

    private String image;
    private Timestamp releaseTimeStamp;

    List<Attachment> attachments;
    private String breed;

    public Pet(int id, int typeId, String name, Date birthDate,
               Gender gender, boolean isVaccinated, String image, Timestamp releaseTimeStamp, boolean isSterilized, boolean isHouseTrained,String breed) {
        this.id = id;
        this.speciesId = typeId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.isVaccinated = isVaccinated;
        this.image = image;
        this.releaseTimeStamp = releaseTimeStamp;
        this.isSterilized = isSterilized;
        this.isHouseTrained = isHouseTrained;
        this.breed=breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isVaccination() {
        return isVaccinated;
    }

    public void setVaccination(boolean vaccination) {
        this.isVaccinated = vaccination;
    }

    public Timestamp getReleaseTimeStamp() {
        return releaseTimeStamp;
    }

    public void setReleaseTimeStamp(Timestamp releaseTimeStamp) {
        this.releaseTimeStamp = releaseTimeStamp;
    }
}
