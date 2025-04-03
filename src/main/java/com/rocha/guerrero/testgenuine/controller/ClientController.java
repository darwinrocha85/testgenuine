package com.rocha.guerrero.testgenuine.controller;

import com.rocha.guerrero.testgenuine.model.Client;
import com.rocha.guerrero.testgenuine.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Obtener todos los registros
    @GetMapping("")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // Obtener todos los registros con paginación
    @GetMapping("/page")
    public Page<Client> getAllClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {

        return clientService.findAllPage(page, size, sortBy, sortDirection);
    }

    // Obtener un registro por ID
    @Cacheable(value = "Clients", key = "#id")
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    // Crear un nuevo registro
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {



        return clientService.createClient(client);

    }

    // Actualizar un registro existente
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client ClientDetails) {
        return clientService.updateClient(id, ClientDetails);

    }

    // Eliminar un registro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        return clientService.deleteClient(id);
    }

    // Obtener todos los registros que contienen un texto en su nombre con paginación
    @GetMapping("/search")
    public Page<Client> searchClientsByFields(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        return clientService.searchClientsByFields(name, page, size, sortBy, sortDirection);
    }
}
