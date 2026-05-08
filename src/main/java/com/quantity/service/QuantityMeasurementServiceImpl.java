package com.quantity.service;

import com.quantity.dto.QuantityDTO;
import com.quantity.entity.QuantityMeasurementEntity;
import com.quantity.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository repository) {

        this.repository = repository;
    }

    @Override
    public QuantityDTO add(
            QuantityDTO q1,
            QuantityDTO q2) {

        double result =
                q1.getValue() + q2.getValue();

        repository.save(
                new QuantityMeasurementEntity(
                        "addition",
                        result
                )
        );

        return new QuantityDTO(
                result,
                q1.getUnit()
        );
    }
}