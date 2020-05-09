# Brainfuck-Compiler-C

*[changelog](https://github.com/DarkwaterKiller/Brainfuck-Compiler-C/blob/master/changelog.md)*

**NOTE: This compiler requires that the user has GCC installed on their system and added to their PATH. If you do not know what that means, or you do not know how to install GCC on your system, please reference [Helpful Information](https://github.com/DarkwaterKiller/Brainfuck-Compiler-C#Helpful-Information) below.**





## How to build - Windows
1. Go to cmd navigate to the directory in which you saved these files
2. Run `gcc compilerwindows.c -o BrainfuckCompile`
3. To compile brainfuck programs, follow the steps in [How to run - Windows](https://github.com/DarkwaterKiller/Brainfuck-Compiler-C#how-to-run---Windows)





## How to build - Linux
*I want to clarify that I have only tested this on Ubuntu. I promise that I will work on getting confirmation this works on other Linux distros.*

1. Open terminal and navigate to the directory in which you saved these files
2. Run `gcc compilerlinux.c -o BrainfuckCompile`
3. To compile brainfuck programs, follow the steps in [How to run - Linux](https://github.com/DarkwaterKiller/Brainfuck-Compiler-C#How-to-run---Linux)





## How to run - Windows
1. Write a brainfuck program, preferrably with the `.bf` file extension
2. If you followed the steps in [How to build - Windows](https://github.com/DarkwaterKiller/Brainfuck-Compiler-C#how-to-build---Windows), run `BrainfuckCompile <path to brainfuck file> <output file name>`
    - If everything was successful, you should notice a new file in the directory labeled as `<output file name>.exe`. This is your compiled brainfuck program.
3. Run `<output file name>`





## How to run - Linux
*See the disclaimer in the Linux build instructions*
1. Write a brainfuck program, preferrably with the `.bf` file extension
2. If you followed the steps in [How to build - Linux](https://github.com/DarkwaterKiller/Brainfuck-Compiler-C#How-to-build---Linux), run `./BrainfuckCompile <path to brainfuck file> <output file name>`
    - If everything was successful, you should notice a new file in the directory labeled as `<output file name>`. This is your compiled brainfuck program.
3. Run `./<output file name>`





## How to keep the generated .c files
This is essentially a universal build method. As the compiling process does not deal with "cleaning up" (deleting) the .c files generated when compiling the brainfuck code.
1. Open cmd (Windows) or terminal (Linux) and navigate to the directory in which you saved these files
2. Run `gcc compilernodelete.c -o BrainfuckCompile`
3. To compile brainfuck programs, follow
    - [These steps for Windows](https://github.com/DarkwaterKiller/Brainfuck-Compiler-C#how-to-run---Windows)
    - [These steps for Linux](https://github.com/DarkwaterKiller/Brainfuck-Compiler-C#how-to-run---Linux)





## Helpful Information

### Installing GCC
#### Windows
I recommend watching and following [this YouTube tutorial](https://youtu.be/8Ib7nwc33uA).

#### Linux
You can probably get away with running the following
1. `sudo apt update`
2. `sudo apt install build-essential`





## Other
I've included [helloworld.bf](https://github.com/DarkwaterKiller/Brainfuck-Compiler-C/blob/master/helloworld.bf) as an example for you to use to test your system. When compiled and run, the hello world program should print out "Hello World!" to the console. (Duh)



### What is Brainfuck?
I have no idea how you got here, but you should probably look at [this](https://en.wikipedia.org/wiki/Brainfuck).