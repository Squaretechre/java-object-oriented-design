package com.danparkin;

// Money exposes the fact that it stores value as a double.
// If the type of value needs to change then all calls will need to change.

public class Money {
    private double value;

    public Money(double value) {
        this.value = value;
    }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
}
