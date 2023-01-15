package ru.job4j.ood.isp.violations.example3;

public class EmailSender implements Sender {
    @Override
    public void sendSMS() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendEmail() {
        System.out.println("Sending email..");
    }

    @Override
    public void sendPostal() {
        throw new UnsupportedOperationException();
    }
}
