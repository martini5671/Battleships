package AI;

import battleships2.Battlefield;
import battleships2.PointWithInteger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class BasicAI {
    private ArrayList<Point> possiblePointCoordinates;

    // store history of shots
    public BasicAI()
    {
        this.possiblePointCoordinates = setPossiblePointCoordinates();
    }
    private ArrayList<Point> setPossiblePointCoordinates()
    {
        ArrayList<Point> pointArrayList = new ArrayList<>();
        for (int i = 1; i < 11; i++)
        {
            for (int j = 1; j < 11; j++)
            {
                Point point = new Point(i,j);
                pointArrayList.add(point);
            }
        }
        return pointArrayList;

    }

    public String getRandomShot()
    {
        Random random = new Random();
        int random_index = random.nextInt(possiblePointCoordinates.size());
        Point random_point = possiblePointCoordinates.get(random_index);
        String random_string_coordinate = Battlefield.translatePointCoordinatesToStringCoordinates(random_point);
        removePointFromCoordinatesList(random_point);
        return random_string_coordinate;
    }


    public void printCoordinates()
    {
        for (Point point: possiblePointCoordinates)
        {
            System.out.println(point);
        }
    }
    public void removePointFromCoordinatesList(Point point)
    {
        possiblePointCoordinates.remove(point);
    }
    public void removePointsFromCoordinatesList(ArrayList<Point> pointArrayList)
    {
        for (Point point: pointArrayList)
        {
            possiblePointCoordinates.remove(point);
        }
    }
    public void addPointsToCoordinatesList(ArrayList<Point> pointArrayList)
    {
        possiblePointCoordinates.addAll(pointArrayList);
    }

    public ArrayList<Point> getPossiblePointCoordinates() {
        return possiblePointCoordinates;
    }
}
