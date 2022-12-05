package ru.job4j.ood.lsp.storegoods.control;

/**
 * Данный интерфейс описывает
 * расход срока годности продукта.
 * Так как этот функионал необходимо
 * использовать во всех хранилищах,
 * мы вынесли его и будем
 * взаимодействовать с ним через
 * интерфейс. Таким образом мы
 * не нарушим SRP.
 * @param <T> объект, который
 *           описывает дату ({@link java.util.Calendar},
 *           {@link java.util.Date} и пр.
 */
public interface ExpirationCalculator<T> {

    double calculateInPercent(T startDate, T endDate);
}
