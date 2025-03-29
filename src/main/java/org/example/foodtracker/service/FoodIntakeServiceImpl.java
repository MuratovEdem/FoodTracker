package org.example.foodtracker.service;

import org.example.foodtracker.enums.TargetEnum;
import org.example.foodtracker.model.Client;
import org.example.foodtracker.model.Dish;
import org.example.foodtracker.model.FoodIntake;
import org.example.foodtracker.repository.FoodIntakeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodIntakeServiceImpl implements FoodIntakeService {

    private final FoodIntakeRepository foodIntakeRepository;
    private final ClientService clientService;
    private final DishService dishService;

    public FoodIntakeServiceImpl(FoodIntakeRepository foodIntakeRepository, ClientService clientService, DishService dishService) {
        this.foodIntakeRepository = foodIntakeRepository;
        this.clientService = clientService;
        this.dishService = dishService;
    }

    public FoodIntake create(FoodIntake foodIntake) {
        Client client = clientService.getById(foodIntake.getClient().getId());

        List<Dish> dishes = new ArrayList<>();
        for (int i = 0; i < foodIntake.getDishes().size(); i++) {
            dishes.add(dishService.getById(foodIntake.getDishes().get(i).getId()));
        }

        foodIntake.setClient(client);
        foodIntake.setDishes(dishes);

        return foodIntakeRepository.save(foodIntake);
    }

    public List<Dish> getListDishesByClientIdAndDate(Long clientId, LocalDate date) {
        return foodIntakeRepository.findListDishesByDate(clientId, date);
    }

    public String getReportByClientIdAndDate(Long clientId, LocalDate date) {
        String report = "";

        Client client = clientService.getById(clientId);
        List<Dish> listDishesByClientIdAndDate = getListDishesByClientIdAndDate(clientId, date);

        Long harrisBenedictFormula = (long) (66 + (13.7 * client.getWeight() + (5L * client.getHeight()) - (6.76 * client.getAge())));

        if (client.getTarget().getName().equals(TargetEnum.GAIN.getName())) {
            harrisBenedictFormula = (long) (harrisBenedictFormula * 0.85);
        } else if(client.getTarget().getName().equals(TargetEnum.LOSS.getName())) {
            harrisBenedictFormula = (long) (harrisBenedictFormula * 1.15);
        }

        Long totalCaloriesPerServing = 0L;

        for (Dish dish : listDishesByClientIdAndDate) {
            totalCaloriesPerServing += dish.getCaloriesPerServing();
        }
        report += "Общее количество калорий за день = " + totalCaloriesPerServing + " ккал \n Цель " + harrisBenedictFormula + " ккал\n";

        if (totalCaloriesPerServing.equals(harrisBenedictFormula)) {
            report += "Вы уложились в дневную норму калорий";
        } else if (totalCaloriesPerServing > harrisBenedictFormula) {
            report += "Вы немного перели";
        } else {
            report += "Вы немного недоели";
        }

        return report;
    }

    public void deleteById(Long id) {
        foodIntakeRepository.deleteById(id);
    }
}
