package ru.job4j.ood.lsp.violations;

/**
 * Данный пример описывает нарушение LSP.
 * 1.Добавлено дополнительное поле
 * в дочерний класс {@link Captain},
 * что может повлечь за собой нарушение
 * контракта (или уже повлекло).
 * 2.Использование метода getClass().
 * Вероятное решение: не использовать
 * здесь наследование, разнести
 * капитана и пассажира в отдельные классы.
 * Создать интерфейс "авторизация" и
 * передавать туда дженерик, чтобы
 * мы могли проверять любого участника
 * полета.
 */
public class FlightPass {

    private static class Person {

        private String name;

        private String surname;

        public Person(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }
    }

    private static class Passenger extends Person {

        public Passenger(String name, String surname) {
            super(name, surname);
        }
    }

    private static class Captain extends Person {

        private String pilotCert;

        public Captain(String name, String surname, String pilotCert) {
            super(name, surname);
            this.pilotCert = pilotCert;
        }
    }

    private static class Authorization {

        private final Person person;

        public Authorization(Person person) {
            this.person = person;
        }

        public boolean inspect(Person person) {
            boolean result = false;
            if (person.getClass() == Captain.class) {
                result = true;
                /**проверяем имя/фамилию и сертификат*/
            } else if (person.getClass() == Passenger.class) {
                result = true;
                /**проверяем имя/фамилию*/
            }
            return result;
        }
    }
}
