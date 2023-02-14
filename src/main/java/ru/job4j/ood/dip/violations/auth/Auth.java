package ru.job4j.ood.dip.violations.auth;

/**
 * Данный пример описывает нарушение DIP,
 * т.к. здесь вышестоящий класс {@link Auth}
 * зависит от нижестоящего {@link PostgreSqlConnection}
 * напрямую.
 * Решение: нужно добавить интерфейс,
 * например, DataBaseConnection, а потом
 * использовать этот интерфейс для построения
 * новых классов (напр. MySqlConnection, Postgre и пр.).
 */
public class Auth {

    PostgreSqlConnection connection;

    public Auth(PostgreSqlConnection connection) {
        this.connection = connection;
    }

    public boolean authentificate(String login, String password) {
        /**some code*/
        return false;
    }
}
