import java.util.InputMismatchException;
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
        while( runProgram ) {
            game = new Game();
            while( !game.gameDone ) {
                System.out.println( game.gameBoard );
                game.gameBoard.makeMove( game.getMove(), game.currentLetter );
                game.swapLetter();
                game.gameDone = game.checkTie() || game.gameBoard.gameWon();
            }

            if( game.checkTie() ) {
                System.out.println( "Tie Game" );
            }
            else {
                game.swapLetter();
                System.out.println( game.currentLetter + " wins" );
            }

            askPlayAgain();

        }

        //close the scanner
        scan.close();
    }

    public Game() {
        gameDone = false;
        currentLetter = 'X';
        gameBoard = new Board();
    }

    public int getMove() {
        int inp = -1;
        while( inp == -1 ) {
            System.out.print( "Enter your move: " );
            try {
                inp = scan.nextInt();
            } catch( InputMismatchException e ) {
                System.out.println( "Sorry, that is not a valid move. " );
            }
            if( !gameBoard.spaceOpen( inp ) ) {
                System.out.println( "Sorry, that space is already taken." );
                inp = -1;
            }
        }
        return inp;
    }

    public boolean checkTie() {
        return gameBoard.isFull() && !gameBoard.gameWon();
    }

    public void swapLetter() {
        game.currentLetter = (game.currentLetter == 'X' ) ? 'O':'X';
    }

    public static void askPlayAgain() {
        String tmp = null;
        while( tmp == null ) {
            System.out.print( "Would you like to play again? Y/n: ");
            scan.nextLine();
            tmp = scan.nextLine();
            try {
                if( tmp.toLowerCase().charAt(0) == 'y' || tmp.toLowerCase().charAt(0) == 'n' )
                    runProgram = tmp.toLowerCase().charAt(0) == 'y';
                else {
                    System.out.println( "Sorry, I didn't catch that." );
                    tmp = null;
                }
            } catch( Exception e ) {
                System.out.println( "Sorry, I didn't catch that." );
                tmp = null;
            }
        }
    }
}