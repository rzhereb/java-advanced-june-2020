package com.oktenweb.javaadvjune.service;

import com.oktenweb.javaadvjune.dao.DirectorRepository;
import com.oktenweb.javaadvjune.dao.MovieRepository;
import com.oktenweb.javaadvjune.dto.BadRequestException;
import com.oktenweb.javaadvjune.dto.MovieCreateDto;
import com.oktenweb.javaadvjune.dto.MovieDto;
import com.oktenweb.javaadvjune.entity.Director;
import com.oktenweb.javaadvjune.entity.Movie;
import org.apache.commons.lang3.CharUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService {

    private MovieRepository movieRepository;
    private DirectorRepository directorRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }

    @Override
    public MovieDto saveMovie(MovieCreateDto movie) {
        final char firstLetter = movie.getTitle().charAt(0);
        if (!CharUtils.isAsciiAlphaUpper(firstLetter)) {
            throw new BadRequestException("Title should start with capital letter");
        }

        Movie movieEntity = new Movie();
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setDuration(movie.getDuration());
        final Optional<Director> director = directorRepository.findById(movie.getDirectorId());
        final Director existingDirector =
                director.orElseThrow(() -> new BadRequestException("No director with such id"));
        movieEntity.setDirector(existingDirector);
        return convertToMovieDto(movieRepository.saveAndFlush(movieEntity));
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream().map(movie ->
                new MovieDto(movie.getId(), movie.getTitle(), movie.getDuration(), movie.getDirector().getName())
        ).collect(Collectors.toList());
    }

    @Override
    public MovieDto getMovieById(int id) {
        return convertToMovieDto(movieRepository.getOne(id));
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
        }
    }

    private MovieDto convertToMovieDto(Movie movie) {
        return new MovieDto(movie.getId(), movie.getTitle(), movie.getDuration(), movie.getDirector().getName());
    }
}
