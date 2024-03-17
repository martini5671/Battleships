package battleships2;

import java.util.*;

public class GenerateRandomBattlefield {
    public static char[][] GenerateBattlefield() {
        // Declaration of 2D array
        char[][] battlefield = new char[12][12];

        int char_ascii = 65;
        int number_ascii = 48;

        for (int i = 0; i < battlefield.length ; i++) {
            for (int j = 0; j < battlefield.length; j++) {
                if(j ==0 && i > 0 && i != 11)
                {   // pionowo jest wstawiany alfabet
                    battlefield[i][j] = (char) char_ascii;
                    battlefield[i][j+11] = (char) char_ascii;
                    char_ascii++;
                }
                if(i ==0 && j >0 && j!= 11)
                {   //poziomo wstawiamy numery
                    battlefield[i][j] = (char) number_ascii;
                    battlefield[i+11][j] = (char) number_ascii;
                    number_ascii++;
                }
                if((i>0 && i <11)&&(j>0 && j<11 ))
                {
                    battlefield[i][j] = '~';
                }
            }
        }
        battlefield[0][0] = '+'; // Top left corner
        battlefield[0][11] = '+'; // Top right corner
        battlefield[11][0] = '+'; // Bottom left corner
        battlefield[11][11] = '+'; // Bottom right corner

        return battlefield;
    }
    public static void AddShips(char[][] battlefield)
    {
        //array list for the length of ships
        ArrayList<Integer> ships_length = new ArrayList<>();
        Collections.addAll(ships_length, 5, 4, 3, 3, 2);
        for(int ship_length: ships_length)
        {
            AddShipsToBattlefield(ship_length, battlefield);
        }

    }
    public static void AddShipsToBattlefield( int length_ship, char[][] battlefield)
    {
        //do while cond
        boolean do_while_cond = true;

        do {
            // get random
            Random rand = new Random();

            // make array list with all and column indexes
            ArrayList<Integer> start_row_index = new ArrayList<>();
            ArrayList<Integer> start_column_index = new ArrayList<>();
            Integer[] elements = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

            // Add elements from the array to the ArrayList
            Collections.addAll(start_row_index, elements);
            Collections.addAll(start_column_index, elements);

            // get random index of culumn and row to start
            int start_row = start_row_index.get(rand.nextInt(elements.length));
            int start_column = start_column_index.get(rand.nextInt(elements.length));

            ArrayList<Character> directions = new ArrayList<>();
            Collections.addAll(directions, 'u', 'l');

            ArrayList<Integer> ship_row_indexes = new ArrayList<>();
            ArrayList<Integer> ship_column_indexes = new ArrayList<>();

            int n_of_cells = length_ship - 1;

            for (int i = 0; i < 2; i++) {

                int randomIndex = rand.nextInt(directions.size()); // random index
                char randomDirection = directions.get(randomIndex); // random direction

                if (randomDirection == 'u') {
                    // add starting points
                    ship_row_indexes.add(start_row);
                    ship_column_indexes.add(start_column);

                    // get middle coordinates
                    for (int j = 1; j < n_of_cells; j++) {
                        ship_row_indexes.add(start_row + j);
                        ship_column_indexes.add(start_column);
                    }
                    // add last point to the array
                    ship_row_indexes.add(start_row + n_of_cells); // coordinates of end point
                    ship_column_indexes.add(start_column); // coordinates of end point

                    // check all conditions
                    if(ValidateShipPlacement(ship_row_indexes, ship_column_indexes, battlefield))
                     {
                        for (int j = 0; j < ship_row_indexes.size(); j++) {
                            battlefield[ship_row_indexes.get(j)][ship_column_indexes.get(j)] = 'O';
                        }
                        do_while_cond = false;
                        break;
                    } else {
                        directions.remove(randomIndex); // remove not valid direction
                        ship_row_indexes.clear();
                        ship_column_indexes.clear();
                    }
                }
                if (randomDirection == 'l') {
                    // add starting points
                    ship_row_indexes.add(start_row);
                    ship_column_indexes.add(start_column);

                    // get middle coordinates
                    for (int j = 1; j < n_of_cells; j++) {
                        ship_row_indexes.add(start_row);
                        ship_column_indexes.add(start_column - j);
                    }
                    // add last point to the array
                    ship_row_indexes.add(start_row); // coordinates of end point
                    ship_column_indexes.add(start_column - n_of_cells); // coordinates of end point

                    // check all conditions
                    if(ValidateShipPlacement(ship_row_indexes, ship_column_indexes, battlefield)) {
                        for (int j = 0; j < ship_row_indexes.size(); j++) {
                            battlefield[ship_row_indexes.get(j)][ship_column_indexes.get(j)] = 'O';
                            // tu zabrakło indeksu startowego
                        }
                        do_while_cond = false;
                        break;
                    } else {
                        directions.remove(randomIndex); // remove not valid direction
                        ship_row_indexes.clear();
                        ship_column_indexes.clear();
                    }

                }
            }
        }
        while (do_while_cond);
    }
    // ArePointsOfShipOnTheBoard
    public static boolean ValidateShipPlacement(ArrayList<Integer> rows, ArrayList<Integer> columns, char[][] battlefield)
    {
        return ArePointsOfShipOnTheBoard(rows, columns) && IsSurroundingAvailable(rows, columns, battlefield) && ArePointsNotOccupiedByOtherShip(rows, columns, battlefield);
    }

    public static boolean ArePointsNotOccupiedByOtherShip(ArrayList<Integer> rows, ArrayList<Integer> columns, char[][]battlefield)
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
    public static boolean IsSurroundingAvailable(ArrayList<Integer> rows, ArrayList<Integer> columns, char[][]battlefield)
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


    public static boolean ArePointsOfShipOnTheBoard(ArrayList<Integer> rows, ArrayList<Integer> columns) {
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


    public static void DisplayBattlefield(char[][] battlefield)
    {
        System.out.println(Arrays.deepToString(battlefield).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

    }

}


