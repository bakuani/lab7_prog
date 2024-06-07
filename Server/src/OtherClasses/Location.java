package OtherClasses;

import java.io.Serializable;
import java.util.Objects;

/**
 * класс для определении местанахождения
 */
public class Location implements Serializable {
    private static final long serialVersionUID = 2;
    private int x;
    private Double y;
    private Long z;
    private String name;

    public Location(int x, Double y, Long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;
        return x == location.x && Objects.equals(y, location.y) && Objects.equals(z, location.z) && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }

    @Override
    public String toString() {
        return x + ";" + y + ";" + z + ";" + name;
    }


}
