package DataBase;

import Commands.CommandList;
import OtherClasses.Color;
import OtherClasses.Coordinates;
import OtherClasses.Location;
import OtherClasses.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

public class FromDB {
    private static final String REQUEST = "SELECT * FROM collection";

    private final Connection connection;

    public FromDB(Connection connection) {
        this.connection = connection;
    }

    /**
     * загрузка коллекции из бд
     *
     * @return
     */
    public CopyOnWriteArrayList<Person> loadCollection() {
        CopyOnWriteArrayList<Person> collection = new CopyOnWriteArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(REQUEST);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Person p = extractPersonFromResult(result);
                collection.add(p);
                CommandList.getIDs().add(p.getId());
            }

            statement.close();
            System.out.println("Коллекция успешно загружена");
        } catch (SQLException e) {
            System.out.println("Произошла ошибка загрузки коллекции");
            e.printStackTrace();
            System.exit(-1);
        }

        return collection;
    }

    /**
     * создание объектта из бд
     *
     * @param result данные из бд
     * @return объект
     * @throws SQLException
     */
    private Person extractPersonFromResult(ResultSet result) {
        try {
            long ID = result.getLong("id");
            String name = result.getString("name");
            String coordinatesFromDB = result.getString("coordinates");
            String[] XAndY = coordinatesFromDB.split(";");
            Double x = Double.valueOf(XAndY[0]);
            Integer y = Integer.valueOf(XAndY[1]);
            Coordinates coordinates = new Coordinates(x, y);
            Double height = result.getDouble("height");
            Double weight = result.getDouble("weight");
            String passportID = result.getString("passportid");
            String color;
            Color eyeColor = null;
            color = result.getString("eyecolor");
            switch (color) {
                case ("зелёный"):
                    eyeColor = Color.GREEN;
                    break;
                case ("синий"):
                    eyeColor = Color.BLUE;
                    break;
                case ("белый"):
                    eyeColor = Color.WHITE;
                    break;
                case ("коричневый"):
                    eyeColor = Color.BROWN;
                    break;
            }

            String locationFromDB = result.getString("location");
            String[] xAndYAndZAndName = locationFromDB.split(";");
            Integer LocationX = Integer.valueOf(xAndYAndZAndName[0]);
            Double LocationY = Double.valueOf(xAndYAndZAndName[1]);
            Long LocationZ = Long.valueOf(xAndYAndZAndName[2]);
            String LocationName = xAndYAndZAndName[3];
            Location location = new Location(LocationX, LocationY, LocationZ, LocationName);
            String creator = result.getString("creator");

            Person person = new Person(ID, name, coordinates, height, weight, passportID, eyeColor, location, creator);

            return person;
        } catch (SQLException e) {
            System.out.println("Ошибка в чтении с базы данных");
            return null;
        }
    }

}