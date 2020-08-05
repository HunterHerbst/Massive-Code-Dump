#include <stdio.h>

int main( void )
{
    int i, iterations;
    float product;
    puts("Enter the amount of terms to use: ");
    scanf("%d", &iterations);

    i = 0;
    while( i < iterations )
    {
        if( i == 0 )
            product = 2;
        else
            product *= (float)( 4 * i * i ) / (float)( (2 * i - 1) * ( 2 * i + 1 ) );
        i++;
    }

    printf("Pi with %d terms is: %f", i, product);

    return 0;
}