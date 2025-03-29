package org.example.foodtracker.service;

import org.example.foodtracker.exception.NotFoundException;
import org.example.foodtracker.model.Dish;
import org.example.foodtracker.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish getById(Long id) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        return optionalDish.orElseThrow(() -> new NotFoundException("Dish with id = " + id + " not found"));
    }

    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    public Dish create(Dish dish) {
        return dishRepository.save(dish);
    }

    public void deleteById(Long id) {
        dishRepository.deleteById(id);
    }
}
