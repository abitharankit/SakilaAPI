//package com.example.sakila.controllers;
//
//
//import com.example.sakila.dto.output.CategoryOutput;
//import com.example.sakila.repositories.CategoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/categories")
//public class CategoryController {
//    @Autowired
//    private CategoryRepository categoryRepository;
//    @GetMapping
//    public List<CategoryOutput> readAll(){
//        return categoryRepository.findAll()
//                .stream()
//                .map(CategoryOutput::from)
//                .collect(Collectors.toList());
//    }
//}
