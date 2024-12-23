package com.br.rncar.project.repositories;

import com.br.rncar.project.config.HystrixClientFallback;
import com.br.rncar.project.domain.models.Address;
import com.br.rncar.project.dto.ViaCepResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep",url = "https://viacep.com.br/ws/", fallback = HystrixClientFallback.class)
public interface CepRepository {

    @GetMapping("{cep}/json/")
    ViaCepResponseDto findbyCep(@PathVariable("cep")String cep) throws Exception;
}
