#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct listitem
{
    char* name;
    struct listitem *next;
} listitem;

listitem* newListitem( char* );
char* getName();
int getMenuSelection();
int isUniqueName( char*, listitem* );
void addItem( listitem*, listitem* );
void printList( listitem* );
void printMenu();
void addRoutine( listitem* );
void removeRoutine( listitem* );

int main( char** args )
{
    listitem *root = malloc( sizeof( listitem ) );
    int menuSelection, endProgram = 0;
    root->next = NULL;
    while( !endProgram )
    {
        printMenu();
        menuSelection = getMenuSelection();

        switch( menuSelection )
        {
            case 1: 
                addRoutine( root );
                break;
            case 2:
                removeRoutine( root );
                break;
            case 3:
                printList( root );
                break;
            case 4:
                endProgram = 1;
                puts( "\nGoodbye!" );
                break;
            default:
                puts( "That is not a valid seletion!" );
        }
    }
    return 0;
}

listitem* newListitem( char* name )
{
    listitem *newItem = malloc( sizeof( listitem ) );
    newItem->name = name;
    newItem->next = NULL;
    return newItem;
}

char* getName()
{
    char *name = malloc( sizeof( char ) );
    printf( "\n%s", "Enter a name: " );
    scanf( "%s", name );
    return name;
}

int getMenuSelection()
{
    int selection;
    printf( "%s", "\n\tYour selection: " );
    scanf( "%d", &selection );
    return selection;
}

int isUniqueName( char* name, listitem *root )
{
    listitem *current = root->next;
    while( current != NULL )
    {
        if( !strcmp( name, current->name ) )
            return 0;
        current = current->next;
    }
    return 1;
}

void addItem( listitem *newEntry, listitem *root )
{
    listitem* current = root;
    while( current->next != NULL )
        current = current->next;
    current->next = newEntry;
}

void printList( listitem *root )
{
    listitem *current = root->next;
    if( current == NULL )
    {
        puts( "\nThe list is empty." );
        return;
    }
    int i = 1;
    while( current != NULL )
    {
        printf( "\n%d - %s", i, current->name );
        current = current->next;
        i++;
    }
    puts( "" );
}

void printMenu()
{
    puts( "\nPlease select one of the following:" );
    puts( "\t1 - Add a new item to the list" );
    puts( "\t2 - Remove an item from the list" );
    puts( "\t3 - Print the list" );
    puts( "\t4 - Exit program" );
}

void addRoutine( listitem *root )
{
    char *name = getName();
    if( !isUniqueName( name, root ) )
    {
        puts( "\nSorry, entered name must be unique!" );
        puts( "To see all names currently being used, print the list" );
        return;
    }
    addItem( newListitem( name ), root );
}

void removeRoutine( listitem *root )
{
    listitem *current = root;
    char *name = getName();
    if( isUniqueName( name, root ) )
    {
        puts( "\nSorry, it doesn't appear that name is in the list!" );
        puts( "To see all names currently being used, print the list" );
        return;
    }
    while( current->next != NULL && strcmp( name, current->next->name ) )
        current = current->next;
    current->next = current->next->next;
}
