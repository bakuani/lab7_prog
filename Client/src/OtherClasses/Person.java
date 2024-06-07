package OtherClasses;

import Interfaces.IDGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * тип элементов, содержащихся в коллекции
 */
public class Person implements Comparable<Person>, Serializable {
    private static final long serialVersionUID = 3;
    private long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private double height;
    private double weight;
    private String passportID;
    private Color eyeColor;
    private Location location;
    private String creator;

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    /**
     * @return ID элемента
     */
    public long getId() {
        return id;
    }

    /**
     * устанавливает заданный ID
     *
     * @param id заданный ID
     */
    public void setId(long id) {
        this.id = id;

    }

    /**
     * @return имя элемента
     */
    public String getName() {
        return name;
    }

    /**
     * @return паспортный ID элемента
     */
    public String getPassportID() {
        return passportID;
    }

    public Person(String name, Coordinates coordinates, double height, double weight, String passportID, Color eyeColor, Location location) {
        id = IDGenerator.createID();
        this.name = name;
        this.coordinates = coordinates;
        creationDate = LocalDateTime.now();
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.location = location;
    }
    public Person(Long id, String name, Coordinates coordinates, double height, double weight, String passportID, Color eyeColor, Location location) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        creationDate = LocalDateTime.now();
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return id == person.id && Double.compare(height, person.height) == 0 && Double.compare(weight, person.weight) == 0 && Objects.equals(name, person.name) && Objects.equals(coordinates, person.coordinates) && Objects.equals(creationDate, person.creationDate) && Objects.equals(passportID, person.passportID) && eyeColor == person.eyeColor && Objects.equals(location, person.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, height, weight, passportID, eyeColor, location);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + coordinates + "," + creationDate + "," + height + "," + weight + "," + passportID + "," + eyeColor + "," + location;
    }

    @Override
    public int compareTo(Person o) {
        return Long.compare(id, o.getId());
    }
}
