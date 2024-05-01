package battleships2;

import java.awt.*;

public class PointWithInteger {
    public Point point;
    public int value;

    public Point getPoint() {
        return point;
    }

    public int getValue() {
        return value;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public PointWithInteger(Point point, int value) {
        this.point = point;
        this.value = value;
    }
    public void printPointWithInteger()
    {
        System.out.println(point);
        System.out.println(value);
    }

    public PointWithInteger() {

    }

}

