package com.sigabem.freight.service;

import com.sigabem.freight.exception.ViaCepException;
import com.sigabem.freight.model.ViaCepDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerViaCepService {

    @Value("${url.viacep}")
    private String urlViaCep;
    private final RestTemplate restTemplate;

    public ConsumerViaCepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ViaCepDTO getInfoForZipCode(String zipCode) {
        try {
            var url = urlViaCep + zipCode + "/json/";
            return restTemplate.getForObject(url, ViaCepDTO.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ViaCepException("Error when searching for zip code information");
        }
    }
}
