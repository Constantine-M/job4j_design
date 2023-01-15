package ru.job4j.ood.isp.violations.example4;

public class LoanPayment implements Payment {

    @Override
    public void payLoan() {
        System.out.println("Pay loan..");
    }

    @Override
    public void payBank() {
        throw new UnsupportedOperationException();
    }
}
