package com.oktenweb.javaadvjune.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MovieCreateDto {

    private String title;
    private int duration;
    private int directorId;
}
