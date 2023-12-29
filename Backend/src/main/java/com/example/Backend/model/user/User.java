package com.example.Backend.model.user;

import com.example.Backend.emum.Gender;
import com.example.Backend.emum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;


// TODO make lombok workkkkkk :( it seems like the problem is within the pom.xml file...
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
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
    private Gender gender;
    private LocalDate birthdate;
    private Role role;


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername() {
        return this.username;
    }

}
