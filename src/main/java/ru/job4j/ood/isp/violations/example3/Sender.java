package ru.job4j.ood.isp.violations.example3;

/**
 * Данный пример является
 * нарушением ISP.
 * Решение: разнести по
 * разным интефейсам отправку
 * SMS, email и почты.
 */
public interface Sender {

    void sendSMS();

    void sendEmail();

    void sendPostal();
}
