package com.oktenweb.javaadvjune.dao;

import com.oktenweb.javaadvjune.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
