#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX_NUM 100
#define ITERATIONS 200

int findMax(int* nums)
{
    //set max to the first number
    int max = nums[0];
    //loop through the list of numbers
    for(int i = 0; i < (sizeof(nums) / sizeof(nums[0])); i++)
        if(max < nums[i]) max = nums[i];
    //return the max number
    return max;
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
    for(int i = 0; i < MAX_NUM; i++) 
        if(totalOcc[i] > 0)
            printf("%-03d:\t%-03d\n", i, totalOcc[i]);

    //free memory and exit
    free(totalOcc);
    exit(0);
}
