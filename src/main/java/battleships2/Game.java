package battleships2;

import AI.EnemyIA;

public class Game {
    public static void main(String[] args) {
        Battlefield enemyB = new Battlefield(false);
        // tu dodać metode zapogiegajaca złemu inputowi
        Battlefield playerB = new Battlefield(true);
        EnemyIA enemyIA = new EnemyIA();

        while(enemyB.getAllShipsLength()!=0 && playerB.getAllShipsLength() !=0)
        {
            String playerShotString = Validators.getValidCoordinatesToAttack(enemyB.getBattlefield());
            //receive hit enemy:
            enemyB.receiveHit(playerShotString.charAt(0), playerShotString.charAt(1),true);
            Battlefield.displayTwoBattlefields(playerB.getBattlefield(), enemyB.getGameplayBattlefield());

            //enemy turn:
            System.out.println("Now its enemie's turn...");
            String enemy_shot = enemyIA.getRandomShot();
            System.out.println("The enemy has taken a shot at field: " + enemy_shot);
            System.out.println(" ");

            //wait and receive hit by player
            wait2seconds();
            playerB.receiveHit(enemy_shot.charAt(0), enemy_shot.charAt(1), false);
            Battlefield.displayTwoBattlefields(playerB.getBattlefield(), enemyB.getGameplayBattlefield());
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

    public static void wait2seconds()
    {
        try {
            Thread.sleep(2000); // Sleep for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
