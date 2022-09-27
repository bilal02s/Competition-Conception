CFLAGS=-ansi -Wall -pedantic

test : testCompetition testMatch

testCompetition : test/competition/competition/*.java  
	javac -classpath test4poo.jar test/competition/competition/*.java 
	
testMatch : test/competition/match/*.java
	javac -classpath test4poo.jar test/competition/match/*.java

clean:
	rm -f *.o output input data.bin

.PHONY: all clean

