package ru.job4j.ood.ocp.violations;

/**
 * Данный класс описывает пример
 * нарушения принципа OCP, а точнее
 * нарушение в методе {@link Weapon#attack()}.
 * Перейдите туда пожалуйста.
 *
 * P.S.Этот пример даже рабочий).
 */
public class PersonWeapon {

    private static class Person {

        private String name;
        private Weapon weapon;

        public Person(String name, Weapon weapon) {
            this.name = name;
            this.weapon = weapon;
        }

        private void attack() {
            this.weapon.attack();
        }

        private void changeWeapon(Weapon newWeapon) {
            this.weapon = newWeapon;
        }
    }

    private static class Weapon {

        private String name;
        private int damage;

        public Weapon(String name, int damage) {
            this.name = name;
            this.damage = damage;
        }

        /**
         * Например, нам сказали, что
         * нужно ввести оружие "нож".
         * Код данного метода нарушает
         * принцип открытости/закрытости,
         * т.к. мы изменили существующий
         * функционал.
         *
         * В качестве решения необходимо было
         * создать интерфейс "Weapon" или
         * сделать этот класс родительским
         * и наследоваться от него. Когда
         * потребуют новое оружие, то
         * нам достаточно будет создать новый
         * класс для оружия.
         */
        public void attack() {
            if (name.equals("sword")) {
                System.out.printf("Attack by %s with damage %d%n", name, damage);
            }
            if (name.equals("knife")) {
                System.out.printf("Attack by %s with damage %d%n", name, damage);
            }
        }
    }

    public static void main(String[] args) {
        Weapon sword = new Weapon("sword", 50);
        Person warrior = new Person("Consta", sword);
        warrior.attack();
        warrior.changeWeapon(new Weapon("knife", 10));
        warrior.attack();
    }
}
