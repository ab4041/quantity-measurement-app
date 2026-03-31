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
                throw new IllegalArgumentException("Invalid numeric value");

            this.value = value;
            this.unit = unit;
        }


        private double toFeet() {
            return unit.toFeet(value);
        }


        // UC5 conversion method
        public QuantityMeasurement convertTo(LengthUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double valueInFeet = this.toFeet();

            double convertedValue =
                    valueInFeet / targetUnit.getConversionFactor();

            return new QuantityMeasurement(convertedValue, targetUnit);
        }


        // UC6 Addition method
        public QuantityMeasurement add(QuantityMeasurement other) {

            if (other == null)
                throw new IllegalArgumentException("Second operand cannot be null");

            double sumFeet =
                    this.toFeet() + other.toFeet();

            double resultValue =
                    sumFeet / this.unit.getConversionFactor();

            return new QuantityMeasurement(resultValue, this.unit);
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


    // UC5 static conversion API
    public static double convert(
            double value,
            LengthUnit source,
            LengthUnit target
    ) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        double valueInFeet = source.toFeet(value);

        return valueInFeet / target.getConversionFactor();
    }
}