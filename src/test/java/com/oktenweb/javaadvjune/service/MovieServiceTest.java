package com.oktenweb.javaadvjune.service;

import com.oktenweb.javaadvjune.dao.DirectorRepository;
import com.oktenweb.javaadvjune.dao.MovieRepository;
import com.oktenweb.javaadvjune.dto.MovieDirectorDto;
import com.oktenweb.javaadvjune.dto.MovieDto;
import com.oktenweb.javaadvjune.entity.Director;
import com.oktenweb.javaadvjune.entity.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

  @Mock
  private MovieRepository movieRepository;

  @Mock
  private DirectorRepository directorRepository;

  @InjectMocks
  private MovieService movieService;

  @Test
  public void givenDirectorNameWhenGettingMoviesByDirectorNameThenReturnMovieDirectorDto() {
    Movie movie1 = new Movie();
    movie1.setId(1);
    movie1.setTitle("Title 1");
    Movie movie2 = new Movie();
    movie2.setId(2);
    movie2.setTitle("Title 2");

    final String directorName = "Tarantino";
    Director director = new Director();
    director.setId(1);
    director.setName(directorName);
    director.setMovies(Arrays.asList(movie1, movie2));

    Mockito.when(directorRepository.findMoviesByDirectorName(ArgumentMatchers.anyString()))
        .thenReturn(director);

    MovieDto movieDto1 = new MovieDto(movie1.getId(), movie1.getTitle(), 0, directorName);
    MovieDto movieDto2 = new MovieDto(movie2.getId(), movie2.getTitle(), 0, directorName);

    MovieDirectorDto expected = new MovieDirectorDto(1, Arrays.asList(movieDto1, movieDto2));

    final MovieDirectorDto actual = movieService.getMoviesByDirectorName(directorName);

    Assertions.assertThat(actual.getMovies()).size().isEqualTo(2);
    Assertions.assertThat(actual.getDirectorId()).isEqualTo(expected.getDirectorId());

  }

}
