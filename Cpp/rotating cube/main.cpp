#include <iostream>
#include <GL/glut.h>
#define _USE_MATH_DEFINES
#include <cmath>
#include <vector>



int main(int argc, char** argv)
{
    std::vector<float> cube[8];

    // make cube in memory with initialized points
    for(int i = 0; i < 8; i++)
    {
        cube[i] = std::vector<float>{(float)(i & 0b001), (float)((i & 0b010) >> 1), (float)((i & 0b100) >> 2)};
    }

    // print out cube points for debug
    for(std::vector<float> v : cube)
    {
        for(float f : v)
            std::cout << f << " ";
        std::cout << std::endl;
    }
}
