package com.quantity;

public class QuantityMeasurementApp {

    // ---------- INTERFACE ----------

    public interface IMeasurable {

        double getConversionFactor();

        double convertToBaseUnit(double value);

        double convertFromBaseUnit(double baseValue);

        String getUnitName();
    }


    // ---------- WEIGHT UNIT ENUM ----------

    public enum WeightUnit implements IMeasurable {

        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double conversionFactor;

        WeightUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        @Override
        public double getConversionFactor() {
            return conversionFactor;
        }

        @Override
        public double convertToBaseUnit(double value) {
            return value * conversionFactor;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue / conversionFactor;
        }

        @Override
        public String getUnitName() {
            return name();
        }
    }


    // ---------- GENERIC QUANTITY CLASS ----------

    public static class Quantity<U extends IMeasurable> {

        private final double value;
        private final U unit;

        public Quantity(double value, U unit) {

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

        public U getUnit() {
            return unit;
        }


        // ---------- CONVERSION ----------

        public Quantity<U> convertTo(U targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double baseValue =
                    unit.convertToBaseUnit(value);

            double convertedValue =
                    targetUnit.convertFromBaseUnit(baseValue);

            return new Quantity<>(convertedValue, targetUnit);
        }


        // ---------- ADDITION ----------

        public Quantity<U> add(Quantity<U> other, U targetUnit) {

            if (other == null || targetUnit == null)
                throw new IllegalArgumentException("Invalid parameters");

            double base1 =
                    unit.convertToBaseUnit(value);

            double base2 =
                    other.unit.convertToBaseUnit(other.value);

            double sum = base1 + base2;

            double result =
                    targetUnit.convertFromBaseUnit(sum);

            return new Quantity<>(result, targetUnit);
        }


        // ---------- EQUALITY ----------

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (!(obj instanceof Quantity<?>))
                return false;

            Quantity<?> other =
                    (Quantity<?>) obj;

            if (!unit.getClass().equals(other.unit.getClass()))
                return false;

            double base1 =
                    unit.convertToBaseUnit(value);

            double base2 =
                    other.unit.convertToBaseUnit(other.value);

            return Math.abs(base1 - base2) < 0.001;
        }
    }
}