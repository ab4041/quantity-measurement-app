package com.quantity;

import com.quantity.controller.QuantityMeasurementController;
import com.quantity.dto.QuantityDTO;
import com.quantity.repository.QuantityMeasurementDatabaseRepository;
import com.quantity.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityMeasurementController controller =
                new QuantityMeasurementController(

                        new QuantityMeasurementServiceImpl(

                                new QuantityMeasurementDatabaseRepository()
                        )
                );

        QuantityDTO result =
                controller.performAddition(

                        new QuantityDTO(10, "KG"),
                        new QuantityDTO(5, "KG")
                );

        System.out.println(
                "Addition Result = "
                        + result.getValue()
                        + " "
                        + result.getUnit()
        );
    }
}