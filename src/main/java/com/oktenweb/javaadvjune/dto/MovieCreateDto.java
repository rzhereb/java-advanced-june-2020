package com.oktenweb.javaadvjune.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class MovieCreateDto {

    private String title;
    private int duration;
    private int directorId;
}
