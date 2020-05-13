public class Board {
    
    private char[] board;

    public Board() {
        board = new char[9];
    }

    public boolean spaceOpen( int space ) {
        return board[ space-1 ] == 0;
    }

    public boolean isFull() {
        for( int i = 0; i < board.length; i++ )
            if( board[i] == 0 )
                return false;
        return true;
    }

    public boolean makeMove( int move, char letter ) {
        if( move < 1 || move > 9 ) {
            System.out.println( "Sorry, that space is not on the board." );
            return false;
        }
        else if( !spaceOpen( move ) ) {
            System.out.println( "Sorry, that space is already taken." );
            return false;
        }
        else {
            board[ move-1 ] = letter;
            return true;
        }
    }

    public boolean gameWon() {
        for( int i = 0; i < 3; i++ ) {
            //horizontal
            if( board[i*3] == board[i*3+1] && board[i*3] == board[i*3+2] && board[i*3] != 0 ) {
                return true;
            }
            //vertical
            if( board[i] == board[i+3] && board[i] == board[i+6] && board[i] != 0 ) {
                return true;
            }
        }
        //forward diagonal
        if( board[0] == board[4] && board[0] == board[8] && board[0] != 0 ) {
            return true;
        }
        //backward diagonal
        if( board[2] == board[4] && board[2] == board[6] && board[2] != 0 ) {
            return true;
        }

        //no win
        return false;
    }

    @Override
    public String toString() {
        return String.format( "%n\t %c | %c | %c%n\t-----------%n\t %c | %c | %c%n\t-----------%n\t %c | %c | %c%n",
            ( board[6] == 0 ) ? '7':board[6],
            ( board[7] == 0 ) ? '8':board[7],
            ( board[8] == 0 ) ? '9':board[8],
            ( board[3] == 0 ) ? '4':board[3],
            ( board[4] == 0 ) ? '5':board[4],
            ( board[5] == 0 ) ? '6':board[5],
            ( board[0] == 0 ) ? '1':board[0],
            ( board[1] == 0 ) ? '2':board[1],
            ( board[2] == 0 ) ? '3':board[2] );
    }
}