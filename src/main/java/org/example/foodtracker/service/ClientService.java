package org.example.foodtracker.service;

import org.example.foodtracker.model.Client;

import java.util.List;

public interface ClientService {

    Client getById(Long id);

    List<Client> getAll();

    Client create(Client client);

    void deleteById(Long id);

    Client editTargetByClientIdAndTargetId(Long clientId, Long targetId);
}
