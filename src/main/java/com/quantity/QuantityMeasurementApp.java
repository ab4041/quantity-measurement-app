package com.quantity;

public class QuantityMeasurementApp {

    // ---------- FUNCTIONAL INTERFACE ----------

    @FunctionalInterface
    public interface SupportsArithmetic {
        boolean isSupported();
    }

    // ---------- MAIN INTERFACE ----------

    public interface IMeasurable {

        SupportsArithmetic supportsArithmetic = () -> true;

        double toBase(double value);

        double fromBase(double baseValue);

        default boolean supportsArithmetic() {
            return supportsArithmetic.isSupported();
        }

        default void validateOperationSupport(String operation) {
            // default: allowed
        }
    }


    // ---------- WEIGHT ----------

    public enum WeightUnit implements IMeasurable {

        KILOGRAM(1.0),
        GRAM(0.001);

        private final double factor;

        WeightUnit(double factor) {
            this.factor = factor;
        }

        public double toBase(double value) {
            return value * factor;
        }

        public double fromBase(double baseValue) {
            return baseValue / factor;
        }
    }


    // ---------- VOLUME ----------

    public enum VolumeUnit implements IMeasurable {

        LITRE(1.0),
        MILLILITRE(0.001);

        private final double factor;

        VolumeUnit(double factor) {
            this.factor = factor;
        }

        public double toBase(double value) {
            return value * factor;
        }

        public double fromBase(double baseValue) {
            return baseValue / factor;
        }
    }


    // ---------- TEMPERATURE ----------

    public enum TemperatureUnit implements IMeasurable {

        CELSIUS,
        FAHRENHEIT;

        SupportsArithmetic supportsArithmetic = () -> false;

        public double toBase(double value) {

            if (this == FAHRENHEIT)
                return (value - 32) * 5 / 9;

            return value;
        }

        public double fromBase(double baseValue) {

            if (this == FAHRENHEIT)
                return (baseValue * 9 / 5) + 32;

            return baseValue;
        }

        @Override
        public boolean supportsArithmetic() {
            return supportsArithmetic.isSupported();
        }

        @Override
        public void validateOperationSupport(String operation) {

            throw new UnsupportedOperationException(
                    "Temperature does not support " + operation);
        }
    }


    // ---------- GENERIC QUANTITY ----------

    public static class Quantity<U extends IMeasurable> {

        private final double value;
        private final U unit;

        public Quantity(double value, U unit) {

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }


        public double getValue() {
            return value;
        }


        public Quantity<U> add(Quantity<U> other, U targetUnit) {

            unit.validateOperationSupport("addition");

            double base =
                    unit.toBase(value)
                            + other.unit.toBase(other.value);

            return new Quantity<>(
                    targetUnit.fromBase(base),
                    targetUnit);
        }


        public Quantity<U> subtract(Quantity<U> other) {

            unit.validateOperationSupport("subtraction");

            double base =
                    unit.toBase(value)
                            - other.unit.toBase(other.value);

            return new Quantity<>(
                    unit.fromBase(base),
                    unit);
        }


        public double divide(Quantity<U> other) {

            unit.validateOperationSupport("division");

            double base1 =
                    unit.toBase(value);

            double base2 =
                    other.unit.toBase(other.value);

            if (base2 == 0)
                throw new ArithmeticException("Divide by zero");

            return base1 / base2;
        }


        public Quantity<U> convertTo(U targetUnit) {

            double base =
                    unit.toBase(value);

            double converted =
                    targetUnit.fromBase(base);

            return new Quantity<>(converted, targetUnit);
        }


        @Override
        public boolean equals(Object obj) {

            if (!(obj instanceof Quantity<?>))
                return false;

            Quantity<?> other =
                    (Quantity<?>) obj;

            if (!unit.getClass()
                    .equals(other.unit.getClass()))
                return false;

            double base1 =
                    unit.toBase(value);

            double base2 =
                    other.unit.toBase(other.value);

            return Math.abs(base1 - base2) < 0.001;
        }
    }
}