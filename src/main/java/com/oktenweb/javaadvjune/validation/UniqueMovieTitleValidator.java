package com.oktenweb.javaadvjune.validation;

import com.oktenweb.javaadvjune.dao.MovieRepository;
import com.oktenweb.javaadvjune.entity.Movie;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class UniqueMovieTitleValidator implements ConstraintValidator<UniqueMovieTitle, String> {

    // Not working with other spring beans IF you are validating ENTITY class
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void initialize(UniqueMovieTitle constraintAnnotation) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final Movie movie = movieRepository.findByTitle(value);
        return movie == null;
    }
}
