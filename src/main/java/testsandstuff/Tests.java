package testsandstuff;

import battleships2.Battlefield;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

// Java program to play an Audio
// file using Clip Object
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Tests {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
//        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");
//        PrintStream mainStream = new PrintStream(fileOutputStream);
//
//        PrintStream secondaryStream = System.out; // Use System.out for the secondary output stream
//
//        DualPrintStream dualPrintStream = new DualPrintStream(mainStream, secondaryStream);
//
//        System.setOut(dualPrintStream);
//        System.out.println("Hello, World!");
//        System.out.println("Hello, World!");
//        System.out.println("Hello, World!");
//        System.out.println("Hello, World!");
//        dualPrintStream.close();

        char[][] batllefield1 = generateEmptyBattlefield();
        char[][] battledield2 = generateEmptyBattlefield();
        displayTwoBattlefields(battledield2, batllefield1);
        displayOneBattlefield(batllefield1);


        for (int i = 0; i < 100; i++) {
            Battlefield battlefield = new Battlefield(false);
            battlefield.displayFullBattlefield();
        }


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
    }

    private static char[][] generateEmptyBattlefield() {
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


    public static void displayOneBattlefield(char[][] this_battlefield) {
        System.out.println(" ");
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.print(this_battlefield[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(" ");
    }
}
