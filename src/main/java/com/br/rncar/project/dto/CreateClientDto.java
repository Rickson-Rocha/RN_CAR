package com.br.rncar.project.dto;

import com.br.rncar.project.domain.models.Address;
import com.br.rncar.project.domain.models.Client;
import com.br.rncar.project.domain.models.Vehicle;
import com.br.rncar.project.service.AddressService;


import java.util.ArrayList;
import java.util.List;

public record CreateClientDto(String firstName,
                              String lastName,
                              String telephone,
                              String cpf,
                              List<CreateAddressDto> address,
                              List<CreateVehicleDto> vehicles) {

    // Método para converter CreateClientDto em Client (parcialmente preenchido)
    public Client toEntity() {
        Client client = new Client();
        client.setFirstName(this.firstName);
        client.setLastName(this.lastName);
        client.setTelephone(this.telephone);
        client.setCpf(this.cpf);
        return client;
    }


    public List<Address> toAddressEntities(Client client, AddressService addressService) {
        List<Address> addressList = new ArrayList<>();
        for (CreateAddressDto addressDto : this.address) {
            try {
                // Buscar e salvar o endereço usando o addressService
                Address address = addressService.findAndSaveAddress(addressDto.zipCode());
                address.setClient(client);
                addressList.add(address);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao buscar ou salvar o endereço: " + addressDto.zipCode(), e);
            }
        }
        return addressList;
    }


    public List<Vehicle> toVehicleEntities(Client client) {
        List<Vehicle> vehicleList = new ArrayList<>();
        this.vehicles.forEach(vehicleDto -> {
            Vehicle vehicle = new Vehicle();
            vehicle.setPlate(vehicleDto.plate());
            vehicle.setModel(vehicleDto.model());
            vehicle.setColor(vehicleDto.color());
            vehicle.setYear(vehicleDto.year());
            vehicle.setClient(client); // Associa ao cliente
            vehicleList.add(vehicle);
        });
        return vehicleList;
    }
}
