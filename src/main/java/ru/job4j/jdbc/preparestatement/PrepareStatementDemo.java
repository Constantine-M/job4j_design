package ru.job4j.jdbc.preparestatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 0.2. PrepareStatement.
 *
 * Данный класс описывает принципы работы
 * интерфейса {@link java.sql.PreparedStatement}.
 *
 * С помощью объекта типа
 * {@link java.sql.PreparedStatement}
 * можно вставлять аргументы в запрос.
 *
 * @author Constantine on 27.04.2022
 */
public class PrepareStatementDemo {

    private Properties properties;

    private Connection connection;

    public PrepareStatementDemo(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"));
    }

    /**
     * Данный метод осуществляет вставку
     * в таблицу.
     *
     * Этот метод несколько улучшен,
     * поскольку позволяет получить
     * id вставленного элемента.
     *
     * Для этого нужно при создании
     * {@link PreparedStatement}
     * вторым аргументом передать
     * {@link Statement#RETURN_GENERATED_KEYS}.
     * После как обычно выполнить запрос.
     * Наконец, чтобы получить ключ,
     * нужно вызвать метод
     * {@link Statement#getGeneratedKeys()}.
     *
     * @param city обьект город.
     */
    public void insert(City city) {
        try (PreparedStatement statement =
                connection.prepareStatement("INSERT INTO cities (name, population) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Данный метод позволяет обновить
     * данные таблицы по указанным
     * столбцам: "наименование" и
     * "популяция".
     *
     * Метод {@link java.sql.Statement#executeUpdate}
     * возвращает 0, если никаких
     * обновлений не произошло.
     * Если обновилось n-ое число
     * строк, то метод вернет число n.
     *
     * @param city объект город.
     * @return true, если обновление
     * произошло.
     */
    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("UPDATE cities SET name = ?, population = ? WHERE id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(1, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("DELETE FROM cities WHERE id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }
}
