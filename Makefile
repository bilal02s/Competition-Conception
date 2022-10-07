CFLAGS=-ansi -Wall -pedantic

exec.jar : manifest 
	jar cvfe exec.jar manifest Main -C classes


src : util competition exception match competitionPackage #main

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

#main : src/Main.java 
#	javac -sourcepath src -d classes src/Main.java

test : testCompetition testMatch

testCompetition : test/competition/competition/*.java  
	javac -classpath test4poo.jar test/competition/competition/*.java 
	
testMatch : test/competition/match/*.java
	javac -classpath test4poo.jar test/competition/match/*.java

clean:
	rm -f *.o output input data.bin

.PHONY: all clean

