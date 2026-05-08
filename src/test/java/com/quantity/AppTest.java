package com.quantity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.quantity.controller.QuantityMeasurementController;
import com.quantity.dto.QuantityDTO;
import com.quantity.repository.QuantityMeasurementCacheRepository;
import com.quantity.service.QuantityMeasurementServiceImpl;

public class AppTest {

    @Test
    void testAddition() {

        QuantityMeasurementController controller =
                new QuantityMeasurementController(

                        new QuantityMeasurementServiceImpl(

                                QuantityMeasurementCacheRepository.getInstance()
                        )
                );

        QuantityDTO result =
                controller.performAddition(

                        new QuantityDTO(10, "KG"),
                        new QuantityDTO(5, "KG")
                );

        assertEquals(15.0, result.getValue());
    }
}