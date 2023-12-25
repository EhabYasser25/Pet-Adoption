package com.example.Backend;

import com.example.Backend.model.user.Staff;
import com.example.Backend.model.user.User;
import com.example.Backend.service.StaffService;
import com.example.Backend.service.UserService;
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

			StaffService staffService = ctx.getBean(StaffService.class);
			Staff staff = staffService.getById(3);
			System.out.println(staff);
		};
	}

}
