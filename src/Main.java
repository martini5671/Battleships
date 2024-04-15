
import battleships2.EnemyBattlefield;
import battleships2.PlayersBattlefield;
import battleships2.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

import static battleships2.GenerateRandomBattlefield.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
 class Main {


    public static void main(String[] args)
    {
//        for (int i = 0; i <10 ; i++) {
//            char[][] battlefield = GenerateBattlefield();
//            AddShips(battlefield);
//            System.out.println();
//            DisplayBattlefield(battlefield);
        Point point = new Point();
        point.x = 12;
        point.y = 34;
        System.out.println(point);

        Point point2 = new Point();
        point2.x = 14;
        point2.y = 34;

        Point point3 = new Point();
        point3.x = 14;
        point3.y = 34;


        ArrayList<Point> point_list = new ArrayList<>();
        point_list.add(point);
        System.out.println(point_list);
        System.out.println(point_list.size());

        point_list.add(point2);
        point_list.removeIf(n ->(n.equals(point3)));
        System.out.println(point_list);

        Ship ship = new Ship( 4);
        System.out.println(ship.getShipLength());
        ship.addShipCoordinates(point);
        ship.printShipCoordinates();
        System.out.println(ship.getShipName());
        Point point1 = new Point(45,45);
        Point point7 = new Point(45,46);
        ship.addShipCoordinates(point7);
        ship.addShipCoordinates(point1);
        ship.printShipCoordinates();
        ship.takeHit(point7);
        ship.takeHit(point1);
        ship.takeHit(point);

        EnemyBattlefield enemyBattlefield = new EnemyBattlefield();
        System.out.println("Battlefield with ships:");
        System.out.println("Show coordinates");
        enemyBattlefield.displayShipsNamesAndTheirCoordinates();
       // enemyBattlefield.updateBattlefield();
        for (int i = 0; i < 10; i++) {
            enemyBattlefield.takeShotAtBattlefield((char)(65 + i), (char)(48 +i));
        }

        enemyBattlefield.takeShotAtBattlefield('D', '9');

        String dupa = "dupa";
        dupa.charAt(0);
        System.out.println(dupa.charAt(0));

        PlayersBattlefield playersBattlefield = new PlayersBattlefield();
        playersBattlefield.setShipCoordinates();
        playersBattlefield.displayShipsNamesAndTheirCoordinates();

   }
}