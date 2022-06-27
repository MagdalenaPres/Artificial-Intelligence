import java.util.ArrayList;
import java.util.Objects;

public class Machine
{
    private final int id;
    private final int x;
    private final int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Machine machine = (Machine) o;
        return x == machine.x && y == machine.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Machine(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", x=" + x +
                ", y=" + y;
    }
}
