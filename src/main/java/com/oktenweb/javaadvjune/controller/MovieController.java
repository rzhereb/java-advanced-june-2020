package com.oktenweb.javaadvjune.controller;

import com.oktenweb.javaadvjune.entity.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    private List<Movie> movies = new ArrayList<>();

    {
        movies.add(new Movie(1, "LoTR: RoTK", 186));
        movies.add(new Movie(2, "Harry Potter: DH", 131));
        movies.add(new Movie(3, "Deadpool", 115));
    }

//    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    @GetMapping
    public List<Movie> getAllMovies() {
        return movies;
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        movies.add(movie);
        return movie;
    }

}


//        /movies
//                C(create)   R(read)   U(update)   D(delete)
//                MySQL:  Insert      Select    Update      Delete
//                REST:   POST        GET       PUT/PATCH   DELETE