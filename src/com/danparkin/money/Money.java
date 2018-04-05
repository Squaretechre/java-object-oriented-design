package com.danparkin.money;

enum Currency { USD, EURO;
    public double conversionRateTo(Currency target) { return 1.0; }
}

// Money exposes the fact that it stores value as a double.
// If the class of value needs to change then all calls will need to change.

// What happens when Money needs to express what currency it is?
// getValue returns a primitive that doesn't explain this.

// Exposing getters & setters (accessors and mutators) are evil and expose too much information about how objects work.
// Exposing the inner workings of objects makes code less maintainable.

// Extracting information from an object and then doing an operation on it externally leads to poorly factored solutions.
// Instead delegate the work to the object by asking it to do the operation for you.
// Objects are lazy, the Test class doesn't want to know about currency conversion.
// Test should delegate this work to Money.
// Delegation - the object that has the information does the work.

public class Money {
    private Currency currency;
    private double value;

    public Money(double value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public boolean isGreaterThan(Money op) {
        return(normalized() > op.normalized());
    }

    private double normalized() {
        return currency == Currency.USD
                ? value
                : value * currency.conversionRateTo(Currency.USD);
    }
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

        // double normalizedBalance = balance.getValue() * balance.getCurrency().conversionRateTo(Currency.USD);
        // double normalizedRequest = request.getValue() * request.getCurrency().conversionRateTo(Currency.USD);

        // The responsibility of comparing two monetary values has now been moved onto the Money class.
        // This is a reasonable expectation of the Money class as it holds the data concerned in the operation.
        // The normalization to USD is now also a implementation detail hidden behind the isGreaterThan method.
        // This is more concise and less complex.
        if(balance.isGreaterThan(request)) {
            dispenseFunds(request);
        }
    }
}
