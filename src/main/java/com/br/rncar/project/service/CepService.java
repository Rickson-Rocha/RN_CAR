package com.br.rncar.project.service;


import com.br.rncar.project.domain.models.Address;
import com.br.rncar.project.dto.ViaCepResponseDto;
import com.br.rncar.project.repositories.CepRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service

public class CepService {
    private static final Logger log = LoggerFactory.getLogger(CepService.class);
    private final CepRepository cepRepository;
    public CepService(CepRepository cepRepository) {
            this.cepRepository = cepRepository;
    }

    public void findZipcode(String cep) throws Exception {
         var  address = cepRepository.findbyCep(cep);
         log.info("Cep: {}", address);
         System.out.println(address);

    }

}
