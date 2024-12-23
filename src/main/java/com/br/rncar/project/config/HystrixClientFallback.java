package com.br.rncar.project.config;

import com.br.rncar.project.dto.ViaCepResponseDto;
import com.br.rncar.project.repositories.CepRepository;
import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback  implements CepRepository {
    @Override
    public ViaCepResponseDto findbyCep(String cep) throws Exception {
        throw  new Exception("Cep não encontrado");

    }
}
