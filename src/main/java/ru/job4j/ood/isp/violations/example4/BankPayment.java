package ru.job4j.ood.isp.violations.example4;

public class BankPayment implements Payment {

    @Override
    public void payLoan() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void payBank() {
        System.out.println("Make a payment via a bank..");
    }
}
