package org.example.foodtracker.service;

import org.example.foodtracker.model.Dish;
import org.example.foodtracker.model.FoodIntake;

import java.time.LocalDate;
import java.util.List;

public interface FoodIntakeService {
    FoodIntake create(FoodIntake foodIntake);

    List<Dish> getListDishesByClientIdAndDate(Long clientId, LocalDate date);

    String getReportByClientIdAndDate(Long clientId, LocalDate date);

    void deleteById(Long id);
}