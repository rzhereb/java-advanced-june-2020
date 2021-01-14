package com.oktenweb.javaadvjune.validation;

import com.oktenweb.javaadvjune.dao.MovieRepository;
import com.oktenweb.javaadvjune.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMovieTitleValidator implements ConstraintValidator<UniqueMovieTitle, String> {

    private MovieRepository movieRepository;

    @Autowired
    public UniqueMovieTitleValidator(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void initialize(UniqueMovieTitle constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final Movie movie = movieRepository.findByTitle(value);
        return movie == null;
    }
}
