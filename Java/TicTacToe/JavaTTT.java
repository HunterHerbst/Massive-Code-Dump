import java.util.Scanner;

public class JavaTTT {
    
    //class variables
    static Scanner scan;
    static char[] board;
    static boolean isXTurn, runProgram, gameDone;

    public static void main( String[] args ) {
        //instantiate the scanner at the beginning of the program
        scan = new Scanner( System.in );

        //set the runProgram variable to true, it will be updated at the end of each game
        runProgram = true;

        //main program loop
        while( runProgram ) {
            //set/reset the game variables before starting a new game
            board = new char[9];
            isXTurn = true;
            gameDone = false;

            //main game loop
            while( !gameDone ) {
                gameDone = true;
                printBoard();
            }

            //ask if the user wants to play the game again
            runProgram = askPlayAgain();
        }
        
        //goodbye message
        System.out.println( "\n\tThanks for Playing!\n" );

        //close scanner to free memory
        scan.close();
    }

    public static boolean askPlayAgain() {
        //string to store user input
        String inp;

        //loop until return
        while( true ) {
            //prompt message
            System.out.println( "Would you like to play again?" );
            System.out.print( "Enter (Y)es or (n)o: " );
            
            try {
                //get the user's input
                inp = scan.nextLine().toLowerCase();
                
                //do something depending on the first letter of their input
                switch( inp.charAt(0) ) {
                    case 'y':
                        //return true to signal user wants to play again
                        return true;
                    case 'n':
                        //return false to signal user is done playing
                        return false;
                    default:
                        //print a message to tell the user something went wrong with their input
                        System.out.println( "Sorry, I didn't catch that." );
                        inp = null;
                }
            } catch( Exception e ) {
                //print a message to tell the user something went wrong with their input
                System.out.println( "Sorry, I didn't catch that." );
                inp = null;
            }
        }
    }

    public static void printBoard() {
        /*
            Printed board looks like this:
             7 | 8 | 9
            -----------
             4 | 5 | 6
            -----------
             1 | 2 | 3
        */
        System.out.printf( "%n %c | %c | %c%n", (board[6] == 0) ? '7':board[6], (board[7]==0) ? '8':board[7], (board[8]==0) ? '9':board[8] );
        System.out.println( "-----------" );
        System.out.printf( " %c | %c | %c%n", (board[3]==0) ? '4':board[3], (board[4]==0) ? '5':board[4], (board[5]==0) ? '6':board[5] );
        System.out.println( "-----------" );
        System.out.printf( " %c | %c | %c%n", (board[0]==0) ? '1':board[0], (board[1]==0) ? '2':board[1], (board[2]==0) ? '3':board[2] );
    }

    public static int getMove() {
        //integer used to store player's input choice
        int move;

        //loop until a return
        while( true ) {
            //prompt message
            System.out.println( "Choose a place to move: " );

            try {
                //try parse an int from the console
                move = scan.nextInt();

                //if the int is outside the range, tell the user their input is bad.
                if( !( move > 0 && move < 10 ) ) 
                    System.out.println( "Sorry, you have to enter a number present on the board." );
                //if the space is taken, tell the user their input is bad.
                if( !isOpen( move ) )
                    System.out.println( "Sorry, that space is already taken." );
                //if the input is good and not a filled space, return the number-1
                else
                    return move-1;

            } catch( Exception e ) {
                //tell the user something went wrong with their input
                System.out.println( "Sorry, I didn't catch that." );
            }
        }
    }

    public static boolean boardIsFull() {
        for( int i = 0; i < board.length; i++ )
            if( board[i] == 0 )
                //there is an empty space on the board
                return false;
        //all spaces taken
        return true;
    }

    public static boolean isOpen( int space ) {
        //if the space is 0, it's open
        return board[space-1] == 0;
    }

    //TODO win checker
    //TODO final message print
    //TODO add move to board
    //TODO computer opponent (check game states, generate move, possibly add taunting?, response to taunting, nervousness/mistake meter to allow for screw-ups)
}
