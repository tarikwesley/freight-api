package com.sigabem.freight.controller;

import com.sigabem.freight.model.Freight;
import com.sigabem.freight.model.FreightDTO;
import com.sigabem.freight.model.ViaCepDTO;
import com.sigabem.freight.service.ConsumerViaCepService;
import com.sigabem.freight.service.FreightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FreightController {

    private final ConsumerViaCepService consumerViaCepService;
    private final FreightService freightService;

    public FreightController(ConsumerViaCepService consumerViaCepService, FreightService freightService) {
        this.consumerViaCepService = consumerViaCepService;
        this.freightService = freightService;
    }

    @GetMapping("/{zipCode}")
    public ResponseEntity<ViaCepDTO> listFreight(@PathVariable(value = "zipCode") String zipCode) {
        ViaCepDTO response = consumerViaCepService.getInfoForZipCode(zipCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/freight")
    public ResponseEntity<Freight> calculateFreight(@RequestBody FreightDTO freightDTO) {
        Freight freight = new Freight(freightDTO);
        freightService.calculateDeadline(freight);
        freightService.calculatePrice(freight);
        return ResponseEntity.status(HttpStatus.OK).body(freightService.save(freight));
    }
}
