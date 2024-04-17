package com.example.sakila.controllers;

import com.example.sakila.dto.input.FilmInput;
import com.example.sakila.dto.output.FilmOutput;
import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import com.example.sakila.repositories.FilmRepository;
import com.example.sakila.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import static com.example.sakila.dto.input.ValidationGroup.Create;
import static com.example.sakila.dto.input.ValidationGroup.Update;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/films")
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private LanguageRepository languageRepository;

    @GetMapping
    public List<FilmOutput> readAll() {
        return filmRepository.findAll()
                .stream()
                .map(FilmOutput::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FilmOutput readById(@PathVariable Short id) {
        return filmRepository.findById(id).map(FilmOutput::from)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such film with id %d.", id)
                ));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmOutput create(@Validated(Create.class) @RequestBody FilmInput data){

        final var film = new Film();
        film.setTitle(data.getTitle());
        film.setRating(data.getRating());
        film.setReleaseYear(data.getReleaseYear());
        film.setDescription(data.getDescription());
        Language language = languageRepository.findById(data.getLanguageId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        String.format("No language with id %d.", data.getLanguageId())
                ));
        film.setLanguage(language);
        final var saved = filmRepository.save(film);
        return FilmOutput.from(saved);

    }

    @PatchMapping("/{id}")
    public FilmOutput update(@Validated(Update.class) @RequestBody FilmInput data, @PathVariable Short id) {

        final var film = filmRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                String.format("No language with id %d.", data.getLanguageId())
        ));
        if (data.getTitle() != null) {
            film.setTitle(data.getTitle());
        }
        if (data.getDescription() != null) {
            film.setDescription(data.getDescription());
        }
        if (data.getLanguageId() != null) {
            Language language = languageRepository.findById(data.getLanguageId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            String.format("No language with id %d.", data.getLanguageId())
                    ));
            film.setLanguage(language);
        }
        if (data.getRating() != null) {
            film.setRating(data.getRating());
        }
        final var saved = filmRepository.save(film);
        return FilmOutput.from(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Short id) {
        if (!filmRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("No such film with id %d.", id)
            );
        }
        filmRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

