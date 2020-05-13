# Conways-Game-of-Life

## Table of Contents

* [Introduction](#introduction)
  * [What is Conway's Game of Life?](<#what is conway's game of life?>)
  * [Why Make This?](<#why make this?>)
* [How to Run](<#how to run>)
  * [1. Run the Included Executable JAR](<#1. run the included executable jar>)
  * [2. Compile and run the .java files](<#2. compile and run the .java files>)
* [How to Use](<#how to use>)
  * [Setting the Cells](<#setting the cells>)
  * [Advancing the Generation](<#advancing the generation>)
    * [The Update Button](<#the update button>)
    * [The Timer](<#the timer>)
* [Example Configurations](<#example configurations>)
  * [Still lifes](<#still lifes>)
    * [Block](#block)
    * [Bee-hive](<#bee-hive>)
    * [Boat](#boat)
    * [Loaf](#loaf)
    * [Tub](#tub)
  * [Oscillators](#oscillators)
    * [Blinker (period 2)](<#blinker (period 2)>)
    * [Toad (period 2)](<#toad (period 2)>)
    * [Beacon (period 2)](<#beacon (period 2)>)
    * [Pulsar (period 3)](<#pulsar (period 3)>)
    * [Penta-decathlon (period 15)](<#penta-decathlon (period 15)>)
  * [Spaceships](#spaceships)
    * [Glider](#glider)
    * [Light-weight Spaceship](<#light-weight spaceship>)
    * [Middle-weight Spaceship](<#middle-weight spaceship>)
    * [Heavy-weight Spaceship](<#heavy-weight spaceship>)

## Introduction

### What is Conway's Game of Life?

From the [Wikipedia page for Conway's Game of Life](https://en.wikipedia.org/wiki/Conway's_Game_of_Life):
> The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970. It is a zero player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves. It is Turing complete and can simulate a universal constructor or any other Turing machine.

If you are unfamiliar with Conway's Game of Life, here's a simple explaination of how it works:

1. There is a grid of cells that can be in one of two states, living or dead.
2. A cell will die if:
    1. There are fewer than 2 adjacent living cells. (underpopulation)
    2. There are greater than 3 adjacent living cells. (overpopulation)
3. A cell will stay alive if there are 2 or 3 adjacent living cells.
4. A new cell will be created in any place with *exactly* 3 adjacent living cells.

### Why Make This?

The reason I did this project was to honor John Conway and his work in my own sort of way. Conway made many contributions to various mathematical theories. I won't get into his entire life story, but if you are interested, you can read the Wikipedia page on Conway [here](https://en.wikipedia.org/wiki/John_Horton_Conway).

## How to Run

After downloading the files from this repository, there are two (kind of three) ways you can run my project.

### 1. Run the Included Executable JAR

Assuming you already have the Java Runtime Environment installed on your machine, all you have to do to run the game is run `Conways Game of Life.jar` from the downloaded files.

### 2. Compile and run the .java files

If you want to compile and run the project yourself, all you need to do is open your operating system's command line, (i.e. Command Prompt or Terminal) navigate to the folder containing the .java files, and run the following commands:

> `javac *.java`<br>
> `java GameOfLifeApp`

Additionally, if you want to make your own executable JAR, simply run the following commands:

> `javac *.java`<br>
> `jar cfe "Conways Game of Life.jar" GameOfLifeApp *.class`

## How to Use

### Setting the Cells

When you first start the program, a window will open that looks similar to this:

![Image of the program when it is first opened](/images/GameOfLifeImage0.png)

The first major thing you'll probably notice is the large area filled with dashes. This is the grid of cells. Each "-" is a dead cell. To change a cell to living, simply replace the "-" to a "#". The below is an example of what an X of living cells would look like.

![Image of an X of living cells](/images/GameOfLifeImage1.png)

**NOTE: Make sure that there is always a *single* space between each cell, the program will crash otherwise.**

### Advancing the Generation

There are two ways you can advance the game to the next generation.

#### The Update Button

If you wish to manually step the program forward one generation at a time, you can do so by pressing the "Update" button, located just below the cell grid. This will move the program forward by a single generation.

#### The Timer

A better way to observe how a starting configuration will evolve over time is to use the timer. There are a few things to note when using the timer:

1. When the timer is running, it can only be stopped by either pressing the "Stop Timer" button, or closing the program
2. When the timer is running, you will be unable to edit and make changes to the cell grid. In order to edit the grid, stop the timer
3. When the timer is running, you cannot change the simulation speed. Stop the timer in order to change the speed.
4. The simulation speed is calculated as follows: `1 second / Time Speed`. This means that setting this to 2 will make the simulation advance 2 times per second, 4 would be 4 times per second, and so on.
5. The timer speed **must** be a whole number. If a decimal or fraction is used, the timer will default to a speed of one cycle per second.

## Example Configurations

The following are just a few example configuations, for more, I suggest visiting the [example configurations](https://en.wikipedia.org/wiki/Conway's_Game_of_Life#Examples_of_patterns) section of the Wikipedia page for Conway's Game of Life. I strongly recommend visiting the Wiki page because there are animated gifs of the following configurations there as well.

### Still lifes

#### Block

```null
# #
# #
```

#### Bee-hive

```null
- # # -
# - - #
- # # -
```

#### Boat

```null
# # -
# - #
- # -
```

#### Loaf

```null
- # # -
# - - #
- # - #
- - # -
```

#### Tub

```null
- # -
# - #
- # -
```

### Oscillators

#### Blinker (period 2)

```null
- # -
- # -
- # -
```

#### Toad (period 2)

```null
- # # #
# # # -
```

#### Beacon (period 2)

```null
# # - -
# # - -
- - # #
- - # #
```

#### Pulsar (period 3)

```null
- - # # # - - - # # # - -
- - - - - - - - - - - - -
# - - - - # - # - - - - #
# - - - - # - # - - - - #
# - - - - # - # - - - - #
- - # # # - - - # # # - -
- - - - - - - - - - - - -
- - # # # - - - # # # - -
# - - - - # - # - - - - #
# - - - - # - # - - - - #
# - - - - # - # - - - - #
- - - - - - - - - - - - -
- - # # # - - - # # # - -
```

#### Penta-decathlon (period 15)

```null
# # #
# - #
# # #
# # #
# # #
# # #
# - #
# # #
```

### Spaceships

#### Glider

```null
- # -
- - #
# # #
```

#### Light-weight Spaceship

```null
- # - - #
# - - - -
# - - - #
# # # # -
```

#### Medium-weight Spaceship

```null
- - - # - -
- # - - - #
# - - - - -
# - - - - #
# # # # # -
```

#### Heavy-weight Spaceship

```null
- - - # # - -
- # - - - - #
# - - - - - -
# - - - - - #
# # # # # # -
```
