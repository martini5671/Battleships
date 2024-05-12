package battleships2;

import AI.BasicAI;
import AI.MidAI;
import Stats.DescriptiveStats;
import Stats.HistogramPlotter;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.IOException;
import java.util.ArrayList;

public class Game2 {
    public static void main(String[] args) throws PythonExecutionException, IOException {
        ArrayList<Integer> shots_counter_mid_AI = new ArrayList<>();
        ArrayList<Integer> shots_counter_basic_AI = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {

            int counter_1 = 0;
            int counter_2 =0;

            Battlefield enemyB = new Battlefield(false);
            // tu dodać metode zapogiegajaca złemu inputowi
            Battlefield playerB = new Battlefield(false);
            MidAI mediumAI = new MidAI();
            BasicAI basicAI = new BasicAI();

            while(playerB.getAllShipsLength() !=0)
            {
                //enemy turn:
                String enemy_shot = mediumAI.getShot();
                PointWithInteger feedback = playerB.receiveHit(enemy_shot.charAt(0), enemy_shot.charAt(1), false, false);
                mediumAI.storeFeedback(feedback);
                counter_1 = counter_1 +1;
            }
            // save the number of shots before the end of the game
            shots_counter_mid_AI.add(counter_1);

            while (enemyB.getAllShipsLength() !=0)
            {
                String shot = basicAI.getRandomShot();
                enemyB.receiveHit(shot.charAt(0), shot.charAt(1),false, false);
                counter_2 = counter_2 +1;
            }
            // save the number of shots before the end of the game
            shots_counter_basic_AI.add(counter_2);
        }
        // get stats:

        //get histograms:
        HistogramPlotter.plotOverlappingHistograms(shots_counter_basic_AI, shots_counter_mid_AI, "Random AI", "MidAI");

        System.out.println("Stats for better AI: ");
        DescriptiveStats.displayDescriptiveStats(shots_counter_mid_AI);
        System.out.println("Stats for random AI: ");
        DescriptiveStats.displayDescriptiveStats(shots_counter_basic_AI);

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
