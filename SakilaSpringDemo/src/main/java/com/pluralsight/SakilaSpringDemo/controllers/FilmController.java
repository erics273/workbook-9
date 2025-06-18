package com.pluralsight.SakilaSpringDemo.controllers;

import com.pluralsight.SakilaSpringDemo.dao.FilmDao;
import com.pluralsight.SakilaSpringDemo.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {

    @Autowired
    @Qualifier("jdbcFilmDao")
    private FilmDao filmDao;

    @GetMapping("/api/films")
    public List<Film> getAllFilms(){
        return filmDao.getAll();
    }

    @GetMapping("/api/films/{id}")
    public Film getAllFilms(@PathVariable int id){
        return filmDao.findById(id);
    }

    @PostMapping("/api/films")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Film addFilm(@RequestBody Film film){
        return filmDao.add(film);
    }

//    @PutMapping("/api/films/{id}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public void updateFilm(@PathVariable int id, @RequestBody Film film){
//        filmDao.updateFilm(id, film);
//    }
//
//    @DeleteMapping("/api/films/{id}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public void deleteFilm(@PathVariable int id){
//        filmDao.deleteFilm(id);
//    }

}
