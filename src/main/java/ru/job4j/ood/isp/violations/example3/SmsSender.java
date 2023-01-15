package ru.job4j.ood.isp.violations.example3;

public class SmsSender implements Sender {
    @Override
    public void sendSMS() {
        System.out.println("Send SMS..");
    }

    @Override
    public void sendEmail() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendPostal() {
        throw new UnsupportedOperationException();
    }
}
