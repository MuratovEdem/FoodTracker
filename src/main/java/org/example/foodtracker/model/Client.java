package org.example.foodtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Имя не может быть пустым")
    private String name;
    private String email;

    @Positive(message = "Возраст должен быть положительным числом")
    @Max(value = 150)
    private int age;

    @Positive(message = "Вес должен быть положительным числом")
    @Max(value = 300)
    private int weight;

    @Positive(message = "Рост должен быть положительным числом")
    @Max(value = 300)
    private int height;

    @ManyToOne(fetch = FetchType.EAGER)
    private TargetEntity target;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FoodIntake> foodIntakes = new ArrayList<>();
}
