#include <stdio.h>
#include <stdlib.h>

typedef struct piece
{
    //white is 0, black is 1
    int color;

    //pawn 1, knight 2, bishop 3, rook 4, queen 5, king 6
    int value;
} piece;

typedef struct board
{
    piece ***pieces;
} board;

void placePieces(board *game)
{
    
}

/**
 * Create a new chess board
*/
board* createBoard()
{
    //allocate space for the board
    board* newBoard = malloc(sizeof(board));
    //allocate space for the columns
    newBoard->pieces = malloc(8 * sizeof(piece**));
    for(int i = 0; i < 8; i++)
    {
        //allocate space for the rows
        newBoard->pieces[i] = malloc(8 * sizeof(piece*));
        for(int j = 0; j < 8; j++)
        {
            //allocate each space's pointer
            newBoard->pieces[i][j] = malloc(sizeof(piece));
            //set the new spaces to NULL, meaning nothing is here
            newBoard->pieces[i][j] = NULL;
        }
    }
    //spit out the new chess board
    return newBoard;
}

/**
 * Free all memory being used by the game board
*/
void freeBoard(board *game)
{
    for(int i = 0; i < 8; i++)
    {
        for(int j = 0; j < 8; j++)
        {
            //free each space
            free(game->pieces[i][j]);
        }
        //free the rows
        free(game->pieces[i]);
    }
    //free the columns
    free(game->pieces);
    //free the whole board
    free(game);
}
