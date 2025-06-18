package com.pluralsight.SakilaSpringDemo;

// These are special Spring Boot imports that let us start the app.
import com.pluralsight.SakilaSpringDemo.dao.FilmDao;
import com.pluralsight.SakilaSpringDemo.dao.SimpleFilmDao;
import com.pluralsight.SakilaSpringDemo.models.Film;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

// This is a special annotation that tells Spring Boot:
// "This is the main class for your application."
// It turns on many helpful features automatically (auto-configuration, component scanning, etc.).
@SpringBootApplication
public class SakilaSpringDemoApplication {

	// This is the "main method" — the starting point of any Java application.
	// When you run the app, Java looks for this method first.
	public static void main(String[] args) {

		//make sure the username and password were provided for the db
		if (args.length != 2) {
			System.out.println("Usage: java -jar app.jar <username> <password>");
			System.exit(1);
		}

		// Set system properties with the username and password so Spring can read them later.
		System.setProperty("dbUsername", args[0]);
		System.setProperty("dbPassword", args[1]);

		// This line starts the entire Spring Boot application.
		// It does 3 main things:
		// 1. Creates the Spring "ApplicationContext" (this is like the brain of Spring that manages everything).
		// 2. Scans for your @Component classes (like FilmApp, SimpleFilmDao, etc.) and creates them automatically.
		// 3. Starts the web server (if your app had web controllers), or calls CommandLineRunner beans (like your FilmApp).

		SpringApplication.run(SakilaSpringDemoApplication.class, args);

		// After this line runs, your app is running!
		// If you have a CommandLineRunner (like FilmApp), its run() method will now be called.


	}

}
