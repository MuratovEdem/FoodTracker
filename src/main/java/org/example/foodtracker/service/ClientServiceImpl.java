package org.example.foodtracker.service;

import org.example.foodtracker.exception.NotFoundException;
import org.example.foodtracker.model.Client;
import org.example.foodtracker.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final TargetService targetService;

    public ClientServiceImpl(ClientRepository clientRepository, TargetService targetService) {
        this.clientRepository = clientRepository;
        this.targetService = targetService;
    }

    public Client getById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElseThrow(() -> new NotFoundException("Client with id = " + id + " not found"));
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    public Client editTargetByClientIdAndTargetId(Long clientId, Long targetId) {
        Client client = getById(clientId);
        client.setTarget(targetService.getById(targetId));
        return clientRepository.save(client);
    }
}
