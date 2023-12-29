package com.example.Backend.DAO.user;

import com.example.Backend.DTO.registrationAndAuth.StaffMemberDTO;
import com.example.Backend.enums.Roles;
import com.example.Backend.mapper.user.StaffRowMapper;
import com.example.Backend.model.user.Staff;
import com.example.Backend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StaffDAO {

    @Autowired
    UserDAO userDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Autowired
    private StaffRowMapper staffRowMapper;

    public Staff getById(int id) {
        try {
            Staff result = this.jdbcTemplate.queryForObject(
                    "SELECT * FROM staff WHERE user_id = ?", staffRowMapper, id);
            System.out.println(result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("Staff not found with ID: " + id);
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
//            String query = "INSERT INTO user (first_name, middle_name, last_name, username, password, email, phone_no, gender, birthdate, role) " +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//            int rowsAffected = jdbcTemplate.update(query,
//                    staffMember.getRegistrationRequestDTO().getFirstName(),
//                    staffMember.getRegistrationRequestDTO().getMiddleName(),
//                    staffMember.getRegistrationRequestDTO().getLastName(),
//                    staffMember.getRegistrationRequestDTO().getUsername(),
//                    staffMember.getRegistrationRequestDTO().getPassword(),
//                    staffMember.getRegistrationRequestDTO().getEmail(),
//                    staffMember.getRegistrationRequestDTO().getPhoneNo(),
//                    staffMember.getRegistrationRequestDTO().getGender().toString(),  // Assuming Gender is an Enum
//                    staffMember.getRegistrationRequestDTO().getBirthdate(),
//                    Roles.STAFF.toString());   // Assuming Role is an Enum
//            // If user insertion is successful, insert staff-specific attributes
//            if (rowsAffected > 0) {
//                String staffQuery = "INSERT INTO staff (user_id, shelter_id) VALUES (LAST_INSERT_ID(), ?)";
//                int rowsAffectedStaff = jdbcTemplate.update(staffQuery, staffMember.getShelterId());
//                return rowsAffectedStaff > 0;
//            }
//            return false;
        } catch (DataAccessException e) {
            System.out.println("couldn't insert staff member");
            e.printStackTrace();
            return false;
        }
    }
}
