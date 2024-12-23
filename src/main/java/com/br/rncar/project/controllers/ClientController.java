package com.br.rncar.project.controllers;

import com.br.rncar.project.domain.models.Client;
import com.br.rncar.project.dto.ClientResponseDto;
import com.br.rncar.project.dto.CreateClientDto;
import com.br.rncar.project.repositories.ClientRepository;
import com.br.rncar.project.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService, ClientRepository clientRepository) {

        this.clientService = clientService;

    }
    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody CreateClientDto createClientDto) {

        clientService.createClient(createClientDto);


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> getAllUsers() {
        var allUsers = clientService.getAllClients();
        return ResponseEntity.ok(allUsers);
    }
}
