package com.oktenweb.javaadvjune.service;

import com.oktenweb.javaadvjune.dto.MovieCreateDto;
import com.oktenweb.javaadvjune.dto.MovieDto;
import com.oktenweb.javaadvjune.entity.Movie;

import java.util.List;

public interface IMovieService {

    MovieDto saveMovie(MovieCreateDto movie);

    List<MovieDto> getAllMovies();

    MovieDto getMovieById(int id);

    void deleteMovie(int id);

    Movie updateMovie(int id, Movie movie);
}
