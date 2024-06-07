package OtherClasses;

import java.io.Serializable;
import java.util.Objects;

/**
 * класс для создания координат
 */
public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1;

    private double x;
    private Integer y;

    public Coordinates(double x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates that)) return false;
        return Double.compare(x, that.x) == 0 && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + ";" + y;
    }

}