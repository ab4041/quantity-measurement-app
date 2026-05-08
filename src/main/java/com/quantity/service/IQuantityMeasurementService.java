package com.quantity.service;

import com.quantity.dto.QuantityDTO;

public interface IQuantityMeasurementService {

    QuantityDTO add(
            QuantityDTO q1,
            QuantityDTO q2);
}