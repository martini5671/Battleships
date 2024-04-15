package battleships2;

import java.awt.*;
import java.util.ArrayList;

public class Ship {
    private final int ShipLength;
    private String ShipName;
    private ArrayList<Point> ShipCoordinates = new ArrayList<>();

    private final String[][] ships_names = {
            {"Carrier", "5"},
            {"Battleship", "4"},
            {"Cruiser/Submarine", "3"},
            {"Destroyer", "2"}
    };

    public Ship( int length) {
        // Validate the input length
        if (length < 2 || length > 5) {
            throw new IllegalArgumentException("Invalid ship length. Must be between 2 and 5.");
        }
        for (String[] shipsName : ships_names) {
            if (shipsName[1].equals(Integer.toString(length))) {
                this.ShipName = shipsName[0];
            }
        }
        this.ShipLength = length;
    }
    public int getShipLength() {
        return ShipLength;
    }

    public String getShipName(){return ShipName;}

    public void addShipCoordinates(Point Coordinates) {
        ShipCoordinates.add(Coordinates);
    }
    public Point takeHit(Point Coordinates)
    {
        Point point_to_remove = new Point(-1,-1);
        for(Point point: ShipCoordinates)
        {
            if(point.x == Coordinates.x && point.y == Coordinates.y)
            {
                System.out.println("You hit the " + ShipName + " (" + ShipLength + " blocks)");
                point_to_remove = point;
                //remove destroyed part of the battleship
                ShipCoordinates.remove(point);
                break;
            }
        }
        if(ShipCoordinates.isEmpty())
        {
            System.out.println("You destroyed " + ShipName);
        }
        return point_to_remove;

    }
    public void printShipCoordinates()
    {
        for(Point point : ShipCoordinates)
        {
            System.out.println(point);
        }
    }
    public void printShipName()
    {
        System.out.println(ShipName);
    }

    public void clearShipCoordinates()
    {
        this.ShipCoordinates.clear();
    }

}
