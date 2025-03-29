package org.example.foodtracker.service;

import org.example.foodtracker.enums.TargetEnum;
import org.example.foodtracker.model.Client;
import org.example.foodtracker.model.Dish;
import org.example.foodtracker.model.FoodIntake;
import org.example.foodtracker.model.TargetEntity;
import org.example.foodtracker.repository.ClientRepository;
import org.example.foodtracker.repository.DishRepository;
import org.example.foodtracker.repository.FoodIntakeRepository;
import org.example.foodtracker.repository.TargetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
public class FoodIntakeServiceTest {

    @Autowired
    private FoodIntakeService foodIntakeService;
    @Autowired
    private FoodIntakeRepository foodIntakeRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TargetRepository targetRepository;

    private Long count;

    @BeforeEach
    void BeforeEach() {
        targetRepository.deleteAll();
        count = 0L;
    }

    @Test
    void testGetReportByClientIdAndDate() {
        FoodIntake foodIntake = getFoodIntake();
        String expected = "Общее количество калорий за день = 50 ккал \n" +
                " Цель 1696 ккал\n" +
                "Вы немного недоели";

        foodIntakeRepository.save(foodIntake);

        String reportByClientIdAndDate = foodIntakeService.getReportByClientIdAndDate(foodIntake.getClient().getId(), LocalDate.now());

        assertEquals(expected, reportByClientIdAndDate);
    }

    private FoodIntake getFoodIntake() {
        count++;
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dishRepository.save(getDish()));
        dishes.add(dishRepository.save(getDish()));

        FoodIntake foodIntake = new FoodIntake();

        foodIntake.setDate(LocalDate.now());
        foodIntake.setClient(clientRepository.save(getClient()));
        foodIntake.setDishes(dishes);

        return foodIntake;
    }

    private Dish getDish() {
        count++;
        Dish dish = new Dish();
        dish.setName("Dish");
        dish.setFats(13.5);
        dish.setCarbohydrates(10.3);
        dish.setProteins(7.7);
        dish.setCaloriesPerServing(25L);
        return dish;
    }

    private Client getClient() {
        Client client = new Client();
        client.setName("User");
        client.setEmail("someEmail@mail.ru");
        client.setAge(26);
        client.setWeight(68);
        client.setHeight(175);

        TargetEntity targetEntity = new TargetEntity();
        targetEntity.setName(TargetEnum.MAINTENANCE.getName());
        client.setTarget(targetRepository.save(targetEntity));
        return client;
    }
}
