#include <iostream>
#include <math.h>

int convertBitDepth( int, int, int );
int clampToLowerDepth( int, int, int );

int main( int argc, char** argv )
{

    for( int i = 0; i < 256; i++ )
    {
        std::cout << " 8 bit -> 1 bit -> 8 bit:\t" << clampToLowerDepth( i, 8, 1 ) << std::endl;
    }

    return 0; //program done
}//#end of main()

// int convertBitDepth( int value, int oldDepth, int newDepth )
// {
//     return ( (float)value / pow( 2, oldDepth ) ) * pow( 2, newDepth );
// }

int convertBitDepth( int value, int oldDepth, int newDepth )
{
    return ( value * pow( 2, newDepth ) / pow( 2, oldDepth ) );
}

int clampToLowerDepth( int value, int higherDepth, int lowerDepth )
{
    return convertBitDepth( convertBitDepth( value, higherDepth, lowerDepth ), lowerDepth, higherDepth );
}
