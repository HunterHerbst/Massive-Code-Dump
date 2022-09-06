#include <iostream>
#include <map>
#include <string>
#include <cmath>
#include <GL/glut.h>

//# constants used in program

// window consts
constexpr int WINDOW_WIDTH = 1024, 
    WINDOW_HEIGHT = 512;

// player consts
constexpr int PLAYER_SIDE_LENGTH_MULTIPLIER = 16, 
    PLAYER_MOVE_SPEED_MULT = 2;
constexpr float PLAYER_BACK_VERTEX_ANGLE_OFFSET = 0.5235987902, 
    PLAYER_TURN_RATE = 0.05;

// ray consts
constexpr float FOV = 90.0f,
    DEGRAD = 0.01745329251; // one degree in radians

// map consts
constexpr int mapX = 8,
    mapY = 8,
    mapS = 64; // map square size (side length, not area)
constexpr int gamemap[] =
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


//TODO CLEAR OUT NON CONSTANT GLOBAL VARIABLES!! MOST OF THESE CAN BE CHUCKED INTO A PLAYER STRUCT AND THEN A PLAYER CAN BE MADE IN MAIN TO BE PASSED THROUGH FUNCTIONS (UNLESS GLUT GETS ANGY I GUESS??? IDK YET)
//! ^^^^^^ GLUT GETS ANGY WHEN I ADD PARAMETERS TO CERTAIN FUNCTIONS, SO AT LEAST MAKE A GLOBAL PLAYER VARIABLE TO CLEAN UP RANDOM VARIABLE NAMES
//keystate maps
std::map<unsigned char, bool> state;
std::map<unsigned char, bool> prevState;

//player postion and orientation
float px, py, pdx, pdy, pa;

float roundoff(float value, unsigned int precision)
{
    float pow_10 = pow(10.0f, (float)precision);
    return round(value * pow_10) / pow_10;
}

float dist(float ax, float ay, float bx, float by, float angle)
{
    return (sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay)));
}

//# drawing

void drawRays3D()
{
    // ray number, map pos (x, y, exact square), depth of search
    int r, mx, my, mp, dof;

    // ray position, angle, and the x/y offsets, final distance of ray
    float rx, ry, ra, xo, yo, dt;

    // start ray 1/2 FOV to the left of the player
    ra = pa - DEGRAD * (FOV/2);
    if(ra < 0)
        ra += 2*M_PI;
    if(ra > 2*M_PI)
        ra -= 2*M_PI;

    // cast FOV number of rays
    for(r = 0; r < FOV; r++)
    {
        //--- check horizontal lines ---
        dof = 0;
        // horizontal line length, and xy pos (init to massive value and player pos), and arctan
        float dh = INFINITY,
            hx = px,
            hy = py,
            arctanRay = -1 / std::tan(ra);
        // ray is horizontal
        if( ra == 0 || ra == M_PI)
        {
            ry = py;
            rx = px;
            dof = 8;
        }
        // ray looking up
        else if(ra > M_PI)
        {
            ry = (((int) py >> 6) << 6) - 0.0001; // round y value to nearest 64th value //!CHANGE THIS TO BE DYNAMIC FOR SIZES OTHER THAN 64 IN THE FUTURE
            rx = (py - ry) * arctanRay + px;
            yo = -64;
            xo = -yo * arctanRay;
        }
        // ray looking down
        else if(ra < M_PI)
        {
            ry = (((int) py >> 6) << 6) + 64; // round y value to nearest 64th value //!CHANGE THIS TO BE DYNAMIC FOR SIZES OTHER THAN 64 IN THE FUTURE
            rx = (py - ry) * arctanRay + px;
            yo = 64;
            xo = -yo * arctanRay;
        }
        // perform search on ray with max depth of 8 //!CHANGE THIS TO BE DYNAMIC WTIH MAPS LARGER THAN 8x8 IN THE FUTURE
        while(dof < 8)
        {
            mx = (int)(rx) >> 6;
            my = (int)(ry) >> 6;
            mp = my * mapX + mx;

            // hit a wall
            if(mp < mapX * mapY && mp > -1 && gamemap[mp] == 1)
            {
                hx = rx;
                hy = ry;
                dh = dist(px, py, hx, hy, ra);
                dof = 8;
            }
            // missed wall, so check next square using x/y offsets
            else
            {
                rx += xo;
                ry += yo;
                dof+=1;
            }
        }

        //--- check vertical lines ---
        dof = 0;
        // vertical line length, and xy pos (init to massive value and player pos), and negative tan
        float dv = INFINITY,
            vx = px,
            vy = py,
            ntanRay = -std::tan(ra);
        // ray is vertical
        if( ra == M_PI_2 || ra == 3 * M_PI_2)
        {
            ry = py;
            rx = px;
            dof = 8;
        }
        // ray looking left
        else if(ra > M_PI_2 && ra < 3 * M_PI_2)
        {
            rx = (((int) px >> 6) << 6) - 0.0001; // round y value to nearest 64th value //!CHANGE THIS TO BE DYNAMIC FOR SIZES OTHER THAN 64 IN THE FUTURE
            ry = (px - rx) * ntanRay + py;
            xo = -64;
            yo = -xo * ntanRay;
        }
        // ray looking right
        else if(ra < M_PI_2 || ra > 3 * M_PI_2)
        {
            rx = (((int) px >> 6) << 6) + 64; // round y value to nearest 64th value //!CHANGE THIS TO BE DYNAMIC FOR SIZES OTHER THAN 64 IN THE FUTURE
            ry = (px - rx) * ntanRay + py;
            xo = 64;
            yo = -xo * ntanRay;
        }
        // perform search on ray with max depth of 8 //!CHANGE THIS TO BE DYNAMIC WTIH MAPS LARGER THAN 8x8 IN THE FUTURE
        while(dof < 8)
        {
            mx = (int)(rx) >> 6;
            my = (int)(ry) >> 6;
            mp = my * mapX + mx;
            // hit a wall
            if(mp < mapX * mapY && mp > -1 && gamemap[mp] == 1)
            {
                vx = rx;
                vy = ry;
                dv = dist(px, py, vx, vy, ra);
                dof = 8;
            }
            // missed wall, so check next square using x/y offsets
            else
            {
                rx += xo;
                ry += yo;
                dof+=1;
            }
        }

        // set ray length to shortest length
        rx = (dv < dh) ? vx : hx;
        ry = (dv < dh) ? vy : hy;
        dt = std::min(dv, dh);

        // draw ray for funsies
        glColor3f(1, 0, 0);
        glLineWidth(1);
        glBegin(GL_LINES);
        glVertex2i(px, py);
        glVertex2i(rx, ry);
        glEnd();

        //--- Draw 3D Walls ---

        // compensate for warping
        float ca = pa - ra;
        if(ca < 0)
            ca += 2*M_PI;
        if(ca > 2*M_PI)
            ca -= 2*M_PI;
        dt *= std::cos(ca);

        // line height
        float lineH = (mapS * 320) / dt;
        lineH = std::min(lineH, 320.0f);

        // line offset
        float lineO = 160 - lineH / 2;

        // draw wall
        // change wall color depending on vertical or horizontal line being hit
        if(dv < dh)
            glColor3f(0, 1, 0.6118);
        else
            glColor3f(0.5, 1, 0.6118);
        glLineWidth(8);
        glBegin(GL_LINES);
        glVertex2i(r * 8 + 530, lineO);
        glVertex2i(r * 8 + 530, lineH + lineO);
        glEnd();

        // increase ray angle by one degree
        ra += DEGRAD;
        if(ra < 0)
            ra += 2*M_PI;
        if(ra > 2*M_PI)
            ra -= 2*M_PI;
    }
}

