# variables
CXX := gcc
CCFLAGS := -Wall
OUTFILENAME := "Not A Fork Bomb"

# make exe
notaforkbomb : notaforkbomb.o
	$(CXX) notaforkbomb.o -o $(OUTFILENAME)

#make object files
notaforkbomb.o : notaforkbomb.c
	$(CXX) notaforkbomb.c -c $(CCFLAGS)

# file cleanup
.PHONY : clean
clean :
	rm -f notaforkbomb.o core *~