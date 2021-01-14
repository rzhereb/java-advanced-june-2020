package com.oktenweb.javaadvjune.validation;

import com.oktenweb.javaadvjune.entity.Movie;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MovieValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Movie.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Movie movie = (Movie) target;
        if (StringUtils.isNotBlank(movie.getTitle())) {
            final char firstLetter = movie.getTitle().charAt(0);
            if (!CharUtils.isAsciiAlphaUpper(firstLetter)) {
                errors.rejectValue("title", "title-capital-letter",
                        "Title should start with capital letter");
            }
        }
    }
}
