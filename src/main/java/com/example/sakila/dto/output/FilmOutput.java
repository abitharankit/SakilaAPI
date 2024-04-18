package com.example.sakila.dto.output;

import com.example.sakila.Rating;
import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FilmOutput {

    private Short id;
    private String title;
    private Language language;
    private String description;
    private String rating;
    private Integer releaseYear;
    private List<ActorReferenceOutput> cast;

    public static FilmOutput from (Film film) {
        return new FilmOutput(film.getId(),
                film.getTitle(),
                film.getLanguage(),
                film.getDescription(),
                film.getRating(),
                film.getReleaseYear(),
                film.getCast()
                        .stream()
                        .limit(5)
                        .map(ActorReferenceOutput::from)
                        .collect(Collectors.toList())
                );
    }

}


