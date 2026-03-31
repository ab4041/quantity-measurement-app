package com.quantity;

import java.util.Objects;

public class QuantityMeasurementApp {

    public enum LengthUnit {

        FEET(1.0),

        INCH(1.0 / 12.0),

        YARDS(3.0),

        CENTIMETERS(0.0328084);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double toFeet(double value) {
            return value * conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }


    public static class QuantityMeasurement {

        private final double value;
        private final LengthUnit unit;

        public QuantityMeasurement(double value, LengthUnit unit) {

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        public QuantityMeasurement convertTo(LengthUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double valueInFeet = unit.toFeet(value);

            double convertedValue =
                    valueInFeet / targetUnit.getConversionFactor();

            return new QuantityMeasurement(convertedValue, targetUnit);
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


    public static double convert(
            double value,
            LengthUnit source,
            LengthUnit target
    ) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        if (source == null || target == null)
            throw new IllegalArgumentException("Unit cannot be null");

        double valueInFeet = source.toFeet(value);

        return valueInFeet / target.getConversionFactor();
    }
}