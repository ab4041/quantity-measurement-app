package com.quantity.controller;

import com.quantity.dto.QuantityDTO;
import com.quantity.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private IQuantityMeasurementService service;

    public QuantityMeasurementController(
            IQuantityMeasurementService service) {

        this.service = service;
    }

    public QuantityDTO performAddition(
            QuantityDTO q1,
            QuantityDTO q2) {

        return service.add(q1, q2);
    }
}