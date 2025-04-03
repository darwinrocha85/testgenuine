package com.rocha.guerrero.testgenuine.service;

import org.springframework.stereotype.Service;
import com.rocha.guerrero.testgenuine.model.Client;
import com.rocha.guerrero.testgenuine.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rocha.guerrero.testgenuine.exception.ClientException;

import java.util.List;
import java.util.Optional;


@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }



    public Page<Client> findAllPage(
            int page,
            int size,
            String sortBy,
            String sortDirection
    ) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return clientRepository.findAll(pageable);
    }

    // Obtener una nave espacial por ID
    public ResponseEntity<Client> getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientException("Client with ID " + id + " not found"));
        return ResponseEntity.ok(client);
    }

    public Client getClientById2(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientException("Client with ID " + id + " not found"));
    }


    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientRepository.save(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    public ResponseEntity<Client> updateClient(Long id, Client clientDetails) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setData(clientDetails.getData());



            Client updatedClient = clientRepository.save(client);
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public Page<Client> searchClientsByFields(
            String name,
            int page,
            int size,
            String sortBy,
            String sortDirection
    ) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return clientRepository.findByFieldContainingIgnoreCase(name, pageable);
    }
}
