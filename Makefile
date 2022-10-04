CFLAGS=-ansi -Wall -pedantic
src : competition exception match competitionPackage util

competition : src/competition/*.java 
	javac -sourcepath src -d classes src/competition/*.java 

exception : src/competition/exception/*.java 
	javac -sourcepath src -d classes src/competition/exception/*.java

match : src/competition/match/*.java 
	javac -sourcepath src -d classes src/competition/match/*.java 

competitionPackage : src/competition/competition/*.java 
	javac -sourcepath src -d classes src/competition/competition/*.java 

util : src/util/*.java
	javac -sourcepath src -d classes src/util/*.java

test : testCompetition testMatch

testCompetition : test/competition/competition/*.java  
	javac -classpath test4poo.jar test/competition/competition/*.java 
	
testMatch : test/competition/match/*.java
	javac -classpath test4poo.jar test/competition/match/*.java

clean:
	rm -f *.o output input data.bin

.PHONY: all clean

