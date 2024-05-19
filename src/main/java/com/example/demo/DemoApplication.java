package com.example.demo;

import com.example.demo.user.User;
import com.example.demo.user.UserRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRestClient userRestClient) {
		return args -> {
			List<User> users = userRestClient.findAllUser();
			users.forEach(System.out::println);
		};
	}

}
