CFLAGS=-ansi -Wall -pedantic

exec.jar : manifest 
	jar cvfe exec.jar manifest Main -C classes


src : util competition exception match event #main

competition : src/competition/*.java 
	javac -sourcepath src -d classes src/competition/*.java 

exception : src/competition/exception/*.java 
	javac -sourcepath src -d classes src/competition/exception/*.java

match : src/competition/match/*.java 
	javac -sourcepath src -d classes src/competition/match/*.java 

event : src/competition/event/*.java 
	javac -sourcepath src -d classes src/competition/event/*.java 

util : src/util/*.java
	javac -sourcepath src -d classes src/util/*.java

#main : src/Main.java 
#	javac -sourcepath src -d classes src/Main.java

test : testCompetition testEvent testMatch

testCompetition : test/competition/*.java 
	javac -classpath test4poo.jar test/competition/*.java

testEvent : test/competition/event/*.java  
	javac -classpath test4poo.jar test/competition/event/*.java 
	
testMatch : test/competition/match/*.java
	javac -classpath test4poo.jar test/competition/match/*.java

# javadoc -d docs -subpackages vlille

clean:
	rm -f *.o output input data.bin

.PHONY: all clean

