package com.oktenweb.javaadvjune.dao;

import com.oktenweb.javaadvjune.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
