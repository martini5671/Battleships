package battleships2;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;  // Import the Scanner class

public class PlayersBattlefield extends EnemyBattlefield {
    private char[][] BattlefieldWShips_player;
    // ships
    private ArrayList<Ship> ShipsArray_player = new ArrayList<>();
    // user input sout output


    public PlayersBattlefield() {
        ShipsArray_player.add(new Ship(5));
        ShipsArray_player.add(new Ship(4));
        ShipsArray_player.add(new Ship(3));
        ShipsArray_player.add(new Ship(3));
        ShipsArray_player.add(new Ship(2));
        this.BattlefieldWShips_player = generateEmptyBattlefield();
    }
    public void setShipCoordinates() {
        HashMap<Integer, String> command_output = new HashMap<>();
        command_output.put(0, "first");
        command_output.put(1, "second");
        command_output.put(2, "third");
        command_output.put(3, "fourth");
        command_output.put(4, "fifth");

        Scanner scanner = new Scanner(System.in);

        for (Ship ship : ShipsArray_player) {
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
                    // Split chars:
                    char row_input = userCoordinate.charAt(0);
                    char column_input = userCoordinate.charAt(1);
                    Point player_coordinate = translateBoardCoordinatesToPointCoordinates(row_input, column_input);
                    ship.addShipCoordinates(player_coordinate);
                    array_rows.add(player_coordinate.x);
                    array_columns.add(player_coordinate.y);

                    counter++;
                }

                if (Validators.validateShipPlacementForPlayer(array_rows, array_columns, this.BattlefieldWShips_player)) {
                    System.out.println("Ok you gave proper coordinates. GJ!");
                    for (int i = 0; i < array_rows.size(); i++) {
                        BattlefieldWShips_player[array_rows.get(i)][array_columns.get(i)] = 'O';
                    }
                    displayBattlefieldWithShips();
                    validCoordinates = true;
                } else {
                    System.out.println("Something is wrong with your coordinates! Try again.");
                    ship.clearShipCoordinates();
                }
            }
        }
    }

    @Override
    public void displayBattlefieldWithShips() {
        System.out.println(Arrays.deepToString(this.BattlefieldWShips_player).replace("], ", "]\n").
                replace("[[", "[").replace("]]", "]"));
    }
    @Override
    public void displayShipsNamesAndTheirCoordinates()
    {
        for (Ship ship: ShipsArray_player)
        {
            ship.printShipName();
            ship.printShipCoordinates();

        }
    }

}
