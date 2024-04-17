package com.example.sakila.dto.output;

import com.example.sakila.entities.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class LanguageOutput {
    private Byte id;
    private String name;

    public static LanguageOutput from (Language language) {
        return new LanguageOutput(
                language.getId(),
                language.getName()
        );
    }
}
