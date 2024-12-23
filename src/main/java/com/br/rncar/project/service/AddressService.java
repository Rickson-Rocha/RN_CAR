package com.br.rncar.project.service;


import com.br.rncar.project.domain.models.Address;
import com.br.rncar.project.dto.ViaCepResponseDto;
import com.br.rncar.project.repositories.AddressRepository;
import com.br.rncar.project.repositories.CepRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private static final Logger log = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;
    private final CepRepository cepRepository;

    public AddressService(AddressRepository addressRepository, CepRepository cepRepository) {
        this.addressRepository = addressRepository;
        this.cepRepository = cepRepository;
    }

    public Address findAndSaveAddress(String cep) throws Exception {

        ViaCepResponseDto viaCepResponse = cepRepository.findbyCep(cep);


        Address address = new Address();
        address.setZipCode(viaCepResponse.cep());
        address.setStreet(viaCepResponse.logradouro());
        address.setComplement(viaCepResponse.complemento());
        address.setCity(viaCepResponse.localidade());
        address.setState(viaCepResponse.uf());

        return addressRepository.save(address);
    }
}
