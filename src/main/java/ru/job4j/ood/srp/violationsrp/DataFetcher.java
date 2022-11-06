package ru.job4j.ood.srp.violationsrp;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

/**
 * В данном классе нарушается принцип SRP.
 * 1.Здесь нужно вынести в отдельные классы
 * или интерфейсы работу с пользователями
 * и работу с реквизитами.
 */
public class DataFetcher {

    private HttpRequest request;

    public DataFetcher(HttpRequest request) {
        this.request = request;
    }

    public void getUser(int id) throws URISyntaxException {
        this.request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9000/" + id))
                .build();
    }

    public void getRequisites(int id) throws URISyntaxException {
        this.request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9000/requisites" + id))
                .build();
    }
}
