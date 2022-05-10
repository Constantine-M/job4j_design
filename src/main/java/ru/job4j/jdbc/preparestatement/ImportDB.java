package ru.job4j.jdbc.preparestatement;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 0.2. PrepareStatement.
 *
 * Данный класс описывает
 * загрузку и сохранение в БД
 * спамеров.
 *
 * @author Constantine on 09.05.2022
 */
public class ImportDB {

    private Properties cfg;

    private Connection connection;

    private String dump;

    public ImportDB(Properties cfg, String dump) throws SQLException, ClassNotFoundException {
        this.cfg = cfg;
        this.dump = dump;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName(cfg.getProperty("driver"));
        connection = DriverManager.getConnection(
                cfg.getProperty("url"),
                cfg.getProperty("login"),
                cfg.getProperty("password"));
    }

    /**
     * Данный метод создает таблицу
     * под названием "spammers",
     * если таковой не существует.
     *
     * В задаче мы, вероятно, должны
     * создать ее заранее, но
     * хотелось бы организовать это
     * внутри приложения.
     *
     * Передать название таблицы в
     * метод можно, но хочется запихнуть
     * этот блок внутрь метода сохранения
     * пользователей, а там
     * лишнюю ифомрацию передавать не
     * стоит.
     *
     * Можно было бы добавить
     * {@link ru.job4j.jdbc.statement.TableEditor},
     * но не хочется перемешивать
     * классы. Я работаю в рамках папки
     * и вышеупомянутого класса в ней нет.
     *
     * А {@link PreparedStatement}
     * может быть только один, поэтому
     * здесь отдельный метод и отдельно
     * используется простой {@link Statement}.
     */
    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS spammers (id serial PRIMARY KEY, name varchar(255), email varchar(255));";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class User {

        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    /**
     * Данный метод парсит dump-файл
     * и заполняет список
     * пользователями-спаммерами.
     *
     * В файле. в строке 2 записи через ";".
     * 1.Нужно разбить строку по ";".
     * 2.Присвоить имя и почту объекту
     * класса User.
     *
     * Сделал так:
     * 1.Написал regex, чтобы разбить строку.
     * 2.Использовал {@link HashMap}, чтобы
     * записать туда имя и почту.
     * 3.С помощью {@link Pattern} и
     * {@link Matcher} записал
     * имя и почту в карту.
     * 4.Прошелся по карте и заполнил
     * список пользователей.
     *
     * @return список спаммеров.
     */
    public List<User> load() {
        Map<String, String> values = new HashMap<>();
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            String text;
            Pattern pattern = Pattern.compile("([^;]*);([^;]*)");
            while ((text = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    values.put(matcher.group(1), matcher.group(2));
                }
            }
            values.forEach((k, v) -> users.add(new User(k, v)));
            /*reader.lines().forEach(user -> users.add(new User()));*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void save(List<User> users) {
        createTable();
        for (User user : users) {
            try (PreparedStatement ps =
                         connection.prepareStatement("INSERT INTO spammers (name, email) VALUES (?, ?)")) {
                ps.setString(1, user.name);
                ps.setString(2, user.email);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dump = "C:\\projects\\job4j_design\\data\\dump.txt";
        ImportDB db = new ImportDB(cfg, dump);
        db.save(db.load());
    }
}
