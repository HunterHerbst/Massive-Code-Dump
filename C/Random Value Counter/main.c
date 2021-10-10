#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX_NUM 100
#define ITERATIONS 2000

int findMax(int* nums)
{
    //set max to the first number, get the size of the array
    int max = nums[0];
    //loop through the list of numbers
    for(int i = 0; i < MAX_NUM; i++)
        if(max < nums[i]) max = nums[i];
    //return the max number
    return max;
}

void printGraph(int* nums)
{
    printf("\t_");
    for(int i = 0; i < findMax(nums); i++)
        printf("_");
    
    for(int i = 0; i < MAX_NUM; i++)
    {
        if(nums[i] > 0)
        {
            printf("\n%-03d\t|", i);
            for(int j = 0; j < nums[i]; j++)
            {
                printf("#");
            }
            printf("\t %d", nums[i]);
        }
    }
}

void printUnused(int* nums)
{
    printf("\nUnused numbers: ");
    for(int i = 0; i < MAX_NUM; i++)
        if(!nums[i]) printf("%d, ", i);
}

int main(char** argv, int argc)
{
    //seed the rand
    srand(time(0));

    //pointer to store 100 ints
    int *totalOcc = calloc(sizeof(int), MAX_NUM);

    //fill random numbers
    for(int i = 0; i < ITERATIONS; i++) totalOcc[rand() % MAX_NUM]++;

    //print all values that occurred at least once
    // for(int i = 0; i < MAX_NUM; i++) 
    //     if(totalOcc[i] > 0)
    //         printf("%-03d:\t%-03d\n", i, totalOcc[i]);

    //print the graph of all values
    printGraph(totalOcc);

    //print out the unused values
    printUnused(totalOcc);

    //free memory and exit
    free(totalOcc);
    exit(0);
}
