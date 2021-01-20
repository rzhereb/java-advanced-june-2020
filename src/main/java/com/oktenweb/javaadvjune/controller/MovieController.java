package com.oktenweb.javaadvjune.controller;

import com.oktenweb.javaadvjune.entity.Movie;
import com.oktenweb.javaadvjune.service.IMovieService;
import com.oktenweb.javaadvjune.validation.MovieValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

//@Component
//@Bean
//@Controller
//@Repository
//@Service
//@Configuration
//@Value

@RestController
@RequestMapping(value = "/movies")
@Slf4j
public class MovieController {

    @Autowired
    private IMovieService movieService;

    // if you don't have lombok in your project
//    private static Logger logger = LoggerFactory.getLogger(MovieController.class);

//    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    //PathVariable > RequestParam (RequestParam sometimes is bad practice)
    @GetMapping("/movie")
    public Movie getMovie(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie createMovie(@RequestBody @Valid Movie movie) {
        log.info("Handled POST request with body: {}", movie);
        return movieService.saveMovie(movie);
    }

    @PutMapping(value = "/{id}")
    public Movie updateMovie(@PathVariable int id, @RequestBody @Valid Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
    }

//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder) {
//        webDataBinder.addValidators(new MovieValidator());
//    }
}


//        /movies
//                C(create)   R(read)   U(update)   D(delete)
//                MySQL:  Insert      Select    Update      Delete
//                REST:   POST        GET       PUT/PATCH   DELETE