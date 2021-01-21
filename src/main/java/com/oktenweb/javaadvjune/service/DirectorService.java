package com.oktenweb.javaadvjune.service;

import com.oktenweb.javaadvjune.dao.DirectorRepository;
import com.oktenweb.javaadvjune.entity.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService implements IDirectorService {

    private DirectorRepository directorRepository;

    @Autowired
    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public Director saveDirector(Director director) {
        return directorRepository.saveAndFlush(director);
    }

    @Override
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Override
    public Director getDirectorById(int id) {
        return directorRepository.getOne(id);
    }

    @Override
    public void deleteDirector(int id) {
        directorRepository.deleteById(id);
    }

    @Override
    public Director updateDirector(int id, Director director) {
        if (directorRepository.existsById(id)) {
            director.setId(id);
            return directorRepository.saveAndFlush(director);
        } else {
            throw new IllegalArgumentException("No director with such id: " + id);
        }
    }
}
