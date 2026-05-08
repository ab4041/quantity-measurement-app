package com.quantity;

import com.quantity.controller.QuantityMeasurementController;
import com.quantity.dto.QuantityDTO;
import com.quantity.repository.QuantityMeasurementCacheRepository;
import com.quantity.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityMeasurementController controller =
                new QuantityMeasurementController(

                        new QuantityMeasurementServiceImpl(

                                QuantityMeasurementCacheRepository.getInstance()
                        )
                );

        QuantityDTO result =
                controller.performAddition(

                        new QuantityDTO(5, "KG"),
                        new QuantityDTO(5, "KG")
                );

        System.out.println(
                "Result = " + result.getValue()
        );
    }
}