package br.edu.infnet.elberthapi.model.domain;

public class CalculadoraCientifica {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) throw new IllegalArgumentException("Division by zero");
        return a / b;
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public double squareRoot(double a) {
        if (a < 0) throw new IllegalArgumentException("Negative number");
        return Math.sqrt(a);
    }

    public double log(double a) {
        if (a <= 0) throw new IllegalArgumentException("Log of non-positive number");
        return Math.log(a);
    }

    public double sin(double degrees) {
        return Math.sin(Math.toRadians(degrees));
    }

    public double cos(double degrees) {
        return Math.cos(Math.toRadians(degrees));
    }
}