package ru.job4j.ood.srp.violationsrp;

/**
 * Данный класс описывает шаблон
 * проектирования Singleton, который в
 * свою очередь нарушает принцип SRP.
 *
 * 1.Смешение ответственностей.
 * Синглтон не только выполняет свою
 * основную функцию, но ещё и проверяет,
 * не существует ли уже созданных экземпляров.
 * 2.
 */
public class Database {

    /**
     * Данное поле хранит объект
     * одиночки. Оно должно быть
     * статичным.
     */
    private static Database instance;

    /**
     * Конструктор всегда должен
     * оставаться приватным, чтобы
     * клинеты не могли создавать
     * новые объекты через "NEW".
     */
    private Database() { }

    /**
     * Данный статический метод служит
     * альтернативой конструктору и является
     * точкой доступа к экземпляру этого
     * класса.
     * @return экземплят класса {@link Database}.
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Данный метод описывает полезный
     * функционал, который будут
     * применять пользователи через
     * полученный объект одиночки.
     * @param sql sql запрос.
     */
    public void query(String sql) {
        /*Код для работы с БД посредством SQL запроса*/
    }
}
