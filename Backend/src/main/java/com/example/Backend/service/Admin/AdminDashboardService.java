package com.example.Backend.service.Admin;

import com.example.Backend.DAO.ShelterDAO;
import com.example.Backend.DAO.user.StaffDAO;
import com.example.Backend.model.Shelter;
import com.example.Backend.model.user.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDashboardService {

    @Autowired
    private ShelterDAO shelterDAO;
    @Autowired
    private StaffDAO staffDAO;

    public List<Shelter> getAllShelters() {
        return shelterDAO.getAllShelters();
    }

    public Shelter updateShelter(int id, String name, String address) {
        return shelterDAO.updateShelterById(id, name, address);
    }

    public boolean deleteShelter(int id) {
        return shelterDAO.deleteShelterById(id);
    }

    public List<Staff> getAllShelterStaff (int shelterId) {
        return staffDAO.getAllStaff(shelterId);
    }

    public boolean deleteStaffMember (int staffId) {
        return staffDAO.deleteStaff(staffId);
    }
}
