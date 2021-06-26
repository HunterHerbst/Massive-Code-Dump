#include <stdio.h>
#include <stdlib.h>

//#defines
#define KILO 1024
#define MEGA 1024
#define GIGA 1024

int main(int argc, char** argv)
{
    //variable for keeping track of sys time
    time_t t;

    //set the seed for the rand function
    srand((unsigned) time(&t));

    //file pointer for writing to text file
    FILE *outfile = fopen("test.txt", "w");

    int numGigs = 1;

    for(int i = 0; i < numGigs * GIGA; i++)
        for(int j = 0; j < MEGA; j++)
        {
            for(int k = 0; k < KILO; k++)
                fprintf(outfile, "%c", ('a' + rand() % 26));
        }

    //close all files
    fclose(outfile);
    return 0;
}
