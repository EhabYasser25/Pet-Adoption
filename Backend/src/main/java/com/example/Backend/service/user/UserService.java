package com.example.Backend.service.user;

import com.example.Backend.DAO.Pet.*;
import com.example.Backend.DAO.application.*;
import com.example.Backend.DAO.user.UserDAO;
import com.example.Backend.enums.*;
import com.example.Backend.model.application.*;
import com.example.Backend.model.pet.*;
import com.example.Backend.model.user.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserDAO userDAO;
    private final ApplicationDAO applicationDAO;
    private final PetDAO petDAO;

    public User getById(int id) {
        return userDAO.getById(id);
    }

    public void submitApplication(Application application, String username) {
        application.setStatus(ApplicationStatus.PENDING.getStatus());
        application.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        User user = userDAO.getByUsernameOrEmail(username);
        Pet pet = petDAO.getById(application.getPetId());
        application.setUserId(user.getId());
        application.setShelterId(pet.getShelterId());
        if (applicationDAO.saveApplication(application))
            System.out.println("Application submitted successfully");
        else
            throw new RuntimeException("Application not submitted");
    }
}
