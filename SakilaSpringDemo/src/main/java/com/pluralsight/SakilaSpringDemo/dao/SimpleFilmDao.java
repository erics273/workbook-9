package com.pluralsight.SakilaSpringDemo.dao;

import com.pluralsight.SakilaSpringDemo.models.Film;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// This is a "Component" — a Spring-managed bean.
// The @Component annotation tells Spring:
// "Please create an object of this class and manage it for me."
@Component
public class SimpleFilmDao implements FilmDao {

    // This is where we will store our films — in memory, using a List.
    // A List is like a resizable array — it can hold many Film objects.
    private List<Film> films;

    // This is used to assign a unique ID to each new Film.
    // Every time we add a Film, we will increase this number by 1.
    private int nextId = 1;

    // This is the constructor.
    // It runs when we create a new SimpleFilmDao object.
    public SimpleFilmDao() {
        // We create an empty list to hold our films.
        films = new ArrayList<>();

        // Let's add some sample films so the list is not empty when the app starts.
        films.add(new Film(nextId++, "The Matrix", "Action", 2.99));
        films.add(new Film(nextId++, "Finding Nemo", "Animation", 1.99));
        films.add(new Film(nextId++, "Titanic", "Drama", 3.99));
    }

    // This method is required because we are implementing the FilmDao interface.
    // It allows us to add a new Film to the list.
    @Override
    public void add(Film film) {
        // We assign the next unique ID to the film.
        film.setFilmId(nextId++);

        // We add the film to the list.
        films.add(film);
    }

    // This method is required because we are implementing the FilmDao interface.
    // It returns the list of all stored films.
    @Override
    public List<Film> getAll() {
        // Return the list of films.
        return films;
    }
}
