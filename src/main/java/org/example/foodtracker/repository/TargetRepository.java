package org.example.foodtracker.repository;

import org.example.foodtracker.model.TargetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepository extends JpaRepository<TargetEntity, Long> {
}
