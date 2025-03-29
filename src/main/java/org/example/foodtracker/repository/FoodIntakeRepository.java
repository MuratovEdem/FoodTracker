package org.example.foodtracker.repository;

import org.example.foodtracker.model.Dish;
import org.example.foodtracker.model.FoodIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FoodIntakeRepository extends JpaRepository<FoodIntake, Long> {

    @Query(nativeQuery = true, value = "select d.* from food_intake fi " +
            "join food_intake_dishes fid on fi.id = fid.food_intake_id " +
            "join dishes d ON fid.dish_id = d.id " +
            "where fi.client_id = :clientId and fi.date = :date")
    List<Dish> findListDishesByDate(Long clientId, LocalDate date);
}
