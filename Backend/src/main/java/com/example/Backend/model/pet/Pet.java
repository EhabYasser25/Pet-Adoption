package com.example.Backend.model.pet;

import com.example.Backend.enums.Gender;
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
    private int shelterId;
    private int shelterLocationCity;
    private int shelterLocationCountry;

}
