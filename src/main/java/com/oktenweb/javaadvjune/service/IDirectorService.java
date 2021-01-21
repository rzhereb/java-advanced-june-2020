package com.oktenweb.javaadvjune.service;

import com.oktenweb.javaadvjune.entity.Director;

import java.util.List;

public interface IDirectorService {

    Director saveDirector(Director director);

    List<Director> getAllDirectors();

    Director getDirectorById(int id);

    void deleteDirector(int id);

    Director updateDirector(int id, Director director);
}
