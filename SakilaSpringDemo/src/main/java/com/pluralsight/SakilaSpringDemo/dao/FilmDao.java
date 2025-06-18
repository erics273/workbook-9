package com.pluralsight.SakilaSpringDemo.dao;

import com.pluralsight.SakilaSpringDemo.models.Film;
import java.util.List;

// This is an "interface" — a special kind of class that does NOT have any code inside the methods yet.
// An interface is like a "contract" — it says what methods a class must have, but not how they work.
// Classes that "implement" this interface will have to provide the actual code for these methods.

// The name DAO stands for "Data Access Object."
// A DAO is a class that is responsible for saving, retrieving, updating, or deleting data.
// In this case, FilmDao will manage Film objects.

public interface FilmDao {

    // This method will allow us to add a new Film.
    // We pass in a Film object, and the DAO will store it.
    Film add(Film film);

    // This method will return a list of all Films that have been stored.
    // It returns a List<Film> — a list of Film objects.
    List<Film> getAll();

    Film findById(int id);

    // NOTE:
    // Right now we are only adding 2 methods here.
    // Later we could add more methods to this interface, such as:
    // - deleteById(int filmId)
    // - findById(int filmId)
    // - update(Film film)
    // These methods would give us more ways to work with Films.
}