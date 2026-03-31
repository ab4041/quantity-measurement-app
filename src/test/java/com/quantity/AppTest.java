package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.quantity.QuantityMeasurementApp.Quantity;
import com.quantity.QuantityMeasurementApp.TemperatureUnit;

public class AppTest {

    private static final double EPSILON = 0.001;


    @Test
    void testTemperatureEquality() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(t1, t2);
    }


    @Test
    void testTemperatureConversion() {

        Quantity<TemperatureUnit> t =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                t.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(212.0,
                result.getValue(),
                EPSILON);
    }


    @Test
    void testTemperatureUnsupportedAddition() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.add(t2, TemperatureUnit.CELSIUS)
        );
    }
}