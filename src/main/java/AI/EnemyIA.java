package AI;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class EnemyIA {
    private ArrayList<String> possibleCoordinates;
    public EnemyIA()
    {
        this.possibleCoordinates = generateCoordinates();
    }
    private ArrayList<String> generateCoordinates() {

        int letterAscii = 65;
        int numberAscii = 48;
        ArrayList<String> arrayCoordinates = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                StringBuilder sb = new StringBuilder();
                char letter = (char)(letterAscii + i);
                char number = (char)(numberAscii + j);
                sb.append(letter);
                sb.append(number);
                arrayCoordinates.add(sb.toString());
            }
        }
        return arrayCoordinates;
    }
    public String getRandomShot()
    {
        Random random = new Random();
        int random_index = random.nextInt(possibleCoordinates.size());
        String random_string = possibleCoordinates.get(random_index);
        possibleCoordinates.remove(random_string);
        return random_string;
    }
    public void printCoordinates()
    {
        for (String string: possibleCoordinates)
        {
            System.out.println(string);
        }
    }
}
