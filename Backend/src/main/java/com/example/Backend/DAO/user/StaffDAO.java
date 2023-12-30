package com.example.Backend.DAO.user;

import com.example.Backend.DTO.registrationAndAuth.StaffMemberDTO;
import com.example.Backend.model.user.Staff;
import com.example.Backend.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class StaffDAO {
    @Autowired
    UserDAO userDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Staff getById(int id) {
        try {

            BeanPropertyRowMapper<Staff> rowMapper = new BeanPropertyRowMapper<>(Staff.class);
            Staff result = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM staff WHERE user_id = ?", rowMapper, id);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional
    public boolean addStaffMember(User user, int shelterId) {
        try {
            // First, insert the common user attributes into the user table

            if (userDAO.insertUser(user)){
                String staffQuery = "INSERT INTO staff (user_id, shelter_id) VALUES (LAST_INSERT_ID(), ?)";
                int rowsAffectedStaff = jdbcTemplate.update(staffQuery, shelterId);
                return rowsAffectedStaff > 0;
            }
            return false;

        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllStaff(int shelterId) {
        String sql = "SELECT user_id FROM staff WHERE shelter_id = ?";
        List<User> users = new ArrayList<>();

        List<Integer> userIds = jdbcTemplate.queryForList(sql, Integer.class, shelterId);

        for (Integer userId : userIds) {
            User user = userDAO.getById(userId);
            if (user != null) {
                users.add(user);
            }
        }

        return users;
    }

    public boolean deleteStaff (int staffId) {
        try {
            String sql = "DELETE FROM staff WHERE user_id = ?";
            int result = jdbcTemplate.update(sql, staffId);
            return result > 0;
        } catch (DataAccessException e) {
            System.out.println("Error deleting staff with id: " + staffId);
            return false;
        }
    }

    public boolean updateStaffMember(StaffMemberDTO staffMember) {
        try {
            String email = staffMember.getStaffDetails().getEmail();
            // Fetch the User object from the database
            User user = userDAO.getByUsernameOrEmail(email);
            if (user == null) {
                System.out.println("User not found with Email: " + email);
                return false;
            }

            user.setEmail(staffMember.getStaffDetails().getEmail());
            user.setGender(staffMember.getStaffDetails().getGender());
            user.setFirstName(staffMember.getStaffDetails().getFirstName());
            user.setMiddleName(staffMember.getStaffDetails().getMiddleName());
            user.setLastName(staffMember.getStaffDetails().getLastName());
//            user.setFullName(staffMember.getStaffDetails().getFullName());
            user.setPhoneNo(staffMember.getStaffDetails().getPhoneNo());
            user.setUsername(staffMember.getStaffDetails().getUsername());
            user.setBirthdate(staffMember.getStaffDetails().getBirthdate());

            // Update the User object in the database
            return userDAO.updateUser(user);

        } catch (DataAccessException e) {
            String email = staffMember.getStaffDetails().getEmail();
            System.out.println("Error updating staff member with user email: " + email);
            e.printStackTrace();
            return false;
        }
    }



}
