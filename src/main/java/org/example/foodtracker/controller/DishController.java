package org.example.foodtracker.controller;

import org.example.foodtracker.model.Dish;
import org.example.foodtracker.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(dishService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(dishService.getAll());
    }

    @PostMapping
    public ResponseEntity<Dish> create(@RequestBody @Validated Dish dish) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dishService.create(dish));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        dishService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
