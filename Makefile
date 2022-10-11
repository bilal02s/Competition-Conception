CFLAGS=-ansi -Wall -pedantic

exec.jar : src/competition/Main.java
	jar cvfe exec.jar competition.Main -C classes competition -C classes util


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
	javac -d classes -cp classes:junit-platform-console-standalone-1.9.1.jar test/competition/*.java

testEvent : test/competition/event/*.java  
	javac -d classes -cp classes:junit-platform-console-standalone-1.9.1.jar test/competition/event/*.java 
	
testMatch : test/competition/match/*.java
	javac -d classes -cp classes:junit-platform-console-standalone-1.9.1.jar test/competition/match/*.java

docs : src/competition/*.java
	javadoc competition -sourcepath src -d docs -subpackages competition

clean:
	rm -f *.o output input data.bin

.PHONY: all clean

