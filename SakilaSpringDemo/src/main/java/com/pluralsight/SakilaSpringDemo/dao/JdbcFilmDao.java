package com.pluralsight.SakilaSpringDemo.dao;

import com.pluralsight.SakilaSpringDemo.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// This is a Spring Component.
// The @Component annotation tells Spring to create an instance of this class and manage it as a Bean.
// This allows us to inject it into other classes (like FilmApp).
@Component
public class JdbcFilmDao implements FilmDao {

    // This is the DataSource that we will use to connect to the database.
    // The DataSource is created in our DbConfiguration class.
    private DataSource dataSource;

    // This is a constructor.
    // Spring will automatically call this constructor and pass in the DataSource.
    // The @Autowired annotation tells Spring to "inject" the DataSource Bean here.
    @Autowired
    public JdbcFilmDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // This method will add a new Film to the database.
    // It is required because we are implementing the FilmDao interface.
    @Override
    public void add(Film film) {
        // This is the SQL INSERT statement we will run.
        // We are inserting the film title, rental rate, and language_id.
        String sql = "INSERT INTO film (title, rental_rate, language_id) VALUES (?, ?, ?)";

        // This is a "try-with-resources" block.
        // It ensures that the Connection and PreparedStatement are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the first parameter (?) to the film's title.
            stmt.setString(1, film.getTitle());

            // Set the second parameter (?) to the film's rental rate.
            stmt.setDouble(2, film.getRentalRate());

            // Set the third parameter (?) to the language_id.
            // Hard-coding it to 1 because the language_id cannot be NULL in the database.
            // We are not asking the user for this value in the UI yet.
            stmt.setInt(3, 1);

            // Execute the INSERT statement — this will add the row to the database.
            stmt.executeUpdate();

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }
    }

    // This method will return a list of all Films from the database.
    // It is required because we are implementing the FilmDao interface.
    @Override
    public List<Film> getAll() {
        // Create an empty list to hold the Film objects we will retrieve.
        List<Film> films = new ArrayList<>();

        // This is the SQL SELECT statement we will run.
        String sql = "SELECT film_id, title, rental_rate FROM film";

        // This is a "try-with-resources" block.
        // It ensures that the Connection, Statement, and ResultSet are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Loop through each row in the ResultSet.
            while (rs.next()) {
                // Create a new Film object.
                Film film = new Film();

                // Set the film's ID from the "film_id" column.
                film.setFilmId(rs.getInt("film_id"));

                // Set the film's title from the "title" column.
                film.setTitle(rs.getString("title"));

                // Set the film's rental rate from the "rental_rate" column.
                film.setRentalRate(rs.getDouble("rental_rate"));

                // Add the Film object to our list.
                films.add(film);
            }

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            e.printStackTrace();
        }

        // Return the list of Film objects.
        return films;
    }
}
