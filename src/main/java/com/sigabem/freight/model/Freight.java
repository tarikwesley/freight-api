package com.sigabem.freight.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "freight")
public class Freight {
    @Id
    private String id;
    private String nameOfReceiver;
    private String zipCodeOrigin;
    private String zipCodeDestiny;
    private Double weight;
    private BigDecimal valueOfFreightTotal;
    private LocalDate dateOfExpectedDelivery;
    private LocalDate dateOfConsultation;

    public Freight(FreightDTO freightDTO) {
        this.nameOfReceiver = freightDTO.getNameOfReceiver();
        this.zipCodeOrigin = freightDTO.getZipCodeOrigin();
        this.zipCodeDestiny = freightDTO.getZipCodeDestiny();
        this.weight = freightDTO.getWeight();
    }
}