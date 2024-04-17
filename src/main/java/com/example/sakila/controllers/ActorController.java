package com.example.sakila.controllers;

import com.example.sakila.dto.input.ActorInput;
import com.example.sakila.dto.output.ActorOutput;
import com.example.sakila.entities.Actor;
import com.example.sakila.repositories.ActorRepository;
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
@RequestMapping("/actors")
public class ActorController {
    @Autowired
    private ActorRepository actorRepository;
    @GetMapping
    public List<ActorOutput> readAll() {
        //return actorRepository.findAll();
        return actorRepository.findAll()
                .stream()
                .map(ActorOutput::from)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public ActorOutput readById(@PathVariable Short id) {
        return actorRepository.findById(id)
                .map(ActorOutput::from)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such actor with id %d.", id)
                ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  ActorOutput create(@Validated(Create.class) @RequestBody ActorInput data){
        final var actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());
        final var saved = actorRepository.save(actor);
        return ActorOutput.from(saved);
    }

    @PatchMapping("/{id}")
    public ActorOutput update(@Validated(Update.class) ActorInput data, @PathVariable Short id) {
        if (!actorRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("No such actor with id %d.", id)
            );
        }
        final var actor = actorRepository.findById(id).get();
        if (data.getFirstName() != null) {
            actor.setFirstName(data.getFirstName());
        }
        if (data.getLastName() != null) {
            actor.setLastName(data.getFirstName());
        }
        final var saved = actorRepository.save(actor);
        return ActorOutput.from(saved);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Short id) {
        if (!actorRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("No such actor with id %d.", id)
            );
        }
        actorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

