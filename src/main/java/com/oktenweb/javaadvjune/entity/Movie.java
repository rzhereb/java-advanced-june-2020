package com.oktenweb.javaadvjune.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //mandatory!!!
//@Table  // optional
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column // optional
    @Column(name = "movie_title")
    @NotBlank
//    @UniqueMovieTitle
    private String title;
    @Positive
    @Max(value = 210)
    private int duration;

    @ManyToOne(targetEntity = Director.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private Director director;
//
//    @ManyToOne(targetEntity = Director.class)
//    private Director directorRewarded;



    @Override
    public String toString() {
        return "Movie{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", duration=" + duration +
            '}';
    }
}