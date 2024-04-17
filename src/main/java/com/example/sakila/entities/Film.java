package com.example.sakila.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="film")
@Getter
@Setter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="film_id")
    private Short id;
    @Column(name="title")
    private String title;
    @ManyToOne
    @JoinColumn(name="language_id")
    private Language language;
    @Column(name="description")
    private String description;
    //This would need to be an enum but can be stored in a string will throw an error if you attempt to store a value outside of one of the possible values.
    @Column(name="rating")
    private String rating;
    @Column(name="release_year")
    private Integer releaseYear;

    @ManyToMany(mappedBy = "films", cascade = CascadeType.ALL)
    private List<Actor> cast = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = {@JoinColumn(name="film_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> filmCatagories = new ArrayList<>();


}
