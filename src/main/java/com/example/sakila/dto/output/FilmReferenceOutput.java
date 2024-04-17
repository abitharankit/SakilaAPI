package com.example.sakila.dto.output;

import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilmReferenceOutput {
    private Short id;
    private String title;
    private Integer releaseYear;

    public static FilmReferenceOutput from (Film film) {
        return new FilmReferenceOutput(film.getId(), film.getTitle(), film.getReleaseYear() );

    }
}
