package com.danparkin.accountspayable;

import java.util.Currency;
interface Timesheet{}
interface Invoice{}
class Money { public Money(double val, Currency c){} }

interface Payable {
    void pay();
}

abstract class Worker implements Payable {
    private String _name;

    public Worker(String name) {
        _name = name;
    }

    // Template Method
    public void pay() {
        Money due = getAmountDue();
    }
    abstract protected Money getAmountDue();
}

class Employee extends Worker {
    private Timesheet[] unpaidTimesheets;
    public Employee(String name) { super(name); }

    // Protected grants package access
    @Override
    protected Money getAmountDue() {
        return new Money(12.33, Currency.getInstance("USD"));
    }

    public void attachTimesheet(Timesheet i){}
}

class Contractor extends Worker {
    private Invoice[] _invoicesDue;
    public Contractor(String name) { super(name); }

    @Override
    protected Money getAmountDue() {
        return new Money(1200, Currency.getInstance("USD"));
    }

    public void attachInvoice(Invoice i){}
}

class AccountsPayable {
    // Ommiting access privilege defaults to package access
    private Payable[] _creditors;

    public void PayEverybody() {
        for(Payable creditor : _creditors) {
            creditor.pay();
        }
    }
}