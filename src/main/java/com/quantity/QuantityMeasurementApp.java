package com.quantity;

public class QuantityMeasurementApp {

    public enum LengthUnit {

        FEET(12.0),
        INCH(1.0),
        YARD(36.0),
        CM(0.393701);

        private final double toInchFactor;

        LengthUnit(double toInchFactor) {
            this.toInchFactor = toInchFactor;
        }

        public double toBaseUnit(double value) {
            return value * this.toInchFactor;
        }

        public double fromBaseUnit(double value) {
            return value / this.toInchFactor;
        }
    }


    public static class QuantityMeasurement {

        private final double value;
        private final LengthUnit unit;


        public QuantityMeasurement(double value, LengthUnit unit) {

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

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

            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid parameters");
            }

            // convert both values to base unit (inches)
            double thisInInches =
                    this.unit.toBaseUnit(this.value);

            double otherInInches =
                    other.unit.toBaseUnit(other.value);

            // add them
            double sumInInches =
                    thisInInches + otherInInches;

            // convert to target unit
            double resultValue =
                    targetUnit.fromBaseUnit(sumInInches);

            return new QuantityMeasurement(
                    resultValue,
                    targetUnit
            );
        }
    }
}