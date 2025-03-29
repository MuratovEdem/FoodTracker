package org.example.foodtracker.service;

import org.example.foodtracker.enums.TargetEnum;
import org.example.foodtracker.exception.NotFoundException;
import org.example.foodtracker.model.TargetEntity;
import org.example.foodtracker.repository.TargetRepository;
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
public class TargetServiceTest {

    @Autowired
    private TargetService targetService;
    @Autowired
    private TargetRepository targetRepository;

    @BeforeEach
    void BeforeEach() {
        targetRepository.deleteAll();
    }

    @Test
    void testGetById_Ok() {
        TargetEntity target = getTarget();

        targetRepository.save(target);

        TargetEntity actual = targetService.getById(target.getId());

        assertEquals(target.getId(), actual.getId());
        assertEquals(target.getName(), actual.getName());
    }

    @Test
    void testGetById_ExpectedException() {
        assertThrows(NotFoundException.class, () -> targetService.getById(1L));
    }

    @Test
    void testGetAll_Ok() {
        TargetEntity target = getTarget();
        TargetEntity target1 = getTarget();

        targetRepository.save(target);
        targetRepository.save(target1);

        List<TargetEntity> dishList = targetService.getAll();

        assertEquals(dishList.size(), 2);
    }

    @Test
    void testDeleteById_AllOk() {
        TargetEntity target = getTarget();

        TargetEntity actual = targetRepository.save(target);

        targetService.deleteById(actual.getId());

        assertTrue(targetRepository.findById(actual.getId()).isEmpty());
    }

    private TargetEntity getTarget() {
        TargetEntity targetEntity = new TargetEntity();
        targetEntity.setName(TargetEnum.MAINTENANCE.getName());

        return targetEntity;
    }
}
