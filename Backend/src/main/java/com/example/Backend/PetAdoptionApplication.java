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

	// -------------- TESTING ------------------
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			UserService userService = ctx.getBean(UserService.class);
			User user = userService.getById(3);
			System.out.println(user);

//			StaffService staffService = ctx.getBean(StaffService.class);
//			Staff staff = staffService.getById(3);
//			System.out.println(staff);

			AdminService adminService = ctx.getBean(AdminService.class);
			Admin admin = adminService.getByUsername("User");
			System.out.println(admin);

//			PetTypeService petTypeService = ctx.getBean(PetTypeService.class);
//			species species = petTypeService.getById(1);
//			System.out.println(species);

			PetService petService = ctx.getBean(PetService.class);
			Pet pet = petService.getById(1);
			System.out.println(pet);
		};
	}

}