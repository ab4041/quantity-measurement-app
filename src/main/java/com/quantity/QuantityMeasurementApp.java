package com.quantity;

import java.util.Objects;

public class QuantityMeasurementApp {

    // ENUM inside main class
    public enum LengthUnit {

        FEET(1.0),

        INCH(1.0 / 12.0),

        YARDS(3.0),

        CENTIMETERS(0.0328084); // 1 cm = 0.0328084 feet

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double toFeet(double value) {
            return value * conversionFactor;
        }
    }

    // Generic Quantity Class
    public static class QuantityMeasurement {

        private final double value;
        private final LengthUnit unit;

        public QuantityMeasurement(double value, LengthUnit unit) {

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityMeasurement other =
                    (QuantityMeasurement) obj;

            return Double.compare(
                    this.toFeet(),
                    other.toFeet()
            ) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(toFeet());
        }
    }
}