package battleships2;

import java.awt.*;
import java.util.*;

public class EnemyBattlefield {
    private char[][] BattlefieldWShips = new char[12][12];
    // ships

    private ArrayList<Ship> ShipsArray = new ArrayList<>();

    public EnemyBattlefield() {
        ShipsArray.add(new Ship(5));
        ShipsArray.add(new Ship(4));
        ShipsArray.add(new Ship(3));
        ShipsArray.add(new Ship(3));
        ShipsArray.add(new Ship(2));
        this.BattlefieldWShips = addShipsToEmptyBattlefield(generateEmptyBattlefield());
    }
    public char[][] generateEmptyBattlefield() {
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

    public void displayBattlefieldWithShips() {
        System.out.println(Arrays.deepToString(this.BattlefieldWShips).replace("], ", "]\n").
                replace("[[", "[").replace("]]", "]"));
    }
    public void displayShipsNamesAndTheirCoordinates()
    {
        for (Ship ship: this.ShipsArray)
        {
            ship.printShipName();
            ship.printShipCoordinates();

        }
    }


    public char[][] addShipsToEmptyBattlefield(char[][] battlefield)
    {
        ArrayList<Integer> ships_length = new ArrayList<>();
        Collections.addAll(ships_length, 5, 4, 3, 3, 2);
        int index_ship_array = 0;
        for(int ship_length: ships_length)
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

                int n_of_cells = ship_length - 1;

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
                        if(Validators.validateShipPlacement(ship_row_indexes, ship_column_indexes, battlefield))
                        {
                            for (int j = 0; j < ship_row_indexes.size(); j++) {
                                battlefield[ship_row_indexes.get(j)][ship_column_indexes.get(j)] = 'O';
                                Point point_add = new Point(ship_row_indexes.get(j), ship_column_indexes.get(j));
                                ShipsArray.get(index_ship_array).addShipCoordinates(point_add);
                            }
                            index_ship_array ++;
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
                        if( Validators.validateShipPlacement(ship_row_indexes, ship_column_indexes, battlefield)) {
                            for (int j = 0; j < ship_row_indexes.size(); j++) {
                                battlefield[ship_row_indexes.get(j)][ship_column_indexes.get(j)] = 'O';
                                Point point_add = new Point(ship_row_indexes.get(j), ship_column_indexes.get(j));
                                ShipsArray.get(index_ship_array).addShipCoordinates(point_add);
                            }
                            index_ship_array ++;
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
        return battlefield;
    }


    public void takeShotAtBattlefield(char row_index, char column_index)
    {

        Point shot = translateBoardCoordinatesToPointCoordinates(row_index, column_index);
        boolean cont = true;
        for (Ship ship : ShipsArray)
        {
           Point shot_taken = ship.takeHit(shot);
           if(shot_taken.y != -1 && shot_taken.x != -1)
           {
               BattlefieldWShips[shot.x][shot.y] = 'X';
               cont = false;
               break;
           }
        }
        if(cont)
        {
            System.out.println("You missed!!");
            BattlefieldWShips[shot.x][shot.y]= 'M';
        }
    }

    public Point translateBoardCoordinatesToPointCoordinates(char row_index, char column_index)
    {
        int char_ascii = 65;
        int number_ascii = 48;
        HashMap<Character, Integer> rows = new HashMap<>(10);
        HashMap<Character, Integer> column = new HashMap<>(10);
        for (int i = 0; i < 10; i++) {
            rows.put((char)char_ascii, i+1);
            char_ascii++;
            column.put((char)number_ascii, i+1);
            number_ascii++;
        }
        // x rzad, y = kolumna
        Point point_to_return = new Point();
        point_to_return.x = rows.get(row_index);
        point_to_return.y = column.get(column_index);

        return point_to_return;

    }

}
