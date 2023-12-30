package com.example.Backend.service.staff;

import com.example.Backend.DAO.*;
import com.example.Backend.DAO.Pet.*;
import com.example.Backend.DAO.application.*;
import com.example.Backend.DAO.user.*;
import com.example.Backend.enums.*;
import com.example.Backend.model.*;
import com.example.Backend.model.application.*;
import com.example.Backend.model.pet.*;
import com.example.Backend.model.user.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.*;

@RequiredArgsConstructor
@Service
public class StaffService {

    private final StaffDAO staffDAO;
    private final UserDAO userDAO;
    private final PetDAO petDAO;
    private final ApplicationDAO applicationDAO;
    private final ShelterDAO shelterDAO;

    public void addPet(Pet pet, int shelterId) {
        Shelter shelter = shelterDAO.getShelterById(shelterId);
        pet.setShelterId(shelterId);
        pet.setShelterLocationCity(shelter.getLocationCity());
        pet.setShelterLocationCity(shelter.getLocationCity());
        pet.setReleaseTimeStamp(new Timestamp(System.currentTimeMillis()));
        if (petDAO.insertPet(pet)) {
            System.out.println("Pet added successfully");
        } else {
            throw new RuntimeException("Pet not added");
        }
    }

    public void editPet(Pet pet) {
        if (petDAO.editPet(pet)) {
            System.out.println("Pet updated successfully");
        } else {
            throw new RuntimeException("Pet not updated");
        }
    }

    public void deletePet(int petId) {
        if (petDAO.deletePet(petId)) {
            System.out.println("Pet deleted successfully");
        } else {
            throw new RuntimeException("Pet not deleted");
        }
    }

    public List<Application> getAllApplications(String username) {
        User user = userDAO.getByUsernameOrEmail(username);
        Staff staff = staffDAO.getById(user.getId());
        return applicationDAO.getAllByShelterId(staff.getShelterId());
    }

    public void approveApplication(String username, int applicationId) {
        User staff = userDAO.getByUsernameOrEmail(username);
        Application application = applicationDAO.getById(applicationId);
        application.setStatus(ApplicationStatus.APPROVED.getStatus());
        application.setStaffId(staff.getId());
        if (applicationDAO.updateApplication(application))
            System.out.println("Application approved successfully");
        else
            throw new RuntimeException("Application not approved");
    }

    public void rejectApplication(String username, int applicationId) {
        User staff = userDAO.getByUsernameOrEmail(username);
        Application application = applicationDAO.getById(applicationId);
        application.setStatus(ApplicationStatus.DENIED.getStatus());
        application.setStaffId(staff.getId());
        if (applicationDAO.updateApplication(application))
            System.out.println("Application rejected successfully");
        else
            throw new RuntimeException("Application not rejected");
    }
}
