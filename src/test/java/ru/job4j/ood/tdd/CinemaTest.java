package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
class CinemaTest {

    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(ses -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, -1, 1, date);
        });
    }

    /**
     * Добавлено.
     * Насколько я сообразил, column - это
     * место в зале.
     */
    @Test
    public void whenBuyOnInvalidColumnThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 1, -1, date);
        });
    }

    /**
     * Добавлено.
     * Я купил билет. А потом, если снова захочу
     * купить, то должно вылететь исключение.
     */
    @Test
    public void whenBuyTicketForOccupiedSeatThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket occupied = cinema.buy(account, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Добавлено.
     * По разным причинам сеанс не был
     * добавлен, но при этом могут
     * ожидать того, что он есть,
     * и проводить действия с ним.
     */
    @Test
    public void whenSessionNotFound() {
        Cinema cinema = new Cinema3D();
        Session session1 = new Session3D();
        Session session2 = new Session3D();
        cinema.add(session1);
        List<Session> sessions = cinema.find(ses -> true);
        assertThatThrownBy(() -> sessions.contains(session2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Добавлено.
     * В качестве неверной даты
     * использовал null, иначе нужно
     * закрепить дату за сеансами.
     * А сеанс (интерфейс и класс)
     * пустой.
     */
    @Test
    public void whenBuyTicketOnInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}