package org.example.foodtracker.controller;

import org.example.foodtracker.model.Client;
import org.example.foodtracker.model.Dish;
import org.example.foodtracker.model.FoodIntake;
import org.example.foodtracker.service.FoodIntakeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
public class FoodIntakeControllerTest {

    @MockitoBean
    private FoodIntakeService foodIntakeService;
    private Long count;

    @LocalServerPort
    private int port;
    private RestClient restClient;

    @BeforeEach
    void beforeEach() {
        restClient = RestClient.create("http://localhost:" + port);
        count = 0L;
    }

    @Test
    void getListDishesByClientIdAndDate_Ok() {
        FoodIntake foodIntake = getFoodIntake();

        doReturn(foodIntake.getDishes()).when(foodIntakeService).getListDishesByClientIdAndDate(anyLong(), any());

        assertEquals(foodIntake.getDishes().size(), restClient.get()
                .uri("/foodintake/" + foodIntake.getId() + "?date=" + foodIntake.getDate())
                .retrieve()
                .toEntity(List.class)
                .getBody()
                .size());
    }

    @Test
    void testDeleteById_AllOk() {
        doNothing().when(foodIntakeService).deleteById(anyLong());

        ResponseEntity<Void> bodilessEntity = restClient.delete()
                .uri("/foodintake/" + 1L)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.NO_CONTENT, bodilessEntity.getStatusCode());
    }

    private FoodIntake getFoodIntake() {
        count++;
        List<Dish> dishes = new ArrayList<>();
        dishes.add(getDish());
        dishes.add(getDish());

        FoodIntake foodIntake = new FoodIntake();
        foodIntake.setId(count);
        foodIntake.setDate(LocalDate.now());
        foodIntake.setClient(getClient());
        foodIntake.setDishes(dishes);

        return foodIntake;
    }

    private Dish getDish() {
        count++;
        Dish dish = new Dish();
        dish.setId(count);
        dish.setName("Dish" + count);
        dish.setFats(13.5);
        dish.setCarbohydrates(10.3);
        dish.setProteins(7.7);
        dish.setCaloriesPerServing(25L);
        return dish;
    }

    private Client getClient() {
        count++;
        Client client = new Client();
        client.setId(count);
        client.setName("User" + count);
        client.setAge(26);
        client.setHeight(175);
        client.setWeight(68);
        client.setEmail("someEmail@mail.ru");
        return client;
    }
}
