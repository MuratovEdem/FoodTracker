package org.example.foodtracker.repository;

import org.example.foodtracker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
