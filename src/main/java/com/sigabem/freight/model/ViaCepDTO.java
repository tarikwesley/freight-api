package com.sigabem.freight.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ViaCepDTO {
    @JsonProperty("localidade")
    private String locality;
    @JsonProperty("cep")
    private String zipCode;
    @JsonProperty("uf")
    private String state;
    @JsonProperty("ddd")
    private String areaCode;
}
