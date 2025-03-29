package org.example.foodtracker;

import org.example.foodtracker.enums.TargetEnum;
import org.example.foodtracker.model.TargetEntity;
import org.example.foodtracker.service.TargetService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FoodTrackerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FoodTrackerApplication.class, args);

		fillDb(context);
	}

	private static void fillDb(ConfigurableApplicationContext context) {
		TargetService targetService = context.getBean(TargetService.class);

		TargetEntity targetGain = new TargetEntity();
		targetGain.setName(TargetEnum.GAIN.getName());

		TargetEntity targetLoss = new TargetEntity();
		targetLoss.setName(TargetEnum.LOSS.getName());

		TargetEntity targetMaintenance = new TargetEntity();
		targetMaintenance.setName(TargetEnum.MAINTENANCE.getName());

		targetService.create(targetGain);
		targetService.create(targetLoss);
		targetService.create(targetMaintenance);

	}

}
