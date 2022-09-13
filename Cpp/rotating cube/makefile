# variables
CXX := g++
GLLINKS := -lglu32 -lglut32 -lopengl32
CCFLAGS := -Wall

OUTFILENAME := cube.exe

# make exe
main : main.o
	$(CXX) main.o $(GLLINKS) -o $(OUTFILENAME)

#make object files
main.o : main.cpp
	$(CXX) main.cpp -c $(GLLINKS) $(CCFLAGS)

# file cleanup
.PHONY : clean
clean :
	rm -f main.o core *~