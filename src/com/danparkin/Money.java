package com.danparkin;

enum Currency { USD, EURO;
    public double conversionRateTo(Currency target) { return 1.0; }
}

// Money exposes the fact that it stores value as a double.
// If the type of value needs to change then all calls will need to change.

// What happens when Money needs to express what currency it is?
// getValue returns a primitive that doesn't explain this.

public class Money {
    private Currency currency;
    private double value;

    public Money(double value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public Currency getCurrency() { return currency; }
}

class Test {

    // Stubbed out for sake of demo
    private static void dispenseFunds(Money amount) { /*...*/ }

    public static void test() {
        Money balance = new Money(1.0, Currency.EURO);
        Money request = new Money(1.0, Currency.USD);

        // Getting the currency outside of the Money class and calculating the conversion in the Test class
        // leads to bloated and unmaintainable code.
        // It violates the Law of Demeter - balance.getCurrency().conversionRateTo()
        // Poor delegation, the Test class cares too much about Money related concerns.
        // This work should happen in the object that has the most data, Money.
        double normalizedBalance = balance.getValue() * balance.getCurrency().conversionRateTo(Currency.USD);
        double normalizedRequest = request.getValue() * request.getCurrency().conversionRateTo(Currency.USD);

        if(normalizedBalance > normalizedRequest) {
            dispenseFunds(request);
        }
    }
}
