package com.example.sakila.entities;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.Locale;

@Entity
@Table(name="category")
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Short id;
    @Column(name="name")
    private String name;

}
