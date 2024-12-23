package com.br.rncar.project.dto;

public record ViaCepResponseDto(String cep,String logradouro,String complemento,String unidade, String localidade,
                                String uf, String estado, String regiao) {
}
