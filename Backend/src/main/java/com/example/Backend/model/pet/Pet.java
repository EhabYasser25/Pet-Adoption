package com.example.Backend.model.pet;

import com.example.Backend.emum.Gender;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import javax.xml.crypto.Data;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
public class Pet {
    private int id;
    private int typeId;
    private String name;
    private Date birthDate;
    private Gender gender;
    private boolean vaccination;
    private Blob image; // TODO change if needed
    private Timestamp releaseTimeStamp;

    public Pet(int id, int typeId, String name, Date birthDate,
               Gender gender, boolean vaccination, Blob image, Timestamp releaseTimeStamp) {
        this.id = id;
        this.typeId = typeId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.vaccination = vaccination;
        this.image = image;
        this.releaseTimeStamp = releaseTimeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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
        return vaccination;
    }

    public void setVaccination(boolean vaccination) {
        this.vaccination = vaccination;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Timestamp getReleaseTimeStamp() {
        return releaseTimeStamp;
    }

    public void setReleaseTimeStamp(Timestamp releaseTimeStamp) {
        this.releaseTimeStamp = releaseTimeStamp;
    }
}
