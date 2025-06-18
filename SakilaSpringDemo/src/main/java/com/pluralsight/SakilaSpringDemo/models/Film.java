package com.pluralsight.SakilaSpringDemo.models;

// This is a "model" class.
// A model is just a simple class that holds data about something.
// In this case, it holds information about a Film (movie).

public class Film {

    // This is the unique ID number for each film.
    // It's like a serial number that helps us tell films apart.
    private int filmId;

    // The title (name) of the film.
    private String title;

    // The rental rate (how much it costs to rent the film).
    private double rentalRate;


    //default constructor with no parameters
    public Film() {}

    // This is a "constructor with parameters."
    // It lets us create a Film and set all its data at once.
    public Film(int filmId, String title, String category, double rentalRate) {
        this.filmId = filmId;
        this.title = title;
        this.rentalRate = rentalRate;
    }

    // These are called "getter" and "setter" methods.
    // They allow us to read (get) and change (set) the values of each field.

    // Get or set the film's ID number.
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    // Get or set the film's title.
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Get or set the film's rental rate.
    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    // This is a special method called toString().
    // It tells Java what to print when we print a Film object.
    // This is very helpful for debugging or showing the film in the console.
    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", title='" + title + '\'' +
                ", rentalRate=" + rentalRate +
                '}';
    }
}
