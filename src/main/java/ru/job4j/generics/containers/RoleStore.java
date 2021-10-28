package ru.job4j.generics.containers;

/**
 * 5.2.2. Реализовать Store<T extends Base>.
 * @author Constantine on 28.10.2021
 */
public class RoleStore implements Store<Role> {

    private final Store<Role> roleStore = new MemStore<>();

    @Override
    public void add(Role model) {
        roleStore.add(model);
    }

    @Override
    public void replace(String id, Role model) {
        roleStore.replace(id, model);
    }

    @Override
    public void delete(String id) {
        roleStore.delete(id);
    }

    @Override
    public Role findById(String id) {
        return roleStore.findById(id);
    }
}
