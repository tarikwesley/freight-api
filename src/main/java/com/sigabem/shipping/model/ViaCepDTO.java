package com.sigabem.shipping.model;


import lombok.Getter;

@Getter
public class ViaCepDTO {
    private String localidade;
    private String cep;
    private String uf;
    private String ddd;
}