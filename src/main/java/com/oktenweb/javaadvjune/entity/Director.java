package com.oktenweb.javaadvjune.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
//    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "director", fetch = FetchType.LAZY)
    private List<Movie> movies = new LinkedList<>();
//
//    @OneToMany(mappedBy = "directorRewarded")
//    private Set<Movie> awardMovies = new HashSet<>();


    @Override
    public String toString() {
        return "Director{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", birthDate=" + birthDate +
            '}';
    }
}
