package com.sigabem.freight.service;

import com.sigabem.freight.model.Freight;
import com.sigabem.freight.model.ViaCepDTO;
import com.sigabem.freight.repository.FreightRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class FreightService {
    private final ConsumerViaCepService consumerViaCepService;
    private final FreightRepository freightRepository;

    private static final BigDecimal KILO_VALUE = BigDecimal.ONE;
    private static final BigDecimal FIFTY_PERCENT = new BigDecimal("0.5");
    private static final BigDecimal SEVENTY_FIVE_PERCENT = new BigDecimal("0.75");
    private static final Long ONE_DAY = 1L;
    private static final Long THREE_DAYS = 3L;
    private static final Long TEN_DAYS = 10L;

    public FreightService(ConsumerViaCepService consumerViaCepService, FreightRepository freightRepository) {
        this.consumerViaCepService = consumerViaCepService;
        this.freightRepository = freightRepository;
    }

    public void calculateDeadline(Freight freight) {
        ViaCepDTO responseZipCodeOrigin = consumerViaCepService.getInfoForZipCode(freight.getZipCodeOrigin());
        ViaCepDTO responseZipCodeDestiny = consumerViaCepService.getInfoForZipCode(freight.getZipCodeDestiny());
        freight.setDateOfConsultation(LocalDate.now());

        if (responseZipCodeOrigin.getAreaCode().equals(responseZipCodeDestiny.getAreaCode())) {
            freight.setDateOfExpectedDelivery(LocalDate.now().plusDays(ONE_DAY));
            return;
        }

        boolean stateOfOriginIsEqualsDestiny = responseZipCodeOrigin.getState().equals(responseZipCodeDestiny.getState());

        if (stateOfOriginIsEqualsDestiny) {
            freight.setDateOfExpectedDelivery(LocalDate.now().plusDays(THREE_DAYS));
        } else {
            freight.setDateOfExpectedDelivery(LocalDate.now().plusDays(TEN_DAYS));
        }
    }

    public void calculatePrice(Freight freight) {
        ViaCepDTO responseZipCodeOrigin = consumerViaCepService.getInfoForZipCode(freight.getZipCodeOrigin());
        ViaCepDTO responseZipCodeDestiny = consumerViaCepService.getInfoForZipCode(freight.getZipCodeDestiny());
        BigDecimal valueOfPackage = KILO_VALUE.multiply(BigDecimal.valueOf(freight.getWeight()));

        boolean zipCodeOfOriginIsEqualsDestiny = responseZipCodeOrigin.getAreaCode().equals(responseZipCodeDestiny.getAreaCode());

        if (zipCodeOfOriginIsEqualsDestiny) {
            BigDecimal valueOfDiscount = valueOfPackage.multiply(FIFTY_PERCENT);
            freight.setValueOfFreightTotal(valueOfPackage.subtract(valueOfDiscount).setScale(2, BigDecimal.ROUND_HALF_UP));
        }

        boolean stateOfOriginIsEqualsDestiny = responseZipCodeOrigin.getState().equals(responseZipCodeDestiny.getState());

        if (stateOfOriginIsEqualsDestiny && !zipCodeOfOriginIsEqualsDestiny) {
            BigDecimal valueOfDiscount = valueOfPackage.multiply(SEVENTY_FIVE_PERCENT);
            freight.setValueOfFreightTotal(valueOfPackage.subtract(valueOfDiscount).setScale(2, BigDecimal.ROUND_HALF_UP));
        }

        if (!zipCodeOfOriginIsEqualsDestiny && !stateOfOriginIsEqualsDestiny)
            freight.setValueOfFreightTotal(valueOfPackage.setScale(2, BigDecimal.ROUND_HALF_UP));

    }

    public Freight save(Freight freight) {
        return freightRepository.save(freight);
    }
}
