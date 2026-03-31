package com.quantity;

public class QuantityMeasurementApp {

    // ---------- WEIGHT UNITS ----------

    public enum WeightUnit {

        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double conversionFactor;

        WeightUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double convertToBaseUnit(double value) {
            return value * conversionFactor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / conversionFactor;
        }
    }


    // ---------- QUANTITY MEASUREMENT CLASS ----------

    public static class QuantityMeasurement {

        private final double value;
        private final WeightUnit unit;

        public QuantityMeasurement(double value, WeightUnit unit) {

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (Double.isNaN(value) || Double.isInfinite(value))
                throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public WeightUnit getUnit() {
            return unit;
        }


        // Convert weight to another unit

        public QuantityMeasurement convertTo(WeightUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double baseValue =
                    unit.convertToBaseUnit(value);

            double convertedValue =
                    targetUnit.convertFromBaseUnit(baseValue);

            return new QuantityMeasurement(convertedValue, targetUnit);
        }


        // Add two weights

        public QuantityMeasurement add(
                QuantityMeasurement other,
                WeightUnit targetUnit) {

            if (other == null || targetUnit == null)
                throw new IllegalArgumentException("Invalid parameters");

            double base1 =
                    unit.convertToBaseUnit(value);

            double base2 =
                    other.unit.convertToBaseUnit(other.value);

            double sum = base1 + base2;

            double result =
                    targetUnit.convertFromBaseUnit(sum);

            return new QuantityMeasurement(result, targetUnit);
        }


        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (!(obj instanceof QuantityMeasurement))
                return false;

            QuantityMeasurement other =
                    (QuantityMeasurement) obj;

            double base1 =
                    unit.convertToBaseUnit(value);

            double base2 =
                    other.unit.convertToBaseUnit(other.value);

            return Math.abs(base1 - base2) < 0.001;
        }
    }
}