package org.example.foodtracker.service;

import org.example.foodtracker.exception.NotFoundException;
import org.example.foodtracker.model.TargetEntity;
import org.example.foodtracker.repository.TargetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TargetServiceImpl implements TargetService {

    private final TargetRepository targetRepository;

    public TargetServiceImpl(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Override
    public TargetEntity getById(Long id) {
        Optional<TargetEntity> optionalTargetEntity = targetRepository.findById(id);
        return optionalTargetEntity.orElseThrow(() -> new NotFoundException("Target with id = " + id + " not found"));
    }

    @Override
    public List<TargetEntity> getAll() {
        return targetRepository.findAll();
    }

    @Override
    public TargetEntity create(TargetEntity target) {
        return targetRepository.save(target);
    }

    @Override
    public void deleteById(Long id) {
        targetRepository.deleteById(id);
    }
}
