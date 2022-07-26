package com.sigabem.shipping.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Shipping {
  @Id private String id;
  private String nameOfReciver;
  private String zipCodeOrigin;
  private String zipCodeDestiny;
  private Double weight;
  private Double valueOfShippingTotal;
  private LocalDate dateOfExpectedDelivery;
  private LocalDate dateOfConsultation;
}