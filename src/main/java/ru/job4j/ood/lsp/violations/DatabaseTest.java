package ru.job4j.ood.lsp.violations;

/**
 * Данный пример показывает нарушение
 * принципа подстановки (LSP).
 * В дочернем классе {@link MongoDB}
 * метод {@link MongoDB#joinTable()}
 * не нужен, т.к. данные в MongoDB
 * хранятся в форме коллекций и документов.
 * Мы выбрасываем исключеие и тем самым
 * нарушаем принцип LSP - во время
 * работы приложения, ожидая объединения
 * таблиц нас встретит исключение.
 * В качестве решения использовать
 * интерфейс. Здесь не обойдется и
 * без дальновидности...
 */
public class DatabaseTest {

    private static class Database {

        public void connect() {
            /**Код подключения к БД*/
        }

        public void read() {
            /**Код чтения из БД*/
        }

        public void joinTable() {
            /**Код объединения таблиц*/
        }
    }

    private static class MongoDB extends Database {
        @Override
        public void connect() {
            super.connect();
        }

        @Override
        public void read() {
            super.read();
        }

        /**Нарушение*/
        @Override
        public void joinTable() {
            throw new IllegalArgumentException("Mongo has no tables!");
        }
    }
}
