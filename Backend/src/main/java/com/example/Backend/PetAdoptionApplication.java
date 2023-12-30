package com.example.Backend;

import com.example.Backend.model.pet.Pet;
import com.example.Backend.model.pet.species;
import com.example.Backend.model.user.Admin;
import com.example.Backend.model.user.User;
import com.example.Backend.service.pet.PetService;
import com.example.Backend.service.user.AdminService;
import com.example.Backend.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PetAdoptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetAdoptionApplication.class, args);
	}

}