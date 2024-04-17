package com.example.sakila.dto.input;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import static com.example.sakila.dto.input.ValidationGroup.Create;

@Data
public class FilmInput {
    @NotNull(groups = {Create.class})
    @Size(min = 1, max = 128)
    private String title;

    @NotNull(groups = {Create.class})
    @Min(1)
    @Max(127)
    private Byte languageId;

    @Size(min=1 , max = 500)
    private String description;

    @Size(min = 1, max = 6)
    private String rating;

    @Min(1)
    private Integer releaseYear;

}
