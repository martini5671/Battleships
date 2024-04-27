package battleships2;

import AI.EnemyIA;

import java.awt.*;
import java.util.Scanner;

public class Game {
    // ask player to set ship coordinates
    // generate battlefield for enemy
    // toss a coin to find out who has the first turn
    // btw: new class: Enemy AI which will take as input the battlefield
    // if enemy:
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Battlefield enemyB = new Battlefield(false);
        // tu dodać metode zapogiegajaca złemu inputowi
        Battlefield playerB = new Battlefield(true);
        EnemyIA enemyIA = new EnemyIA();

        while(enemyB.getAllShipsLength()!=0 && playerB.getAllShipsLength() !=0)
        {
            String playerShotString = Validators.getValidCoordinatesInput(enemyB.getBattlefield());
            //receive hit enemy:
            enemyB.receiveHit(playerShotString.charAt(0), playerShotString.charAt(1),true);
            Battlefield.displayTwoBattlefields(playerB.getBattlefield(), enemyB.getBattlefield());

            //enemy turn:
            System.out.println("Now its enemie's turn...");
            String enemy_shot = enemyIA.getRandomShot();
            System.out.println("The enemy has taken a shot at field: " + enemy_shot);
            System.out.println(" ");

            try {
                Thread.sleep(2000); // Sleep for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            playerB.receiveHit(enemy_shot.charAt(0), enemy_shot.charAt(1), false);
            Battlefield.displayTwoBattlefields(playerB.getBattlefield(), enemyB.getBattlefield());
        }
        if(enemyB.getAllShipsLength() > playerB.getAllShipsLength())
        {
            System.out.println("You lost!");
        }
        else
        {
            System.out.println("You won!");
        }

    }

}
