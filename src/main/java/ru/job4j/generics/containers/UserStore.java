package ru.job4j.generics.containers;

/**
 * 5.2.2. Реализовать Store<T extends Base>.
 *
 * Данное хранилище мы реализовываем
 * на основе универсального хранилища.
 * Поэтому используем композицию.
 *
 * Насколько я понял, здесь {@code store}
 * является тем самым универсальным хранилищем.
 * И мы используем {@link MemStore} как шаблон.
 * Тем самым экономим время и строки.
 * @author Constantine on 27.10.2021
 */
public class UserStore implements Store<User> {

    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        store.add(model);
    }

    @Override
    public void replace(String id, User model) {
        store.replace(id, model);
    }

    @Override
    public void delete(String id) {
        store.delete(id);
    }

    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}
