package ru.job4j.ood.isp.menu;

/**
 * Если выносить логику удаления пункта
 * меню, то будет дублирование кода.
 * Удаление должно быть там же, где
 * и добавление.
 * Здесь можно было бы сделать ссылку
 * на интерфейс {@link Menu}, а дальше
 * уже не важно как пункт меню будет
 * удаляться.
 */
public class DeleteAction implements ActionDelegate {

    @Override
    public void delegate() {

    }
}
