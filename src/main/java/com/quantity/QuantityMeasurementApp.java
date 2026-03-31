package com.quantity;

public class QuantityMeasurementApp {

    // Interface

    public interface IMeasurable {

        double convertToBaseUnit(double value);

        double convertFromBaseUnit(double value);

    }


    // Weight Units

    public enum WeightUnit implements IMeasurable {

        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double factor;

        WeightUnit(double factor) {
            this.factor = factor;
        }

        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }

    }


    // Volume Units

    public enum VolumeUnit implements IMeasurable {

        LITRE(1.0),
        MILLILITRE(0.001),
        GALLON(3.78541);

        private final double factor;

        VolumeUnit(double factor) {
            this.factor = factor;
        }

        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }

    }


    // Generic Quantity Class

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

            double base1 = unit.convertToBaseUnit(value);

            double base2 = other.unit.convertToBaseUnit(other.value);

            double sum = base1 + base2;

            double result = targetUnit.convertFromBaseUnit(sum);

            return new Quantity<>(result, targetUnit);

        }

    }

}