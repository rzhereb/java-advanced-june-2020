package com.oktenweb.javaadvjune.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueMovieTitleValidator.class)
@Target({FIELD})
@Retention(value = RUNTIME)
public @interface UniqueMovieTitle {

    String message() default "Movie title should be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
