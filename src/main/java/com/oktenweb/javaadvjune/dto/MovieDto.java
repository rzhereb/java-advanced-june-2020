package com.oktenweb.javaadvjune.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class MovieDto {

    private int movieId;
    private String title;
    private int duration;
    private String directorName;
}
