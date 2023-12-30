package com.example.Backend.model.pet;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private int id;
    private String species;
    private String name;
    private Date birthDate;
    private String gender;
    private boolean isSterilized;
    private boolean isVaccinated;
    private boolean isHouseTrained;
    private byte[] image;
    private Timestamp releaseTimeStamp;
    List<Attachment> attachments;
    private String breed;
    private int shelterId;
    private String shelterLocationCity;
    private String shelterLocationCountry;
    private String description;
}
