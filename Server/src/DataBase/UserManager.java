package DataBase;

import InputOutput.Response;
import OtherClasses.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserManager {

    public static Connection getConnection() {
        return connection;
    }

    private static Connection connection;

    private static final String USERNAME_REQUEST = "SELECT * FROM Users";
    private static final String ADD_USER_REQUEST = "INSERT INTO USERS (username, password) VALUES (?, ?)";

    public UserManager(Connection connection) {
        this.connection = connection;
    }

    /**
     * регистрация юзера
     *
     * @param username ник
     * @param password пароль
     * @return ответ от сервера
     * @throws SQLException
     */
    public static Response registerUser(String username, String password) throws SQLException {
        if (loadUsers().containsKey(username)) return new Response("Такой пользователь уже существует");
        PreparedStatement addStatement = connection.prepareStatement(ADD_USER_REQUEST);
        addStatement.setString(1, username);
        addStatement.setString(2, PasswordsHasher.encryptThisString(password));
        addStatement.executeUpdate();
        addStatement.close();
        return new Response("Пользователь добавлен");
    }

    /**
     * загрузка юзеров из бд
     *
     * @return таблица с юзерами
     * @throws SQLException
     */
    public static HashMap<String, String> loadUsers() {
        HashMap<String, String> users = new HashMap();
        try {
            PreparedStatement statement = connection.prepareStatement(USERNAME_REQUEST);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                User user = extractUserFromResult(result);
                users.put(user.getUsername(), user.getPassword());
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Произошла ошибка загрузки пользователей");
            e.printStackTrace();
            System.exit(-1);
        }
        return users;
    }

    /**
     * создание нового юзера из бд
     *
     * @param result
     * @return
     * @throws SQLException
     */
    private static User extractUserFromResult(ResultSet result) {
        try {
            String username = result.getString("username");
            String password = result.getString("password");

            return new User(username, password);
        } catch (SQLException e) {
            System.out.println("Ошибка во взаимодействии с базой данных");
            return null;
        }

    }

    /**
     * вход
     *
     * @param username ник
     * @param password пароль
     * @return ответ от сервера
     * @throws SQLException
     */
    public static Response logIn(String username, String password) {
        HashMap<String, String> users = loadUsers();
        if (!users.containsKey(username) || !users.get(username).equals(PasswordsHasher.encryptThisString(password))) {
            return new Response("Такого пользователя нет, либо данные введены некорректно");
        }
        return new Response("Вход успешно выполнен");
    }
}