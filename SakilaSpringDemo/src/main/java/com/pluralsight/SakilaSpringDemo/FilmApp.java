package com.pluralsight.SakilaSpringDemo;

import com.pluralsight.SakilaSpringDemo.dao.FilmDao;
import com.pluralsight.SakilaSpringDemo.dao.SimpleFilmDao;
import com.pluralsight.SakilaSpringDemo.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class FilmApp implements CommandLineRunner {

    @Autowired
    @Qualifier("jdbcFilmDao")
    private FilmDao filmDao;

    @Override
    public void run(String... args) throws Exception {

        // We create a Scanner object so we can read user input from the console.
        Scanner scanner = new Scanner(System.in);

        // This is a "loop" that will keep showing the menu until the user chooses to exit.
        while (true) {
            // Print the menu options to the screen.
            System.out.println("\n=== Film Admin Menu ===");
            System.out.println("1. List Films");
            System.out.println("2. Add Film");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Read the user's choice as a String.
            String choice = scanner.nextLine();

            // Use a "switch" to handle each possible choice.
            switch (choice) {

                case "1":
                    // The user chose option 1 → List all films.

                    // Call the DAO to get a list of all films.
                    List<Film> films = filmDao.getAll();

                    // Print the films to the screen.
                    System.out.println("\nFilms:");
                    for (Film film : films) {
                        System.out.println(film);
                    }

                    break;

                case "2":
                    // The user chose option 2 → Add a new film.

                    // Ask the user for the film's title.
                    System.out.print("Enter film title: ");
                    String title = scanner.nextLine();

                    // Ask the user for the film's rental rate.
                    System.out.print("Enter film rental rate: ");
                    double rentalRate = Double.parseDouble(scanner.nextLine());

                    // Create a new Film object and set its data.
                    Film newFilm = new Film();
                    newFilm.setTitle(title);
                    newFilm.setRentalRate(rentalRate);

                    // Add the new film to the DAO (which stores it in memory).
                    filmDao.add(newFilm);

                    // Let the user know that the film was added.
                    System.out.println("Film added successfully.");

                    break;

                case "3":
                    // The user chose option 3 → Exit the program.

                    // Print a goodbye message.
                    System.out.println("Goodbye!");

                    // End the program with a success status (0).
                    System.exit(0);

                default:
                    // The user entered something that is not a valid option.

                    // Tell the user the input was invalid and show the menu again.
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
