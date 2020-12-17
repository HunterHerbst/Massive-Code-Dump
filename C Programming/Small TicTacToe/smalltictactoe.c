#include <stdio.h>

int b[] = {[0 ... 8]=32};
int t = 1;

int getInp(){
    int inp;
    printf("\n%c's move\nEnter a number 1 through 9 for where you want to make your move\n", t?88:79);
    scanf("%d", &inp);
    return inp;
}

int move(int m){
    if( m < 1 || m > 9 || b[--m] != 32 ){
        puts("You cannot make that move!");
        return 1;
    }
    b[m] = t?88:79;
    t = !t;
    return 0;
}

int noFull(){
    int i;
    for( i = 0; i < 9; ++i )
        if( b[i] == 32 )
            return 1;
    return 0;
}

int noWin(){
    int i;
    if( ( ( b[4] == b[0] && b[4] == b[8] ) || ( b[4] == b[2] && b[4] == b[6] ) ) && b[4] != 32 )
        return 0;
    for( i = 0; i < 3; ++i )
        if( ( b[i*3] == b[i*3+1] && b[i*3] == b[i*3+2] && b[i*3] != 32 ) || ( b[i] == b[i+3] && b[i] == b[i+6] && b[i] != 32 ) )
            return 0;
    return 1;
}

int main(void){
    puts("   |   |\n-----------\n   |   |\n-----------\n   |   |");
    while(noFull() && noWin()){
        while(move(getInp()));
        printf("\n %c | %c | %c\n-----------\n %c | %c | %c\n-----------\n %c | %c | %c\n", b[6], b[7], b[8], b[3], b[4], b[5], b[0], b[1], b[2]);
    }
    if(!noWin())
        printf("%c wins!\n", t?79:88);
    else
        puts("Tie game!");
    puts("\nPress Enter to exit");
    while(getchar()!=10);
    getchar();
    return 0;
}
