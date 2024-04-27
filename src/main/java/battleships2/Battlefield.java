package battleships2;

import testsandstuff.AudioPlayer;
import Audio.AudioPlayer2;

import java.awt.*;
import java.util.*;

public class Battlefield {
    AudioPlayer audioPlayer = new AudioPlayer();
    public int counter_loops;
    private char[][] Battlefield = new char[12][12];

    private char[][] GameplayBattlefield = new char[12][12];
    private ArrayList<Ship> ShipsArray = new ArrayList<>();
    // ships
    public Battlefield(boolean is_player_battlefield) {
        this.GameplayBattlefield = generateEmptyBattlefield();
        this.ShipsArray.add(new Ship(5));
        this.ShipsArray.add(new Ship(4));
        this.ShipsArray.add(new Ship(3));
        this.ShipsArray.add(new Ship(3));
        this.ShipsArray.add(new Ship(2));
        if (is_player_battlefield)
        {
            this.Battlefield = generateEmptyBattlefield();
            setShipCoordinates();

        }
        else
        {
            this.Battlefield = generateEmptyBattlefield();
            addRandomShips();
        }

    }
    private char[][] generateEmptyBattlefield() {
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
    public void displayFullBattlefield() {
        System.out.println(" ");
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.print(this.Battlefield[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(" ");
    }
    public void displayGameplayBattlefield() {
        System.out.println(" ");
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.print(this.GameplayBattlefield[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(" ");
    }

    public static void displayTwoBattlefields(char[][] this_battlefield,char[][] other_battlefield) {
        System.out.println("   Your battlefield\t\t\t\t Enemie's battlefield");
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.print(this_battlefield[i][j] + " ");
            }
            System.out.print("\t\t");
            for (int j = 0; j < 12; j++) {
                System.out.print(other_battlefield[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Legend: ocean = ~ | ship = O | destroyed cell of ship = X | missed shot = M");
        System.out.println();
    }
    public void displayShipsNamesAndTheirCoordinates()
    {
        for (Ship ship: this.ShipsArray)
        {
            ship.printShipName();
            ship.printShipCoordinates();
        }
    }
    public int getAllShipsLength()
    {
        int sum_segments = 0;
        for(Ship ship: ShipsArray)
        {
            sum_segments = sum_segments + ship.getShipCoordinates().size();
        }
        return sum_segments;
    }

    private void setShipCoordinates() {
        HashMap<Integer, String> command_output = new HashMap<>();
        command_output.put(0, "first");
        command_output.put(1, "second");
        command_output.put(2, "third");
        command_output.put(3, "fourth");
        command_output.put(4, "fifth");

        Scanner scanner = new Scanner(System.in);

        for (Ship ship : ShipsArray) {
            boolean validCoordinates = false;
            while (!validCoordinates) {
                ArrayList<Integer> array_rows = new ArrayList<>();
                ArrayList<Integer> array_columns = new ArrayList<>();
                int counter = 0;

                while (counter != ship.getShipLength()) {
                    System.out.println("PLease provide " +
                            command_output.get(counter) +
                            " coordinate of the " + ship.getShipName() +
                            " using following format: row index + column index (A1,A3,B7 ...)");

                    String userCoordinate = scanner.nextLine();
                    //validate usercoordinate

                    // Split chars:
                    char row_input = userCoordinate.charAt(0);
                    char column_input = userCoordinate.charAt(1);
                    Point player_coordinate = translateBoardCoordinatesToPointCoordinates(row_input, column_input);
                    ship.addShipCoordinates(player_coordinate);
                    array_rows.add(player_coordinate.x);
                    array_columns.add(player_coordinate.y);
                    counter++;
                }

                if (Validators.validateShipPlacementForPlayer(array_rows, array_columns, this.Battlefield)) {
                    System.out.println("Ok you gave proper coordinates. GJ!");
                    for (int i = 0; i < array_rows.size(); i++) {
                        Battlefield[array_rows.get(i)][array_columns.get(i)] = 'O';
                    }
                    displayFullBattlefield();
                    validCoordinates = true;
                } else {
                    System.out.println("Something is wrong with your coordinates! Try again.");
                    ship.clearShipCoordinates();
                }
            }
        }
    }

    public static Point translateBoardCoordinatesToPointCoordinates(char row_index, char column_index)
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

    public char[][] getBattlefield() {
        return Battlefield;
    }

    public void receiveHit(char row_index, char column_index, boolean printMessage)
    {
        AudioPlayer2 audioPlayer2 = new AudioPlayer2();
        Point shot = translateBoardCoordinatesToPointCoordinates(row_index, column_index);
        boolean areAllShipsIntact = true;

        for (Ship ship : ShipsArray)
        {
            if(ship.getShipCoordinates().isEmpty())
            {
                continue;
            }
            // print message parameter
            Point shot_taken = ship.takeHit(shot, printMessage);
            // check shots
            if(shot_taken.y != -1 && shot_taken.x != -1)
            {
                // add coordinates
                Battlefield[shot.x][shot.y] = 'X';
                GameplayBattlefield[shot.x][shot.y] = 'X';
                audioPlayer2.playHitSound();
                areAllShipsIntact = false;
                break;
            }
        }
        if(areAllShipsIntact)
        {
            if(printMessage)
            {
                System.out.println("You missed!!");
            }

            // change board
            Battlefield[shot.x][shot.y]= 'M';
            GameplayBattlefield[shot.x][shot.y] = 'M';
            // play sound
            audioPlayer2.playMissSound();
        }
    }

    public ArrayList<Ship> getShipsArray() {
        return ShipsArray;
    }

    public void setShipsArray(ArrayList<Ship> shipsArray) {
        ShipsArray = shipsArray;
    }

    private void addRandomShips()
    {
        for (Ship ship: ShipsArray)
        {
            boolean repeate = true;
            while(repeate)
            {
                // count loop
                counter_loops ++;
                // starting point + orientation
                Point random_starting_point = getRandomPointOnBoard();
                char random_orientation = getRandomOrientation();

                // other points:
                ArrayList<Point> other_points = getOtherPoints(random_starting_point, random_orientation, ship.getShipLength());

                // connect all points together:
                ArrayList<Point> full_points = connectPoints(random_starting_point, other_points);

                // translate to int array lists in hashmap
                HashMap<String, ArrayList<Integer>> coordinates_hashmap = translatePointsArrayToHashMap(full_points);

                // validate ship placement
                boolean isValid = Validators.validateShipPlacementForEnemy(coordinates_hashmap.get("rows"), coordinates_hashmap.get("columns"),Battlefield);
                if(isValid)
                {
                    // adjust the board
                    for (int j = 0; j < coordinates_hashmap.get("columns").size(); j++) {
                        Battlefield[coordinates_hashmap.get("rows").get(j)][coordinates_hashmap.get("columns").get(j)] = 'O';
                    }
                    // adjust the ships array
                    ship.addShipCoordinates(full_points);

                    // condition to break out of the loop
                    repeate = false;
                }
            }
        }
    }
    private Point getRandomPointOnBoard() {
        // x >= 1 and x <= 11 i to samo z y: y >= 1 i y <= 11
        Random random = new Random();
        int random_row = random.nextInt(11) + 1;
        int random_column = random.nextInt(11) + 1;
        return new Point(random_row, random_column);
    }

    private char getRandomOrientation() {
        Random rand = new Random();
        char[] orientation = {'v', 'h'};
        int random_index = rand.nextInt(2);
        return orientation[random_index];
    }
    private ArrayList<Point> getOtherPoints(Point starting_point, char orientation, int ship_length)
    {
        ArrayList<Point> additional_points = new ArrayList<>();
        int row_starting_point = starting_point.x;
        int column_starting_point = starting_point.y;
        if(orientation == 'v')
        {
            // get other coordinates vertical: same column different rows
            for (int i = 1; i < ship_length; i++) {
                Point additional_point = new Point(row_starting_point + i, column_starting_point);
                additional_points.add(additional_point);
            }
        }
        else
        {
            for (int i = 1; i < ship_length; i++) {
                Point additional_point = new Point(row_starting_point, column_starting_point +i);
                additional_points.add(additional_point);
            }
        }
        return additional_points;
    }
    private ArrayList<Point> connectPoints(Point starting_point, ArrayList<Point> additional_points)
    {
        ArrayList<Point> connected_point_list = new ArrayList<>();
        connected_point_list.add(starting_point);
        connected_point_list.addAll(additional_points);
        return connected_point_list;
    }
    private HashMap<String, ArrayList<Integer>> translatePointsArrayToHashMap(ArrayList<Point> points) {
        HashMap<String, ArrayList<Integer>> resultMap = new HashMap<>();
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> columns = new ArrayList<>();

        for (Point point : points) {
            rows.add(point.x);
            columns.add(point.y);
        }

        resultMap.put("rows", rows);
        resultMap.put("columns", columns);

        return resultMap;
    }

    public char[][] getGameplayBattlefield() {
        return GameplayBattlefield;
    }

}
