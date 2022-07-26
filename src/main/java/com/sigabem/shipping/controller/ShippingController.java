package com.sigabem.shipping.controller;

import com.sigabem.shipping.model.Shipping;
import com.sigabem.shipping.model.ShippingDTO;
import com.sigabem.shipping.model.ViaCepDTO;
import com.sigabem.shipping.service.ConsumerViaCepService;
import com.sigabem.shipping.service.ShippingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ShippingController {

  @Autowired ConsumerViaCepService consumerViaCepService;
  @Autowired ShippingService freteService;

  @GetMapping("/{zipCode}")
  public ResponseEntity<ViaCepDTO> listShipping(@PathVariable(value = "zipCode") String zipCode) {
    ViaCepDTO response = consumerViaCepService.getInfoForZipCode(zipCode);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping("shipping")
  public ResponseEntity<Shipping> cauculateShipping(@RequestBody ShippingDTO shippingDTO) {
    Shipping shipping = new Shipping();
    BeanUtils.copyProperties(shippingDTO, shipping);
    freteService.calculateDeadline(shipping);
    freteService.calculatePrice(shipping);
    return ResponseEntity.status(HttpStatus.OK).body(freteService.save(shipping));
  }
}
