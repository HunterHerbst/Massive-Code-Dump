#include <stdio.h>

//#Prototypes
void printBoard();
int isValid( int );
int isEmpty( int );
int getInput();
int makeMove( int );
int isFull();
int isXWin();
int isOWin();
void printTie();


//#Globals
char board[ 9 ] = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
int playerTurn = 1, done = 0, xWin = 0, oWin = 0;

int main( int argc, char** argv )
{
    
    while( !done )
    {
        //Print out the game board
        printBoard();

        //Get player input and add it to the board
        while( !makeMove( getInput() ) );

        //Update values
        xWin = isXWin();
        oWin = isOWin();
        done = xWin || oWin || isFull();
    }
    if( !( xWin || oWin ) )
    {
        printTie();
    }

    //Wait for the user to push enter to exit
    puts("\n\nPress Enter to exit...");
    while(getchar() != '\n');
    getchar();
    return 0;
}

/**
 * Print out the game board
 */
void printBoard()
{
    puts("");
    printf(" %c | %c | %c\n", board[ 6 ], board[ 7 ], board[ 8 ]);
    puts("-----------");
    printf(" %c | %c | %c\n", board[ 3 ], board[ 4 ], board[ 5 ]);
    puts("-----------");
    printf(" %c | %c | %c\n", board[ 0 ], board[ 1 ], board[ 2 ]);
}

/**
 * Check if a given integer is within the board space. (1 to 9)
 */
int isValid( int space )
{
    return space >= 1 && space <= 9;
}

/**
 * Check if the board space is already taken.
 */
int isEmpty( int space )
{
    return board[ space-1 ] == ' ';
}

/**
 * Gets the user input and returns it.
 */
int getInput()
{
    //Variable used to store input temporarily
    int inp;

    //Put a message stating who's turn it is
    if( playerTurn )
    {
        puts("\nX's move");
    }
    else
    {
        puts("\nO's move");
    }
    
    //Prompt the user
    puts("Enter a number 1 through 9 for where you want to make your move ");

    //Get the input and store it
    scanf("%d", &inp);

    //Return the input value
    return inp;
}

/**
 * Adds the input move to the board if it is legal.
 * If the move is legal, returns true
 */
int makeMove( int space )
{
    if( !isEmpty( space ) )
    {
        puts("That space is already taken!");
        return 0;
    }
    else if( !isValid( space ) )
    {
        puts("That space is not on the board!");
        return 0;
    }
    else
    {
        if( playerTurn )
        {
            board[ --space ] = 'x';
        }
        else
        {
            board[ --space ] = 'o';
        }
        playerTurn = !playerTurn;
        return 1;
    }
}

/**
 * Returns true if the board is full
 */
int isFull()
{
    int i;
    for( i = 0; i < 9; i++ )
    {
        if( board[ i ] == ' ' )
        {
            return 0;
        }
    }
    return 1;
}

/**
 * Returns true if X wins and prints a win message.
 */
int isXWin()
{
    int i;
    //Check diagonals
    if( ( ( board[ 4 ] == board[ 0 ] && board[ 4 ] == board[ 8 ] )
        || ( board[ 4 ] == board[ 2 ] && board[ 4 ] == board[ 6 ] ) )
        && ( board[4] == 'x' ) )
    {
        printBoard();
        puts("X wins!");
        return 1;
    }

    //Check horizontals and verticals
    for( i = 0; i < 3; ++i )
    {
        if( ( board[ i*3 ] == board[ i*3+1 ] && board[ i*3 ] == board[ i*3+2 ] && board[ i*3 ] == 'x' ) 
            || ( board[ i ] == board[ i+3 ] && board[ i ] == board[ i+6 ] && board[ i ] == 'x' ) )
            {
                printBoard();
                puts("X wins!");
                return 1;
            }
    }

    //Win condition not met
    return 0;
}

/**
 * Returns true if O wins and prints a win message.
 */
int isOWin()
{
    int i;
    //Check diagonals
    if( ( ( board[ 4 ] == board[ 0 ] && board[ 4 ] == board[ 8 ] )
        || ( board[ 4 ] == board[ 2 ] && board[ 4 ] == board[ 6 ] ) )
        && ( board[4] == 'o' ) )
    {
        printBoard();
        puts("O wins!");
        return 1;
    }

    //Check horizontals and verticals
    for( i = 0; i < 3; ++i )
    {
        if( ( board[ i*3 ] == board[ i*3+1 ] && board[ i*3 ] == board[ i*3+2 ] && board[ i*3 ] == 'o' ) 
            || ( board[ i ] == board[ i+3 ] && board[ i ] == board[ i+6 ] && board[ i ] == 'o' ) )
            {
                printBoard();
                puts("O wins!");
                return 1;
            }
    }

    //Win condition not met
    return 0;
}

/**
 * If neither side won, then print out the tie message.
 */
void printTie()
{
    printBoard();
    puts("Tie game!");
}
