package com.br.rncar.project.service;

import com.br.rncar.project.domain.models.Client;
import com.br.rncar.project.dto.ClientResponseDto;
import com.br.rncar.project.dto.CreateClientDto;

import java.util.List;

public interface ClientService {


    public void createClient(CreateClientDto clientDto);
    public List<ClientResponseDto> getAllClients();
}
