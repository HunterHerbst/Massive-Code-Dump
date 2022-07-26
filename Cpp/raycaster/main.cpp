#include <iostream>
#include <map>
#include <GL/glut.h>

#define WINDOW_WIDTH 1024
#define WINDOW_HEIGHT 512

std::map<unsigned char, bool> state;
std::map<unsigned char, bool> prevState;

float px, py; //player position

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
    if(state['w']) py-=5;
    if(state['s']) py+=5;
    if(state['a']) px-=5;
    if(state['d']) px+=5;

    //print only on spacebar press, not on hold or release
    if(state[' '] && !prevState[' ']) std::cout << "hey" << std::endl;
}

void display()
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
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
    px = WINDOW_WIDTH/2; py = WINDOW_HEIGHT/2;      //init player pos
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