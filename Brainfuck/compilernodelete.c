#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void build( char*, FILE* );

FILE *input, *output;
char *buffer, *bfcode = "", *cfilename, *compilercommand;
long numbytes;

int main( int argc, char** argv )
{
    compilercommand = malloc( sizeof( char ) );
    cfilename = malloc( sizeof( char ) );

    //check that there are enough parameters at runtime
    if( argc < 3 )
    {
        puts( "Insufficient number of arguments." );
        puts( "Please check that you have run the program correctly." );
        puts( "BrainCompile <brainfuck file> <output name>" );
    }

    //open the input file
    input  = fopen( argv[1], "r" );

    //make sure the input file isn't empty
    if( input == NULL )
    {
        puts( "The input file is blank." );
        puts( "Check that you spelled the name correctly." );
        return 1;
    }

    //get the number of bytes
    fseek( input, 0L, SEEK_END );
    numbytes = ftell( input );

    //reset the file position indicator to
    //the beginning of the file
    fseek( input, 0L, SEEK_SET );

    //grab sufficient memory for the
    //buffer to hold the text
    buffer = ( char* ) calloc( numbytes, sizeof( char ) );

    //memory error
    if( buffer == NULL )
    {
        puts( "Error with input buffer allocation" );
        return 1;
    }

    //copy all text into the buffer
    fread( buffer, sizeof( char ), numbytes, input );
    
    //close the input file
    fclose( input );

    //confirm everything was read by printing
    //printf( "The input buffer contains the following:\n%s", buffer );


    //make the output file
    strcpy( cfilename, argv[2] );
    strcat( cfilename, ".c" );
    output = fopen( cfilename, "w+" );


    //!this is used to build the c file
    build( buffer, output );


    //close the output file
    fclose( output );

    //!this is the auto compiler
    strcpy( compilercommand, "gcc " );
    strcat( compilercommand, cfilename );
    strcat( compilercommand, " -o " );
    strcat( compilercommand, argv[2] );
    system( compilercommand );

    //free the memory allocated to the buffer
    free( buffer );

    return 0;
}

void build( char *code, FILE *out )
{
    int i, j, indentCount = 1;

    fputs( "#include <stdio.h>\nint main()\n{\n", out );
    fputs( "\tchar array[30000] = {0};\n\tchar *ptr=array;\n", out );

    for( i = 0; i < numbytes; i++ )
    {
        //check if a character is within the specific operators
        if( *code == '<' || *code == '>' || *code == '+' || *code == '-' ||
            *code == '.' || *code == ',' || *code == '[' || *code == ']' )
        {
            //#fix the tabbing for the end of a while-loop
            if( *code == ']' )
                indentCount--;

            //#indent the correct number of times
            for( j = 0; j < indentCount; j++ )
                fputs( "\t", out );

            //#pointer increment operator
            if( *code == '>' )
                fputs( "++ptr;\n", out );

            //#pointer decrement operator
            if( *code == '<' )
                fputs( "--ptr;\n", out );

            //#increment cell operator
            if( *code == '+' )
                fputs( "++*ptr;\n", out );

            //#decrement cell operator
            if( *code == '-' )
                fputs( "--*ptr;\n", out );

            //#print cell operator
            if( *code == '.' )
                fputs( "putchar( *ptr );\n", out );

            //#input to cell operator
            if( *code == ',' )
                fputs( "*ptr = getchar();\n", out );

            //#open while-loop operator
            if( *code == '[' )
            {
                fputs( "while( *ptr )\n", out );

                //do the indents because new line
                for( j = 0; j < indentCount; j++ )
                    fputs( "\t", out );

                fputs( "{\n", out );

                //update the indent count
                indentCount++;
            }

            //#close while-loop operator
            if( *code == ']' )
                fputs( "}\n", out );
        }
        
        
        ++code;
    }

    fputs( "}\n//All done, get brainfucked\n", out );
}