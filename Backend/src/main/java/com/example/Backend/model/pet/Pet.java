package com.example.Backend.model.pet;

import com.example.Backend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class Pet {
    private int id;
    private int typeId;
    private String name;
    private Date birthDate;
    private Gender gender;
    private boolean isSterilized;
    private boolean isVaccinated;
    private boolean isHouseTrained;

    private Blob image; // TODO change if needed
    private Timestamp releaseTimeStamp;
}
