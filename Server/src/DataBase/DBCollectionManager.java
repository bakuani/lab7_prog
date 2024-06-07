package DataBase;

import OtherClasses.Person;
import OtherClasses.Request;
import OtherClasses.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * работа с бд
 */
public class DBCollectionManager {
    private static final String UPDATE = "UPDATE COLLECTION SET NAME = ?, COORDINATES = ?, CREATIONDATE = ?, HEIGHT = ?, WEIGHT = ?, PASSPORTID = ?, EYECOLOR = ?, LOCATION = ? WHERE ID = ? AND CREATOR = ?";
    private static final String DELETE_WITH_ID = "DELETE FROM COLLECTION WHERE ID = ? AND CREATOR = ?";
    private static final String DELETE_WHERE = "DELETE FROM COLLECTION WHERE ? < name and creator = ?";
    private static final String CLEAR_TABLE = "DELETE * FROM COLLECTION WHERE creator = ?";
    private static final String ADD_USER_REQUEST = "INSERT INTO COLLECTION (name, coordinates, creationdate, height, weight, passportid, eyecolor, location, creator) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * добавление объекта в базу
     * @param person объект
     * @param username какой юзер
     * @return состояние выполнение запроса
     */
    public static boolean add(Person person, String username) {
        try {
            PreparedStatement addStatement = UserManager.getConnection().prepareStatement(ADD_USER_REQUEST);
            addStatement.setString(1, person.getName());
            addStatement.setString(2, person.getCoordinates());
            addStatement.setTimestamp(3, Timestamp.valueOf(person.getCreationDate()));
            addStatement.setDouble(4, person.getHeight());
            addStatement.setDouble(5, person.getWeight());
            addStatement.setString(6, person.getPassportID());
            addStatement.setString(7, person.getEyeColor().toString());
            addStatement.setString(8, person.getLocation().toString());
            addStatement.setString(9, username);
            addStatement.executeUpdate();
            addStatement.close();
            return true;
        } catch (SQLException e){
            return false;
        }
    }

    /**
     * очистка бд
     * @param username юзер
     * @return
     */
    public static boolean clear(String username) {
        try {
            PreparedStatement clearStatement = UserManager.getConnection().prepareStatement(CLEAR_TABLE);
            clearStatement.setString(1, username);
            clearStatement.executeUpdate();
            clearStatement.close();
            return true;
        } catch (SQLException e){
            return false;
        }
    }

    /**
     * удаление строки соответсвтующей условию
     * @param param условие
     * @param username юзер
     * @return статус запроса
     */
    public static boolean deleteWhere(String param, String username) {
        try {
            PreparedStatement deleteWhere = UserManager.getConnection().prepareStatement(DELETE_WHERE);
            deleteWhere.setString(1, param);
            deleteWhere.setString(2, username);
            deleteWhere.executeUpdate();
            deleteWhere.close();
            return true;
        } catch (SQLException e){
            return false;
        }
    }

    /**
     * удаление элемента по ID
     * @param inputedID ID
     * @param username юзер
     * @return состояние запроса
     */
    public static boolean deleteWithID(Long inputedID, String username){
        try{
            PreparedStatement deleteWithID = UserManager.getConnection().prepareStatement(DELETE_WITH_ID);
            deleteWithID.setLong(1, inputedID);
            deleteWithID.setString(2, username);
            deleteWithID.executeUpdate();
            deleteWithID.close();
            return true;
        } catch (SQLException e){
            return false;
        }
    }

    /**
     * обновление объекта
     * @param person новые данные
     * @param inputedID ID
     * @param user юзер
     * @return состояние запроса
     */
    public static boolean update(Person person, Long inputedID, User user){
        try {
            PreparedStatement addStatement = UserManager.getConnection().prepareStatement(UPDATE);
            addStatement.setString(1, person.getName());
            addStatement.setString(2, person.getCoordinates());
            addStatement.setTimestamp(3, Timestamp.valueOf(person.getCreationDate()));
            addStatement.setDouble(4, person.getHeight());
            addStatement.setDouble(5, person.getWeight());
            addStatement.setString(6, person.getPassportID());
            addStatement.setString(7, person.getEyeColor().toString());
            addStatement.setString(8, person.getLocation().toString());
            addStatement.setLong(9, inputedID);
            addStatement.setString(10, user.getUsername());
            addStatement.executeUpdate();
            addStatement.close();
            return true;
        } catch (SQLException e){
            return false;
        }
    }
}
