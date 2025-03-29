package org.example.foodtracker.repository;

import org.example.foodtracker.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
