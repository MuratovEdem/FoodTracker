package org.example.foodtracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "food_intake")
public class FoodIntake {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotBlank
    private Client client;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "food_intake_dishes",
        joinColumns = @JoinColumn(name = "food_intake_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"))
    @NotBlank
    private List<Dish> dishes = new ArrayList<>();
}
