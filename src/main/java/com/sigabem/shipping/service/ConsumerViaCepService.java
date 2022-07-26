package com.sigabem.shipping.service;

import com.sigabem.shipping.model.ViaCepDTO;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Service
public class ConsumerViaCepService {
  private CloseableHttpClient httpClient =
      HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
  private HttpComponentsClientHttpRequestFactory requestFactory =
      new HttpComponentsClientHttpRequestFactory();
  private RestTemplate restTemplate;
  private HttpHeaders headers = new HttpHeaders();

  public ConsumerViaCepService() {
    this.requestFactory.setHttpClient(httpClient);
    this.restTemplate = new RestTemplate(requestFactory);
    this.headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
  }

  public ViaCepDTO getInfoForZipCode(String zipCode) {
    UriComponents viaCepApi =
        UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("viacep.com.br")
            .path("/ws/" + zipCode + "/json/")
            .build();

    ViaCepDTO response = restTemplate.getForObject(viaCepApi.toString(), ViaCepDTO.class);
    return response;
  }
}
