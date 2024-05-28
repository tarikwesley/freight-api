package com.sigabem.freight.model;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class FreightDTO {
    @NotNull
    private String nameOfReceiver;
    @NotNull
    @Pattern(regexp = "\\d{8}", message = "The zip code must be in the format XXXXXXXX")
    private String zipCodeOrigin;
    @NotNull
    @Pattern(regexp = "\\d{8}", message = "The zip code must be in the format XXXXXXXX")
    private String zipCodeDestiny;
    @NotNull
    private Double weight;
}