package com.br.rncar.project.dto;

import com.br.rncar.project.domain.models.Client;

import java.util.List;
import java.util.stream.Collectors;


public record ClientResponseDto(Long id,
                                String firstName,
                                String lastName,
                                String telephone,
                                String cpf,
                                List<CreateAddressDto> addresses,
                                List<CreateVehicleDto> vehicles) {
    public static ClientResponseDto fromEntity(Client client) {
        // Converte a lista de endereços
        List<CreateAddressDto> addressDtos = client.getAddressList().stream()
                .map(address -> new CreateAddressDto(
                        address.getZipCode(),
                        address.getStreet(),
                        address.getComplement(),
                        address.getCity(),
                        address.getState()
                ))
                .collect(Collectors.toList());

        // Converte a lista de veículos
        List<CreateVehicleDto> vehicleDtos = client.getVehicleList().stream()
                .map(vehicle -> new CreateVehicleDto(
                        vehicle.getPlate(),
                        vehicle.getModel(),
                        vehicle.getYear(),
                        vehicle.getColor()
                ))
                .collect(Collectors.toList());

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
