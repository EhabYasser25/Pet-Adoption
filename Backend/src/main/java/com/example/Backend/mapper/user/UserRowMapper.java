package com.example.Backend.mapper.user;

import com.example.Backend.emum.Role;
import com.example.Backend.model.user.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("middle_name"),
                rs.getString("last_name"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("phone_no"),
                rs.getString("gender"),
                rs.getDate("birthdate"),
                rs.getString("location_city"),
                rs.getString("location_country"),
                Role.valueOf(rs.getString("role"))
        );
//        return User.build() // TODO Make lombok work for this simpler implementation ::
//                .id(rs.getInt("id"))
//                .firstName(rs.getString("first_name"))
//                .middleName(rs.getString("middle_name"))
//                .lastName(rs.getString("last_name"))
//                .fullName(rs.getString("full_name"))
//                .username(rs.getString("username"))
//                .password(rs.getString("password"))
//                .email(rs.getString("email"))
//                .phoneNo(rs.getString("phone_no"))
//                .gender(rs.getString("gender"))
//                .birthdate(rs.getDate("birthdate"))
//                .locationCity(rs.getString("location_city"))
//                .locationCountry(rs.getString("location_country"))
//                .role(Role.valueOf(rs.getString("role")))
//                .build();
    }
}
