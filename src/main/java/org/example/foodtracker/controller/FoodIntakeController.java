package org.example.foodtracker.controller;

import org.example.foodtracker.model.Dish;
import org.example.foodtracker.model.FoodIntake;
import org.example.foodtracker.service.FoodIntakeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/foodintake")
public class FoodIntakeController {

    private final FoodIntakeService foodIntakeService;

    public FoodIntakeController(FoodIntakeService foodIntakeService) {
        this.foodIntakeService = foodIntakeService;
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<Dish>> getListDishesByClientIdAndDate(@PathVariable Long clientId, @RequestParam LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK).body(foodIntakeService.getListDishesByClientIdAndDate(clientId, date));
    }

    @GetMapping("/{clientId}/report")
    public ResponseEntity<String> getReportByClientIdAndDate(@PathVariable Long clientId, @RequestParam LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK).body(foodIntakeService.getReportByClientIdAndDate(clientId, date));
    }

    @PostMapping()
    public ResponseEntity<FoodIntake> create(@RequestBody FoodIntake foodIntake) {
        return ResponseEntity.status(HttpStatus.CREATED).body(foodIntakeService.create(foodIntake));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        foodIntakeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
