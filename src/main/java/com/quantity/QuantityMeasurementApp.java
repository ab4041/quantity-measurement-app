package com.quantity;

public class QuantityMeasurementApp {

    public interface IMeasurable {
        double toBase(double value);
        double fromBase(double baseValue);
    }

    // ---------------- WEIGHT ----------------

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


    // ---------------- VOLUME ----------------

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


    // ---------------- GENERIC QUANTITY ----------------

    public static class Quantity<U extends IMeasurable> {

        private final double value;
        private final U unit;

        public Quantity(double value, U unit) {
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public U getUnit() {
            return unit;
        }


        // -------- ADD --------

        public Quantity<U> add(Quantity<U> other, U targetUnit) {

            double base1 = unit.toBase(value);
            double base2 = other.unit.toBase(other.value);

            double result = base1 + base2;

            double converted = targetUnit.fromBase(result);

            return new Quantity<>(converted, targetUnit);
        }


        // -------- SUBTRACT (UC12) --------

        public Quantity<U> subtract(Quantity<U> other) {
            return subtract(other, this.unit);
        }

        public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Other quantity cannot be null");

            if (!unit.getClass().equals(other.unit.getClass()))
                throw new IllegalArgumentException("Different measurement category");

            double base1 = unit.toBase(value);
            double base2 = other.unit.toBase(other.value);

            double result = base1 - base2;

            double converted = targetUnit.fromBase(result);

            return new Quantity<>(round(converted), targetUnit);
        }


        // -------- DIVIDE (UC12) --------

        public double divide(Quantity<U> other) {

            if (other == null)
                throw new IllegalArgumentException("Other quantity cannot be null");

            double base1 = unit.toBase(value);
            double base2 = other.unit.toBase(other.value);

            if (base2 == 0)
                throw new ArithmeticException("Division by zero");

            return base1 / base2;
        }


        private double round(double value) {
            return Math.round(value * 100.0) / 100.0;
        }
    }
}