package com.example.Backend.model.user;

import com.example.Backend.emum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.Date;


// TODO make lombok workkkkkk :( it seems like the problem is within the pom.xml file...
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName; // This attribute will be derived/calculated in the application, not stored in the database
    private String username;
    private String password;
    private String email;
    private String phoneNo;
    private String gender;
    private Date birthdate;
    private String locationCity;
    private String locationCountry;
    private Role role;

    public User(int id, String firstName, String middleName, String lastName, String username,
                String password, String email, String phoneNo, String gender, java.sql.Date birthdate, String locationCity,
                String locationCountry, Role role) { // @AllArgsConstructor
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.birthdate = birthdate;
        this.locationCity = locationCity;
        this.locationCountry = locationCountry;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
