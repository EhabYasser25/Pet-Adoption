package com.example.Backend.service.Admin;

import com.example.Backend.DAO.user.StaffDAO;
import com.example.Backend.DAO.user.UserDAO;
import com.example.Backend.DTO.registrationAndAuth.StaffMemberDTO;
import com.example.Backend.Error.GlobalException;
import com.example.Backend.enums.Role;
import com.example.Backend.model.user.User;
import com.example.Backend.service.registrationAndAuth.UserRegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelterManagementService {

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    StaffDAO staffDAO;

    public void registerStaffMember(StaffMemberDTO staffMemberDTO) throws JsonProcessingException,  GlobalException {

        userRegistrationService.check_credentials(staffMemberDTO.getStaffDetails());

        User user= userRegistrationService.get_user(staffMemberDTO.getStaffDetails());

        user.setRole(Role.STAFF);

        if (!staffDAO.addStaffMember(user, staffMemberDTO.getShelterId()));

    }

}
