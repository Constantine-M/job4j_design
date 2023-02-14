package ru.job4j.ood.dip.violations.post;

/**
 * Данный пример не столько нарушение DIP,
 * сколько возможность улучшения,
 * применяя DIP.
 * 1.В приложении было несколько
 * типов хранилищ.
 * 2.Для упрощения мы их объединили в
 * под одним интерфейсом {@link Repository}
 * и начали пользоваться данным интерфейсом
 * в разных файлах проекта.
 * 3.Например, пользовались все время и везде
 * только хранилищем локальным (в памяти)
 * {@link LocalStorage}. Раз 10 упомянули
 * его в разных местах проекта.
 * 4.Потом потребовалось перейти на
 * хранение в БД и вот здесь некоторое
 * неудобство. Нам придется найти 10 мест,
 * где мы использовали {@link LocalStorage}
 * и поменять его на {@link PostgreStorage}.
 * 5.Здесь то нам и помогла бы абстракция
 * в виде класса {@link Storage}, т.к. в
 * этом случае мы внесли бы изменения только
 * в одном месте.
 * Надеюсь, что я правильно размышляю.
 */
public class Storage implements Repository {

    Repository repository;

    public Storage(Repository repository) {
        this.repository = repository;
    }

    @Override
    public boolean add() {
        return false;
    }

    @Override
    public void findAll() {

    }
}
