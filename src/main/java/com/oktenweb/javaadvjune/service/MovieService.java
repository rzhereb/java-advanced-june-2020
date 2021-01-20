package com.oktenweb.javaadvjune.service;

import com.oktenweb.javaadvjune.dao.MovieRepository;
import com.oktenweb.javaadvjune.dto.BadRequestException;
import com.oktenweb.javaadvjune.entity.Movie;
import org.apache.commons.lang3.CharUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements IMovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        final char firstLetter = movie.getTitle().charAt(0);
        if (!CharUtils.isAsciiAlphaUpper(firstLetter)) {
            throw new BadRequestException("Title should start with capital letter");
        }
        return movieRepository.saveAndFlush(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepository.getOne(id);
    }

    @Override
    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie updateMovie(int id, Movie movie) {
        if (movieRepository.existsById(id)) {
            movie.setId(id);
            return movieRepository.saveAndFlush(movie);
        } else {
            throw new IllegalArgumentException("No movie with such id: " + id);
        }    }
}
