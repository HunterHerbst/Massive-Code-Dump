public class Conway {

    public final int rows, columns;
    private int[][] board;
    private int[][] scores;
    private boolean wrapping;

    public Conway() {
        board = new int[10][10];
        scores = new int[10][10];
        rows = 10;
        columns = 10;
        wrapping = false;
    }

    public Conway( final int rows, final int columns ) {
        board = new int[ rows ][ columns ];
        scores = new int[ rows ][ columns ];
        this.rows = rows;
        this.columns = columns;
        wrapping = false;
    }

    public Conway( final int rows, final int columns, final boolean wrapping ) {
        this( rows, columns );
        this.wrapping = wrapping;
    }

    public final void update() {
        this.updateScores();
        this.updateBoard();
    }

    public final int checkCellScore( final int x, final int y ) {
        int neighborCount = 0;
        if( x > 0 ) {
            if( y > 0 )
                neighborCount += board[x - 1][y - 1];
            else if( wrapping )
                neighborCount += board[x - 1][columns - 1];
            if( y < columns - 1 )
                neighborCount += board[x - 1][y + 1];
            else if( wrapping )
                neighborCount += board[x - 1][0];
            neighborCount += board[x - 1][y];
        }
        else if( wrapping ) {
            if( y > 0 )
                neighborCount += board[rows - 1][y - 1];
            else if( wrapping )
                neighborCount += board[rows - 1][columns - 1];
            if( y < columns - 1 )
                neighborCount += board[rows - 1][y + 1];
            else if( wrapping )
                neighborCount += board[rows - 1][0];
            neighborCount += board[rows - 1][y];
        }
        if( x < rows - 1 ) {
            if( y > 0 )
                neighborCount += board[x + 1][y - 1];
            else if( wrapping )
                neighborCount += board[x + 1][columns - 1];
            if( y < columns - 1 )
                neighborCount += board[x + 1][y + 1];
            else if( wrapping )
                neighborCount += board[x + 1][0];
            neighborCount += board[x + 1][y];
        }
        else if( wrapping ) {
            if( y > 0 )
                neighborCount += board[0][y - 1];
            else if( wrapping )
                neighborCount += board[0][columns - 1];
            if( y < columns - 1 )
                neighborCount += board[0][y + 1];
            else if( wrapping )
                neighborCount += board[0][0];
            neighborCount += board[0][y];
        }
        if( y > 0 )
            neighborCount += board[x][y - 1];
        else if( wrapping )
            neighborCount += board[x][columns - 1];
        if( y < columns - 1 )
            neighborCount += board[x][y + 1];
        else if( wrapping )
            neighborCount += board[x][0];
        return neighborCount;
    }

    public final void print() {
        for( int i = 0; i < rows; i++ ) {
            for( int j = 0; j < columns; j++ )
                System.out.print( ( (board[i][j] == 0) ? '-':'#' ) + " " );
            System.out.println();
        }
    }

    public final void printScores() {
        for( int i = 0; i < rows; i++ ) {
            for( int j = 0; j < columns; j++ )
                System.out.print( scores[i][j] + " " );
            System.out.println();
        }
    }

    public final void updateScores() {
        for( int i = 0; i < rows; i++ )
            for( int j = 0; j < columns; j++ )
                scores[i][j] = checkCellScore( i, j );
    }

    public final void toggleCell( final int x, final int y ) {
        board[x][y] = ( board[x][y] == 0 ) ? 1:0;
    }

    public final void updateBoard() {
        for( int i = 0; i < rows; i++ )
            for( int j = 0; j < columns; j++ )
                if( (board[i][j] == 1 && ( scores[i][j] != 2 && scores[i][j] != 3 )) || (board[i][j] == 0 && scores[i][j] == 3) )
                    this.toggleCell( i, j );
    }

    public final void setBoardWithString( final String boardString, final char liveCellChar ) {
        String[] separateColumns = boardString.split( "\n" );
        for( int i = 0; i < rows; i++ ) {
            char[] currentColumn = separateColumns[i].toCharArray();
            for( int j = 0; j < columns; j++ ) {
                board[i][j] = currentColumn[j*2] == liveCellChar ? 1:0;
            }
        }
    }

    public final boolean isWrappingEnabled() { return wrapping; }
    public final void enableWrapping() { wrapping = true; }
    public final void disableWrapping() { wrapping = false; }

    @Override
    public final String toString() {
        return toString( '#', '-' );
    }

    public final String toString( final char liveCellChar, final char deadCellChar ) {
        String tmp = "";
        for( int i = 0; i < rows; i++ ) {
            for( int j = 0; j < columns; j++ )
                tmp += (board[i][j] == 1 ? liveCellChar:deadCellChar) + " ";
            tmp += "\n";
        }
        return tmp;
    }

}
