package ru.job4j.ood.ocp.violations;

/**
 * Данный пример показывает пример
 * нарушения OCP.
 *
 * Допустим, компания была маленькая,
 * полеты в пределах села. Для
 * автоматизации проверки достаточно
 * было имени и фамилии. Поэтому
 * имеем то, что ниже.
 *
 * Но потом компания разрослась и
 * досмотр стал жесче - потребовалась
 * проверка паспортов, а точнее
 * гражданство и серия/номер.
 *
 * Для этого придется добавлять
 * новые поля в существующий класс.
 * Изменится конструктор и метод
 * проверки/досмотра пассажира.
 * Насколько я понимаю, это и есть
 * нарушение.
 */
public class FlightPass {

    private static class Passenger {

        private String name;

        private String surname;

        public Passenger(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }
    }

    private static class Authorization {

        private final Passenger passenger;

        public Authorization(Passenger passenger) {
            this.passenger = passenger;
        }

        public boolean inspect(Passenger passenger) {
            boolean result = false;
            /**
             * Здесь прописываем, соответствует ли
             * имя/фамилия пассажира имени и фамилии
             * в базе данных.
             */
            return result;
        }
    }
}
