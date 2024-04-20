package battleships2;

import java.awt.*;
import java.util.*;

public class Validators {
    public static boolean validateShipPlacementForEnemy(ArrayList<Integer> rows, ArrayList<Integer> columns, char[][] battlefield)
    {
        return arePointsOfShipOnTheBoard(rows, columns) &&
                isSurroundingAvailable(rows, columns, battlefield) &&
                arePointsNotOccupiedByOtherShip(rows, columns, battlefield);
    }
    public static boolean validateShipPlacementForPlayer(ArrayList<Integer> rows, ArrayList<Integer> columns, char[][] battlefield)
    {
        return arePointsOfShipOnTheBoard(rows, columns) &&
                isSurroundingAvailable(rows, columns, battlefield) &&
                arePointsNotOccupiedByOtherShip(rows, columns, battlefield) &&
                arePointsInLine(rows, columns)&&
                areAllPointsUnique(rows, columns)&&
                areAllPointsTogether(rows, columns);

    }


    public static boolean arePointsNotOccupiedByOtherShip(ArrayList<Integer> rows, ArrayList<Integer> columns, char[][]battlefield)
    {  boolean valid = true;
        for (int i = 0; i < rows.size(); i++) {
            int row = rows.get(i);
            int column = columns.get(i);
            if (battlefield[row][column] == 'O') {
                valid = false;
                break;
            }
        }
        return valid;
    }
    public static boolean isSurroundingAvailable(ArrayList<Integer> rows, ArrayList<Integer> columns, char[][]battlefield)
    {
        boolean valid = true;
        if(Objects.equals(rows.get(0), rows.get(1))) // to znaczy że statek jest poziomo
        {
            // check above
            for (Integer column : columns) {
                if (battlefield[rows.getFirst() + 1][column] == 'O') {
                    valid = false;
                    break;
                }
            }
            // check below
            for (Integer column : columns) {
                if (battlefield[rows.getFirst() -1][column] == 'O') {
                    valid = false;
                    break;
                }
            }
            // check left and right
            if(battlefield[rows.getFirst()][columns.getFirst() +1] == 'O')
            {
                valid = false;
            }
            if(battlefield[rows.getFirst()][columns.getLast() - 1] == 'O')
            {
                valid = false;
            }

        }
        else // this checks vertical ship
        {
            // check left
            for (Integer row : rows) {
                if (battlefield[row][columns.getFirst()-1] == 'O') {
                    valid = false;
                    break;
                }
            }
            // check right
            for (Integer row : rows) {
                if (battlefield[row][columns.getFirst() + 1] == 'O') {
                    valid = false;
                    break;
                }
            }
            // check down
            if(battlefield[rows.getFirst() -1][columns.getFirst()] == 'O')
            {
                valid = false;
            }

            // check up
            if(battlefield[rows.getLast() +1][columns.getFirst()] == 'O')
            {
                valid = false;
            }
        }
        return valid;
    }


    public static boolean arePointsOfShipOnTheBoard(ArrayList<Integer> rows, ArrayList<Integer> columns) {
        boolean is_valid = true;
        for (Integer value : rows) {
            if (value >10 || value < 1) {
                is_valid = false;
                break;
            }
        }
        for (Integer value : columns) {
            if (value > 10 || value < 1) {
                is_valid = false;
                break;
            }
        }

        return is_valid;
    }
    public static boolean arePointsInLine(ArrayList<Integer> rows, ArrayList<Integer> columns)
    {
        return (rows.stream().distinct().count() <= 1) || columns.stream().distinct().count() <= 1;

    }
    public static boolean areAllPointsUnique(ArrayList<Integer> rows, ArrayList<Integer> columns)
    {
        HashSet<Integer> hash_coordinates = new HashSet<Integer>();
        for (int i = 0; i < rows.size(); i++) {
            int concat_coordinates = concatenateIntegers(rows.get(i), columns.get(i));
            hash_coordinates.add(concat_coordinates);
        }
        return hash_coordinates.size() == rows.size();

    }

    public static boolean areAllPointsTogether(ArrayList<Integer> rows, ArrayList<Integer> columns) {
        Collections.sort(rows);
        Collections.sort(columns);
        if (rows.stream().distinct().count() > 1 &&
                columns.stream().distinct().count() > 1) {
            return false;
        }

        // gdy pionowo wszystkie wartości sa takie same;
        else if (rows.stream().distinct().count() <= 1) {
            // check columns
            for (int i = 0; i < rows.size() - 1; i++) {
                int difference_columns = Math.abs(columns.get(i + 1) - columns.get(i));
                if (difference_columns != 1) {
                    return false;
                }
            }
        } else if (columns.stream().distinct().count() <= 1) {
            for (int i = 0; i < rows.size() - 1; i++) {
                int difference_rows = Math.abs(rows.get(i + 1) - rows.get(i));
                if (difference_rows != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int concatenateIntegers(int a, int b) {
        return a * 10 + b;
    }
}
