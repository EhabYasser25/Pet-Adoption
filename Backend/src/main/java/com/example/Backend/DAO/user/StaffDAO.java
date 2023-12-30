package com.example.Backend.DAO.user;

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

    public List<Staff> getAllStaff (int shelterId) {
        return jdbcTemplate.query("SELECT * FROM staff", new BeanPropertyRowMapper<>(Staff.class));
    }

    public boolean deleteStaff (int staffId) {
        try {
            String sql = "DELETE FROM staff WHERE id = ?";
            int result = jdbcTemplate.update(sql, staffId);
            return result > 0;
        } catch (DataAccessException e) {
            System.out.println("Error deleting staff with id: " + staffId);
            return false;
        }
    }
    public Integer getShelterIdByUserID(int userId) {
        try {
            return this.jdbcTemplate.queryForObject(
                    "SELECT shelter_id FROM staff WHERE user_id = ?", Integer.class, userId);
        } catch (EmptyResultDataAccessException e) {
            // Handle case where user is not found
            System.out.println("User not found with ID: " + userId);
            return null;
        }
    }

}
