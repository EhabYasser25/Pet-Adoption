package com.example.Backend.DAO.application;

import com.example.Backend.enums.*;
import com.example.Backend.model.application.*;
import lombok.*;
import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@Repository
public class ApplicationDAO {

    private final JdbcTemplate jdbcTemplate;

    public Application getById(int id) {
        try {
            String query = "SELECT * FROM application WHERE id = ?";
            BeanPropertyRowMapper<Application> rowMapper = new BeanPropertyRowMapper<>(Application.class);
            Application application = this.jdbcTemplate.queryForObject(query, rowMapper, id);
            return application;
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("No applications found");
        }
    }

    public List<Application> getAll() {
        try {
            String query = "SELECT * FROM application";
            BeanPropertyRowMapper<Application> rowMapper = new BeanPropertyRowMapper<>(Application.class);
            List<Application> applications = this.jdbcTemplate.query(query, rowMapper);
            return applications;
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("No applications found");
        }
    }

    public List<Application> getAllByShelterId(int shelterId) {
        try {
            String query = "SELECT * FROM application WHERE shelter_id = ?";
            BeanPropertyRowMapper<Application> rowMapper = new BeanPropertyRowMapper<>(Application.class);
            List<Application> applications = this.jdbcTemplate.query(query, rowMapper, shelterId);
            return applications;
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("No applications found");
        }
    }

    public boolean saveApplication(Application application) {
        try {
            String query = "INSERT INTO application (pet_id, user_id, shelter_id, status, message, time_stamp) VALUES (?, ?, ?, ?, ?, ?)";
            int result = jdbcTemplate.update(query, application.getPetId(), application.getUserId(), application.getShelterId(), application.getStatus(), application.getMessage(), application.getTimeStamp());
            return result > 0;
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Application not saved");
        }
    }

    @Transactional
    public boolean updateApplication(Application application) {
        try {
            jdbcTemplate.execute("BEGIN");

            String checkQuery = "SELECT COUNT(*) FROM application WHERE pet_id = ? AND status = 'APPROVED'";
            int count = jdbcTemplate.queryForObject(checkQuery, new Object[]{application.getPetId()}, Integer.class);
            if (count > 0 && application.getStatus().equals(ApplicationStatus.APPROVED.getStatus())) {
                jdbcTemplate.execute("ROLLBACK");
                throw new RuntimeException("This pet has already been approved in another application.");
            }

            String lockQuery = "SELECT * FROM application WHERE pet_id = ? FOR UPDATE";
            jdbcTemplate.queryForList(lockQuery, application.getPetId());

            String updateQuery = "UPDATE application SET status = ?, staff_id = ? WHERE id = ?";
            int result = jdbcTemplate.update(updateQuery, application.getStatus(), application.getStaffId(), application.getId());

            String callProcedureQuery = "CALL decline_pending_applications(?)";
            jdbcTemplate.update(callProcedureQuery, application.getPetId());

            jdbcTemplate.execute("COMMIT");

            return result > 0;
        } catch (Exception e) {
            jdbcTemplate.execute("ROLLBACK");
            throw new RuntimeException("Application not updated: " + e.getMessage());
        }
    }

}
