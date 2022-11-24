package ru.job4j.ood.ocp.violations.example;

/**
 * Данный пример описывает нарушения OCP.
 * 1.Мы связали разработчика
 * напрямую с Java, хотя в теории он
 * может писать и на других языках.
 * 2.Метод {@link Programmer#getProject()}
 * возвращает конкретный проект,
 * связанный с медициной, что тоже
 * неправильно.
 *
 * В обоих случаях связь должна
 * осуществляться через абстракцию.
 */
public class Programmer {

    private String name;

    private JavaLang java;

    public Programmer(String name, JavaLang java) {
        this.name = name;
        this.java = java;
    }
    
    public MedicalProject getProject() {
        return new MedicalProject();
    }
}
