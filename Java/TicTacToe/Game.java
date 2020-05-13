import java.util.Scanner;

public class Game {

    private static Scanner scan;
    private static boolean runProgram;
    private static Game game;

    private boolean gameDone;
    private char currentLetter;
    private Board gameBoard;

    public static void main( String[] args ) {
        //instantiate the scanner
        scan = new Scanner( System.in );

        //set the runProgram boolean to true
        runProgram = true;

        //main program loop
        while( !runProgram ) {
            game = new Game();
            while( !game.gameDone ) {

            }
        }

        //close the scanner
        scan.close();
    }

    public Game() {
        gameDone = false;
        currentLetter = 'X';
        gameBoard = new Board();
    }

    
}