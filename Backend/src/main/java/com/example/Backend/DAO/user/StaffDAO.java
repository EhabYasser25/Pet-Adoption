package com.example.Backend.DAO.user;

import com.example.Backend.model.user.Staff;
import com.example.Backend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
