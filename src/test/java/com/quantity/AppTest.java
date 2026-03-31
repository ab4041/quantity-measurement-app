package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.quantity.QuantityMeasurementApp.Quantity;
import com.quantity.QuantityMeasurementApp.WeightUnit;
import com.quantity.QuantityMeasurementApp.VolumeUnit;

public class AppTest {

    private static final double EPSILON = 0.001;

    @Test
    void testWeightSubtraction() {

        Quantity<WeightUnit> w1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(5000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                w1.subtract(w2);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testVolumeSubtraction() {

        Quantity<VolumeUnit> v1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(2000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                v1.subtract(v2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testWeightDivision() {

        Quantity<WeightUnit> w1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        double result = w1.divide(w2);

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testVolumeDivision() {

        Quantity<VolumeUnit> v1 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        double result = v1.divide(v2);

        assertEquals(1.0, result, EPSILON);
    }
}