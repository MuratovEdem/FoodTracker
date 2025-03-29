package org.example.foodtracker.service;

import org.example.foodtracker.model.Dish;

import java.util.List;

public interface DishService {
    Dish getById(Long id);

    List<Dish> getAll();

    Dish create(Dish dish);

    void deleteById(Long id);
}