// draw overhead map view
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

// draw player on 2D view
void drawPlayer()
{
    glColor3f(1,1,0);
    //define player triangle in counter clockwise direction
    glBegin(GL_TRIANGLES);
    glVertex2i(px, py);
    glVertex2i(px - PLAYER_SIDE_LENGTH_MULTIPLIER * std::cos(pa + PLAYER_BACK_VERTEX_ANGLE_OFFSET), py - PLAYER_SIDE_LENGTH_MULTIPLIER * std::sin(pa + PLAYER_BACK_VERTEX_ANGLE_OFFSET));
    glVertex2i(px - PLAYER_SIDE_LENGTH_MULTIPLIER * std::cos(pa - PLAYER_BACK_VERTEX_ANGLE_OFFSET), py - PLAYER_SIDE_LENGTH_MULTIPLIER * std::sin(pa - PLAYER_BACK_VERTEX_ANGLE_OFFSET));
    glEnd();
}

// print information about current player state
void printInfo()
{
    std::cout << "Current player angle deg: " << pa * 180 / M_PI << std::endl;
    std::cout << "Current player angle rad: " << pa << std::endl;
}

// player character movement controller
void playerController()
{
    if(state['w'] || state['W'])
    {
        px += pdx * PLAYER_MOVE_SPEED_MULT;
        py += pdy * PLAYER_MOVE_SPEED_MULT;
    }
    if(state['s'] || state['S'])
    {
        px -= pdx * PLAYER_MOVE_SPEED_MULT;
        py -= pdy * PLAYER_MOVE_SPEED_MULT;
    }
    if(state['a'] || state['A'])
    {
        pa -= PLAYER_TURN_RATE;
        if(pa < 0)
        {
            pa += 2*M_PI;
        }
        pdx = std::cos(pa);
        pdy = std::sin(pa);
    }
    if(state['d'] || state['D'])
    {
        pa += PLAYER_TURN_RATE;
        if(pa > 2*M_PI)
        {
            pa -= 2*M_PI;
        }
        pdx = std::cos(pa);
        pdy = std::sin(pa);
    }
    // strafing //TODO this doesn't work right lol
    if(state['q'] || state['Q'])
    {
        px -= std::cos(pa + M_PI_2) * PLAYER_MOVE_SPEED_MULT;
        py -= std::sin(pa + M_PI_2) * PLAYER_MOVE_SPEED_MULT;
    }
    if(state['e'] || state['E'])
    {
        px += std::cos(pa + M_PI_2) * PLAYER_MOVE_SPEED_MULT;
        py += std::sin(pa + M_PI_2) * PLAYER_MOVE_SPEED_MULT;
    }

    //print only on spacebar press, not on hold or release
    if(state[' '] && !prevState[' '])
        printInfo();
}

// display func (call drawing functions from above)
void display()
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    drawMap2D();
    drawPlayer();
    drawRays3D();
    glutSwapBuffers();
}

//# input processing

// key press
void processInputsDown(unsigned char key, int x, int y)
{
    state[key] = true;
}

// key release
void processInputsUp(unsigned char key, int x, int y)
{
    state[key] = false;
}

// special keys (like f1-f12, arrows, etc.)
void processSpecialInputs(unsigned int key, int x, int y)
{

}

//# helpers

// timer for posting redisplays
void update(int extra)
{
    playerController(); //update player based on inputs
    glutPostRedisplay();
    prevState = state;  //after everything, set previous state to current state
    glutTimerFunc(16, update, 0);
}

// variable initialization
void init()
{
    glClearColor(0.3, 0.3, 0.3, 0);                 //set background color
    gluOrtho2D(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0);
    px = 300; py = 300; pa = 0;     //init player pos
    pdx = std::cos(pa); pdy = std::sin(pa);   //init player rotation
}

// main program function
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