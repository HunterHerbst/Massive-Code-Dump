#include <iostream>
#include <map>
#include <cmath>
#include <GL/glut.h>

#define WINDOW_WIDTH 1024
#define WINDOW_HEIGHT 512

//keystate maps
std::map<unsigned char, bool> state;
std::map<unsigned char, bool> prevState;

//player postion and orientation
float px, py, pdx, pdy, pa;

//map dimensions and cube size
const int mapX = 8,
    mapY = 8,
    mapS = 64;
const int gamemap[] =
{
    1, 1, 1, 1, 1, 1, 1, 1,
    1, 0, 1, 0, 0, 0, 0, 1,
    1, 0, 1, 0, 0, 0, 0, 1,
    1, 0, 1, 0, 0, 0, 0, 1,
    1, 0, 0, 0, 0, 0, 0, 1,
    1, 0, 0, 0, 0, 1, 0, 1,
    1, 0, 0, 0, 0, 0, 0, 1,
    1, 1, 1, 1, 1, 1, 1, 1
};
    
void drawMap2D()
{
    int x, y, xo, yo;
    for(y=0; y < mapY; y++)
    {
        for(x=0; x < mapX; x++)
        {
            if(gamemap[y*mapX+x] == 1)
            {
                glColor3f(1,1,1);   
            }
            else
            {
                glColor3f(0,0,0);
            }
            xo = x*mapS;
            yo = y*mapS;
            // draw quad in COUNTER CLOCKWISE direction
            glBegin(GL_QUADS);
            glVertex2i(xo     +1, yo     +1);
            glVertex2i(xo     +1, yo+mapS-1);
            glVertex2i(xo+mapS-1, yo+mapS-1);
            glVertex2i(xo+mapS-1, yo     +1);
            glEnd();
        }
    }
}

void drawPlayer()
{
    glColor3f(1,1,0);
    glPointSize(8);
    glBegin(GL_POINTS);
    glVertex2i(px, py);
    glEnd();
}

void playerController()
{
    // if(state['w']) py-=5;
    // if(state['s']) py+=5;
    // if(state['a']) px-=5;
    // if(state['d']) px+=5;

    if(state['w'])
    {
        px += pdx;
        py += pdy;
    }
    if(state['s'])
    {
        px -= pdx;
        py -= pdy;
    }
    if(state['a'])
    {
        pa-=0.1;
        if(pa < 0)
        {
            pa += 2*M_PI;
        }
        pdx = cos(pa*5);
        pdy = sin(pa*5);
    }
    if(state['d'])
    {
        pa+=0.1;
        if(pa > 2*M_PI)
        {
            pa -= 2*M_PI;
        }
        pdx = cos(pa*5);
        pdy = sin(pa*5);
    }

    //print only on spacebar press, not on hold or release
    if(state[' '] && !prevState[' ']) std::cout << "hey" << std::endl;
}

void display()
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    drawMap2D();
    drawPlayer();
    glutSwapBuffers();
}

void processInputsDown(unsigned char key, int x, int y)
{
    // Legacy code from tutorial, this isn't instant
    // if(key == 'w') { py-=5; }
    // if(key == 's') { py+=5; }
    // if(key == 'a') { px-=5; }
    // if(key == 'd') { px+=5; }
    //escape key
    // if(key == 27 )
    // {
    // }
    // glutPostRedisplay();

    state[key] = true;
}

void processInputsUp(unsigned char key, int x, int y)
{
    state[key] = false;
}

//timer for posting Redisplays
void update(int extra)
{
    playerController(); //update player based on inputs
    glutPostRedisplay();
    prevState = state;  //after everything, set previous state to current state
    glutTimerFunc(16, update, 0);
}

void processSpecialInputs(unsigned int key, int x, int y)
{

}

void init()
{
    glClearColor(0.3, 0.3, 0.3, 0);                 //set background color
    gluOrtho2D(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0);
    px = 300; py = 300;      //init player pos
}

int main(int argc, char** argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);        //display mode
    glutInitWindowSize(WINDOW_WIDTH, WINDOW_HEIGHT);                      //set window size
    glutCreateWindow("Raycaster - DarkwaterKiller");    //make window with title
    init();                                             //run init func
    glutDisplayFunc(display);                           //set display func to display();
    glutKeyboardFunc(processInputsDown);                    //set input processor func to processInputsDown();
    glutKeyboardUpFunc(processInputsUp);                //set input release processor func to precessInputsUp();
    glutTimerFunc(0, update, 0);                         //begin update function
    glutIgnoreKeyRepeat(GL_TRUE);                       //ignore repeated key presses (for when key is held for movement)
    glutMainLoop();                                     //begin main loop
}