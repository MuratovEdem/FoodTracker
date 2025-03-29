package org.example.foodtracker.controller;

import org.example.foodtracker.model.Dish;
import org.example.foodtracker.service.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
public class DishControllerTest {

    @MockitoBean
    private DishService dishService;
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
    void testGetById_Ok() {
        Dish dish = getDish();

        doReturn(dish).when(dishService).getById(anyLong());

        ResponseEntity<Dish> actual = restClient.get()
                .uri("/dishes/" + dish.getId())
                .retrieve()
                .toEntity(Dish.class);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        Dish body = actual.getBody();
        assertNotNull(body);
        assertEquals(dish.getId(), body.getId());
    }

    @Test
    void testGetAll_Ok() {
        Dish dish = getDish();
        Dish dish1 = getDish();

        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish);
        dishes.add(dish1);

        doReturn(dishes).when(dishService).getAll();

        assertEquals(dishes.size(), restClient.get()
                .uri("/dishes")
                .retrieve()
                .toEntity(List.class)
                .getBody()
                .size());
    }

    @Test
    void testDeleteById_AllOk() {
        doNothing().when(dishService).deleteById(anyLong());

        ResponseEntity<Void> bodilessEntity = restClient.delete()
                .uri("/dishes/" + 1L)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.NO_CONTENT, bodilessEntity.getStatusCode());
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
}
