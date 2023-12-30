package com.example.Backend.DAO.user;

import com.example.Backend.DAO.LocationDAO;
import com.example.Backend.enums.Gender;
import com.example.Backend.enums.Role;
import com.example.Backend.model.user.Admin;
import com.example.Backend.model.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class UserDAOTest {

    @Autowired
    UserDAO userDAO;

    @Autowired
    StaffDAO staffDAO;

    @Autowired
    AdminDAO adminDAO;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    LocationDAO locationDAO;

    @Test
    public void test(){


//        BeanPropertyRowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
//        String sql = "SELECT country FROM location";
//        System.out.println( this.jdbcTemplate.query(sql, new SingleColumnRowMapper<>(String.class)).toString());

        List<String> result = this.jdbcTemplate.query(
                "SELECT country FROM location", new SingleColumnRowMapper<>(String.class));

        System.out.println(locationDAO.getCountries());


//        System.out.println(result);
        userDAO.getById(3);
        System.out.println(userDAO.existsByEmail("jane@example.com"));
        User user =  new User();
        user.setEmail("jack@gmail.com");
        user.setUsername("jack");
        user.setPassword("password");
        user.setFirstName("Mariam");
        user.setGender(Gender.FEMALE);
        user.setBirthdate(LocalDate.parse("2002-02-01"));
        user.setMiddleName("osama");
        user.setLastName("Elsamni");
        user.setPhoneNo("010121234135");
        user.setRole(Role.USER);

//        StaffMemberDTO staff =  new StaffMemberDTO();
//        staff.getRegistrationRequestDTO().setEmail("staff@gmail.com");
//        staff.getRegistrationRequestDTO().setUsername("mariam");
//        staff.getRegistrationRequestDTO().setPassword("password");
//        staff.getRegistrationRequestDTO().setFirstName("Mariam");
//        staff.getRegistrationRequestDTO().setGender(Gender.FEMALE);
//        staff.getRegistrationRequestDTO().setBirthdate(LocalDate.parse("2002-02-01"));
//        staff.getRegistrationRequestDTO().setMiddleName("osama");
//        staff.getRegistrationRequestDTO().setLastName("Elsamni");
//        staff.getRegistrationRequestDTO().setPhoneNo("010121234135");
//        staff.setShelterId(1);
//        staffDAO.addStaffMember(staff);


//        User userr = userDAO.getByUsernameOrEmail("staff@gmail.com");
//        System.out.println(userr.getFirstName());

        Admin admin = adminDAO.getByUsername("admin");
        System.out.println(admin.getPassword());


//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        String encodedPassword = passwordEncoder.encode("Strong!123");
//        adminDAO.insertAdmin(Admin.builder().password(encodedPassword).username("menna").build());




    }

}