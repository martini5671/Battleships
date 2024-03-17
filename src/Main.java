
import static battleships2.GenerateRandomBattlefield.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
 class Main {


    public static void main(String[] args)
    {
        for (int i = 0; i <10 ; i++) {
            char[][] battlefield = GenerateBattlefield();
            AddShips(battlefield);
            System.out.println();
            DisplayBattlefield(battlefield);

        }




    }


}