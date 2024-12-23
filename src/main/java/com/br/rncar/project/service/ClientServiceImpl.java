package com.br.rncar.project.service;

import com.br.rncar.project.domain.models.Address;
import com.br.rncar.project.domain.models.Client;
import com.br.rncar.project.domain.models.Vehicle;
import com.br.rncar.project.dto.ClientResponseDto;
import com.br.rncar.project.dto.CreateAddressDto;
import com.br.rncar.project.dto.CreateClientDto;
import com.br.rncar.project.dto.CreateVehicleDto;
import com.br.rncar.project.repositories.AddressRepository;
import com.br.rncar.project.repositories.ClientRepository;
import com.br.rncar.project.repositories.VehicleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger log = LoggerFactory.getLogger(AddressService.class);
    private final ClientRepository clientRepository;
    private final AddressService addressService;
    private final AddressRepository addressRepository;
    private final VehicleRepository vehicleRepository;

    public ClientServiceImpl(ClientRepository clientRepository, AddressService addressService,
                             AddressRepository addressRepository, VehicleRepository vehicleRepository) {
        this.clientRepository = clientRepository;
        this.addressService = addressService;
        this.addressRepository = addressRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    @Transactional
    public void createClient(CreateClientDto clientDto) {
        Client client = clientDto.toEntity();

        // Persiste o cliente para gerar o ID
        client = clientRepository.save(client);

        // Converte e persiste os endereços
        List<Address> addressList = clientDto.toAddressEntities(client,addressService);
        addressRepository.saveAll(addressList);

        // Converte e persiste os veículos
        List<Vehicle> vehicleList = clientDto.toVehicleEntities(client);
        vehicleRepository.saveAll(vehicleList);

        // Atualiza as listas no cliente e salva novamente
        client.setAddressList(addressList);
        client.setVehicleList(vehicleList);
        clientRepository.save(client);

        log.info("Cliente criado com sucesso: {}", client.toString());
    }

    @Override
    public List<ClientResponseDto> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(ClientResponseDto::fromEntity)
                .collect(Collectors.toList());
    }


    private ClientResponseDto toClientResponseDto(Client client) {
        // Converte a lista de endereços
        List<CreateAddressDto> addressDtos = new ArrayList<>();
        for (Address address : client.getAddressList()) {
            addressDtos.add(new CreateAddressDto(
                    address.getZipCode(),
                    address.getStreet(),
                    address.getComplement(),
                    address.getCity(),
                    address.getState()
            ));
        }

        // Converte a lista de veículos
        List<CreateVehicleDto> vehicleDtos = new ArrayList<>();
        for (Vehicle vehicle : client.getVehicleList()) {
            vehicleDtos.add(new CreateVehicleDto(
                    vehicle.getPlate(),
                    vehicle.getModel(),
                    vehicle.getYear(),
                    vehicle.getColor()
            ));
        }

        // Retorna o DTO do cliente
        return new ClientResponseDto(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getTelephone(),
                client.getCpf(),
                addressDtos,
                vehicleDtos
        );
    }




}

