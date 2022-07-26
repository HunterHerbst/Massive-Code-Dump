# variables
CXX := g++
GLLINKS := -lglu32 -lglut32 -lopengl32
CCFLAGS := -Wall
OUTFILENAME := raycaster.exe

# make exe
raycaster : raycaster.o
	$(CXX) raycaster.o $(GLLINKS) -o $(OUTFILENAME)

#make object files
raycaster.o : raycaster.cpp
	$(CXX) raycaster.cpp -c $(GLLINKS) $(CCFLAGS)

# file cleanup
.PHONY : clean
clean :
	rm -f raycaster.o core *~