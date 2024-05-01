package AI;

import battleships2.Battlefield;
import battleships2.PointWithInteger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MidAI extends BasicAI {

    private ArrayList<Point> AdjacentCells = new ArrayList<>();
    private ArrayList<PointWithInteger> PWI_history = new ArrayList<>();

    public MidAI() {
        super();
    }
    public String getShot()
    {

        // case 1 no history:
        if (PWI_history.isEmpty()) {
            return super.getRandomShot();
        } else if (PWI_history.getLast().getPoint().x != -1 && AdjacentCells.isEmpty()) {
            // go into hunter mode
            Point last_point = PWI_history.getLast().getPoint();
            setAdjacentCells(last_point);
            Point point_shot = getRandomPointFromAdjacentCells();
            return Battlefield.translatePointCoordinatesToStringCoordinates(point_shot);
        } else if (PWI_history.getLast().getPoint().x != -1 && !AdjacentCells.isEmpty()) {
            // return cells to the pool of available cells
            addPointsToCoordinatesList(AdjacentCells);
            // clear entire list
            AdjacentCells.clear();
            //set new cells
            setAdjacentCells(PWI_history.getLast().getPoint());
            Point point_shot = getRandomPointFromAdjacentCells();
            return Battlefield.translatePointCoordinatesToStringCoordinates(point_shot);
        } else if (PWI_history.getLast().getPoint().x == -1 && AdjacentCells.isEmpty()) {
            return super.getRandomShot();
        } else //(PWI_history.getLast().getPoint().x == -1 && !AdjacentCells.isEmpty())
        {
            // continue shooting adjacent points
            Point point_shot = getRandomPointFromAdjacentCells();
            return Battlefield.translatePointCoordinatesToStringCoordinates(point_shot);
        }

    }

    public void storeFeedback(PointWithInteger PWI)
    {
        PWI_history.add(PWI);
    }

    private void setAdjacentCells(Point point) {
        ArrayList<Point> points = new ArrayList<>();
        Point point_up = new Point(point.x - 1, point.y);
        Point point_down = new Point(point.x + 1, point.y);
        Point point_left = new Point(point.x, point.y - 1);
        Point point_right = new Point(point.x, point.y + 1);
        points.add(point_up);
        points.add(point_down);
        points.add(point_left);
        points.add(point_right);
        // discard those which are outside the borders
        java.util.List<Point> filtered_list_points = points.stream().filter(p -> p.x >= 1 && p.x <= 10)
                .filter(p -> p.y >= 1 && p.y <= 10)
                .collect(Collectors.toList());
        ArrayList<Point> adjacent_cells = (ArrayList<Point>) filtered_list_points;
        // filter adjacent cells and get intersection with the available coordinates
        ArrayList<Point> intersection = getIntersectionWithAvailableCoordinates(adjacent_cells);
        super.removePointsFromCoordinatesList(intersection);
        AdjacentCells = intersection;
    }

    private Point getRandomPointFromAdjacentCells()
    {
        Random random = new Random();
        if(AdjacentCells.isEmpty())
        {
            String random_shot = super.getRandomShot();
            return Battlefield.translateBoardCoordinatesToPointCoordinates(random_shot.charAt(0), random_shot.charAt(1));
        }
        else
        {
            int random_index = random.nextInt(AdjacentCells.size());
            Point random_point = AdjacentCells.get(random_index);
            //remove
            AdjacentCells.remove(random_point);
            return random_point;
        }

    }

    private ArrayList<Point> getIntersectionWithAvailableCoordinates(ArrayList<Point> list)
    {
        java.util.List<Point> intersect = list.stream()
                .filter(super.getPossiblePointCoordinates()::contains)
                .collect(Collectors.toList());
        return (ArrayList<Point>) intersect;
    }
    private ArrayList<Point> getIntersectionTwoLists(ArrayList<Point> list,ArrayList<Point> list2)
    {
        List<Point> intersect = list.stream()
                .filter(list2::contains)
                .collect(Collectors.toList());
        return (ArrayList<Point>) intersect;
    }

}
