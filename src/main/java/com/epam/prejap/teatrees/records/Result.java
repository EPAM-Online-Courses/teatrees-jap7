package com.epam.prejap.teatrees.records;

import java.util.Scanner;

/**
 * @author Herman Kulik
 */
public class Result {

    public static void concludeTheGame(int score){
        System.out.println("Type your name (only 3 letters allowed): ");
        Scanner scanner = new Scanner(System.in);
        String nameOfThePlayer = scanner.nextLine();
        while(nameOfThePlayer.length()!=3){
            System.out.println("Retype your name! Only 3 letters are allowed!:");
            nameOfThePlayer = scanner.nextLine();
        }
        boolean isNewBest = new RecordHandler().handleNewRecord(new Record(nameOfThePlayer,score));
        if(isNewBest) {
            System.out.println("Congrats! It's your new record " + nameOfThePlayer +"!");
        }
    }
}
