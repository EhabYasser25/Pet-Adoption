package com.example.Backend.DTO.registrationAndAuth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffMemberDTO {

    RegistrationRequestDTO staffDetails;
    private int shelterId;
}
