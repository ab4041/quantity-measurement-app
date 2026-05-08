package com.quantity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.quantity.entity.QuantityMeasurementEntity;
import com.quantity.repository.QuantityMeasurementDatabaseRepository;

public class AppTest {

    @Test
    void testDatabaseSave() {

        QuantityMeasurementDatabaseRepository repo =
                new QuantityMeasurementDatabaseRepository();

        repo.deleteAll();

        repo.save(
                new QuantityMeasurementEntity(
                        "addition",
                        15.0
                )
        );

        assertEquals(
                1,
                repo.getTotalCount()
        );
    }
}