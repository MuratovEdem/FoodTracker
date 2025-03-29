package org.example.foodtracker.service;

import org.example.foodtracker.exception.NotFoundException;
import org.example.foodtracker.model.Client;
import org.example.foodtracker.repository.ClientRepository;
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
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;

    private Long count;

    @BeforeEach
    void BeforeEach() {
        clientRepository.deleteAll();
        count = 0L;
    }

    @Test
    void testGetById_AllOk() {
        Client client = getClient();

        clientRepository.save(client);

        Client actual = clientService.getById(client.getId());

        assertEquals(client.getId(), actual.getId());
        assertEquals(client.getName(), actual.getName());
    }

    @Test
    void testGetById_ExpectedException() {
        assertThrows(NotFoundException.class, () -> clientService.getById(1L));
    }

    @Test
    void testGetAll_Ok() {
        Client client = getClient();
        Client client1 = getClient();

        clientRepository.save(client);
        clientRepository.save(client1);

        List<Client> clientList = clientService.getAll();

        assertEquals(clientList.size(), 2);
    }

    @Test
    void testDeleteById_AllOk() {
        Client client = getClient();

        Client actual = clientRepository.save(client);

        clientService.deleteById(actual.getId());

        assertTrue(clientRepository.findById(actual.getId()).isEmpty());
    }

    private Client getClient() {
        count++;
        Client client = new Client();
        client.setName("Client" + count);
        client.setAge((int) (26 + count));
        client.setHeight((int) (175 + count));
        client.setWeight((int) (68 + count));
        client.setEmail("someEmail@mail.ru");
        return client;
    }
}
