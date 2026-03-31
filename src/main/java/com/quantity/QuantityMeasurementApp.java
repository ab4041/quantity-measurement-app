package com.quantity;

public class QuantityMeasurementApp {

    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(1.0 / 30.48);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double convertToBaseUnit(double value) {
            return value * conversionFactor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / conversionFactor;
        }
    }


    public static class QuantityMeasurement {

        private final double value;
        private final LengthUnit unit;

        public QuantityMeasurement(double value, LengthUnit unit) {

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }


        public QuantityMeasurement add(
                QuantityMeasurement other,
                LengthUnit targetUnit) {

            if (other == null || targetUnit == null)
                throw new IllegalArgumentException("Invalid parameters");

            double thisBase =
                    unit.convertToBaseUnit(value);

            double otherBase =
                    other.unit.convertToBaseUnit(other.value);

            double sum =
                    thisBase + otherBase;

            double result =
                    targetUnit.convertFromBaseUnit(sum);

            return new QuantityMeasurement(result, targetUnit);
        }
    }
}