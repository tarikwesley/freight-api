package com.sigabem.shipping.service;

import com.sigabem.shipping.model.Shipping;
import com.sigabem.shipping.model.ViaCepDTO;
import com.sigabem.shipping.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShippingService {
  @Autowired ConsumerViaCepService consumerViaCepService;
  @Autowired ShippingRepository freteRepository;

  public Shipping calculateDeadline(Shipping shipping) {
    ViaCepDTO responseZipCodeOrigin =
        consumerViaCepService.getInfoForZipCode(shipping.getZipCodeOrigin());
    ViaCepDTO responseZipCodeDestiny =
        consumerViaCepService.getInfoForZipCode(shipping.getZipCodeDestiny());
    shipping.setDateOfConsultation(LocalDate.now());

    if (responseZipCodeOrigin.getDdd().equals(responseZipCodeDestiny.getDdd())) {
      shipping.setDateOfExpectedDelivery(LocalDate.now().plusDays(1));
      return shipping;
    }

    if (responseZipCodeOrigin.getUf().equals(responseZipCodeDestiny.getUf())) {
      shipping.setDateOfExpectedDelivery(LocalDate.now().plusDays(3));
      return shipping;
    }

    if (!responseZipCodeOrigin.getUf().equals(responseZipCodeDestiny.getUf())) {
      shipping.setDateOfExpectedDelivery(LocalDate.now().plusDays(10));
      return shipping;
    }
    return shipping;
  }

  public Shipping calculatePrice(Shipping shipping) {
    ViaCepDTO responseZipCodeOrigin =
        consumerViaCepService.getInfoForZipCode(shipping.getZipCodeOrigin());
    ViaCepDTO responseZipCodeDestiny =
        consumerViaCepService.getInfoForZipCode(shipping.getZipCodeDestiny());

    if (responseZipCodeOrigin.getDdd().equals(responseZipCodeDestiny.getDdd())) {
      shipping.setValueOfShippingTotal(shipping.getWeight() - shipping.getWeight() * 0.50);
    }

    if (responseZipCodeOrigin.getUf().equals(responseZipCodeDestiny.getUf())) {
      shipping.setValueOfShippingTotal(shipping.getWeight() - shipping.getWeight() * 0.75);
    }

    if (!responseZipCodeOrigin.getUf().equals(responseZipCodeDestiny.getUf())) {
      shipping.setValueOfShippingTotal(shipping.getWeight());
    }
    return shipping;
  }

  public Shipping save(Shipping shipping) {
    return freteRepository.save(shipping);
  }
}
