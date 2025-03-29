package org.example.foodtracker.service;

import org.example.foodtracker.model.TargetEntity;

import java.util.List;

public interface TargetService {

    TargetEntity getById(Long id);

    List<TargetEntity> getAll();

    TargetEntity create(TargetEntity target);

    void deleteById(Long id);
}
