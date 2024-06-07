package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String URL;
    private final String username;
    private final String password;

    public DBConnection(String URL, String username, String password) {
        this.URL = URL;
        this.username = username;
        this.password = password;
    }

    /**
     * @return подключение к бд
     */
    public Connection connectToDB(){
        try {
            Connection connection = DriverManager.getConnection(URL, username, password);
            System.out.println("Есть пробитие!!!!");
            return connection;
        } catch (SQLException e){
            System.err.println("Не удалось подключиться");
            System.exit(-1);
        }

        return null;
    }
}
