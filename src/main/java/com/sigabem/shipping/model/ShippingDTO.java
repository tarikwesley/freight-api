package com.sigabem.shipping.model;

import lombok.Getter;

@Getter
public class ShippingDTO {
    private String nameOfReciver;
    private String zipCodeOrigin;
    private String zipCodeDestiny;
    private Double weight;
}