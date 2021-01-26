package com.oktenweb.javaadvjune.service;

import com.oktenweb.javaadvjune.dto.MovieCreateDto;
import com.oktenweb.javaadvjune.dto.MovieDirectorDto;
import com.oktenweb.javaadvjune.dto.MovieDto;
import com.oktenweb.javaadvjune.dto.MoviePageDto;
import com.oktenweb.javaadvjune.entity.Movie;
import org.springframework.data.domain.PageRequest;

public interface IMovieService {

    MovieDto saveMovie(MovieCreateDto movie);

    MoviePageDto getAllMovies(PageRequest pageRequest);

    MovieDto getMovieById(int id);

    void deleteMovie(int id);

    Movie updateMovie(int id, Movie movie);

    MovieDirectorDto getMoviesByDirectorName(String name);
}
