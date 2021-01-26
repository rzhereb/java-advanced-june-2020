package com.oktenweb.javaadvjune.dao;

import com.oktenweb.javaadvjune.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DirectorRepository extends JpaRepository<Director, Integer> {

  @Query("Select d from Director d join fetch d.movies where d.name like :name")
  Director findMoviesByDirectorName(String name);

  // select m from Movie m join m.director d where d.name like :name
}
