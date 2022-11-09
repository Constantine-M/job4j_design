package ru.job4j.ood.srp.violationsrp;

/**
 * В данном классе нарушается принцип SRP.
 * 1.В модели данных User описана лишняя
 * логика, которую необходимо вынести
 * в отдельный интерфейс UserAction например.
 */
public class User {

    private String name;

    private int age;

    private String education;

    private double salary;

    public User(String name, int age, String education, double salary) {
        this.name = name;
        this.age = age;
        this.education = education;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEducation() {
        return education;
    }

    public double getSalary() {
        return salary;
    }

    public void save(User user) {
        /*Код сохранения пользователя в БД например*/
    }

    public void print(User user) {
        /*Код вывода пользователя в консоль*/
    }

    public User delete(User user) {
        /*Код для удаления пользователя из БД*/
        return null;
    }
}
