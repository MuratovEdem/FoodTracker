package org.example.foodtracker.service;

import org.example.foodtracker.exception.NotFoundException;
import org.example.foodtracker.model.Dish;
import org.example.foodtracker.repository.DishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
public class DishServiceTest {

    @Autowired
    private DishService dishService;
    @Autowired
    private DishRepository dishRepository;

    private Long count;

    @BeforeEach
    void BeforeEach() {
        dishRepository.deleteAll();
        count = 0L;
    }

    @Test
    void testGetById_AllOk() {
        Dish dish = getDish();

        dishRepository.save(dish);

        Dish actual = dishService.getById(dish.getId());

        assertEquals(dish.getId(), actual.getId());
        assertEquals(dish.getName(), actual.getName());
    }

    @Test
    void testGetById_ExpectedException() {
        assertThrows(NotFoundException.class, () -> dishService.getById(1L));
    }

    @Test
    void testGetAll_Ok() {
        Dish dish = getDish();
        Dish dish1 = getDish();

        dishRepository.save(dish);
        dishRepository.save(dish1);

        List<Dish> dishList = dishService.getAll();

        assertEquals(dishList.size(), 2);
    }

    @Test
    void testDeleteById_AllOk() {
        Dish dish = getDish();

        Dish actual = dishRepository.save(dish);

        dishService.deleteById(actual.getId());

        assertTrue(dishRepository.findById(actual.getId()).isEmpty());
    }

    private Dish getDish() {
        count++;
        Dish dish = new Dish();
        dish.setName("Dish" + count);
        dish.setFats(13.5);
        dish.setCarbohydrates(10.3);
        dish.setProteins(7.7);
        dish.setCaloriesPerServing(25L);
        return dish;
    }
}
