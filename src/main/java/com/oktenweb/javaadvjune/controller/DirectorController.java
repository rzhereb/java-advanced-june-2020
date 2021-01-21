package com.oktenweb.javaadvjune.controller;

import com.oktenweb.javaadvjune.entity.Director;
import com.oktenweb.javaadvjune.service.IDirectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/directors")
@Slf4j
public class DirectorController {

    @Autowired
    private IDirectorService directorService;

    @GetMapping
    public List<Director> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    @GetMapping("/director")
    public Director getDirector(@PathVariable int id) {
        return directorService.getDirectorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Director createDirector(@RequestBody Director director) {
        log.info("Handled POST request with body: {}", director);
        return directorService.saveDirector(director);
    }

    @PutMapping(value = "/{id}")
    public Director updateDirector(@PathVariable int id, @RequestBody @Valid Director director) {
        return directorService.updateDirector(id, director);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDirector(@PathVariable int id) {
        directorService.deleteDirector(id);
    }
}