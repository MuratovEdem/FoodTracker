package org.example.foodtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Название блюда не может быть пустым")
    private String name;

    @Positive(message = "Калории должны быть положительным числом")
    private Long caloriesPerServing;

    @PositiveOrZero(message = "Белки должны быть положительными или нулевыми")
    private double proteins;

    @PositiveOrZero(message = "Жиры должны быть положительными или нулевыми")
    private double fats;

    @PositiveOrZero(message = "Углеводы должны быть положительными или нулевыми")
    private double carbohydrates;

    @ManyToMany
    @JoinTable(name = "food_intake_dishes",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "food_intake_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<FoodIntake> foodIntakes = new ArrayList<>();
}
