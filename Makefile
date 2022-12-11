CFLAGS=-ansi -Wall -pedantic

exec.jar : src/competition/Main.java
	jar cvfe exec.jar competition.Main -C classes competition -C classes util


src : util competition exception matchEvent match event displayer reader journalist report #main

competition : src/competition/*.java 
	javac -sourcepath src -d classes src/competition/*.java 

exception : src/competition/exception/*.java 
	javac -sourcepath src -d classes src/competition/exception/*.java

match : src/competition/match/*.java 
	javac -sourcepath src -d classes src/competition/match/*.java 

matchEvent : src/competition/match/event/*.java
	javac -sourcepath src -d classes src/competition/match/event/*.java

event : src/competition/event/*.java 
	javac -sourcepath src -d classes src/competition/event/*.java 

io : src/competition/io/*.java 
	javac -sourcepath src -d classes src/competition/io/*.java

displayer : src/competition/io/displayer/*.java
	javac -sourcepath src -d classes src/competition/io/displayer/*.java

reader : src/competition/io/reader/*.java 
	javac -sourcepath src -d classes src/competition/io/reader/*.java

journalist : src/competition/journalist/*.java
	javac -sourcepath src -d classes src/competition/journalist/*.java 

report : src/competition/journalist/report/*.java
	javac -sourcepath src -d classes src/competition/journalist/report/*.java

util : src/util/*.java
	javac -sourcepath src -d classes src/util/*.java

#main : src/Main.java 
#	javac -sourcepath src -d classes src/Main.java

test : mock testUtil testCompetition testReport testJournalist testEvent testMatch testMatchEvent

mock : mockMatch mockIO

mockMatch : test/competition/match/mock/*.java
	javac -d classes -cp classes:junit-platform-console-standalone-1.9.1.jar test/competition/match/mock/*.java

mockIO : test/competition/io/mock/*.java 
	javac -d classes -cp classes:junit-platform-console-standalone-1.9.1.jar test/competition/io/mock/*.java

testCompetition : test/competition/*.java 
	javac -d classes -cp classes:junit-platform-console-standalone-1.9.1.jar test/competition/*.java

testEvent : test/competition/event/*.java  
	javac -d classes -cp classes:junit-platform-console-standalone-1.9.1.jar test/competition/event/*.java 
	
testMatch : test/competition/match/*.java
	javac -d classes -cp classes:junit-platform-console-standalone-1.9.1.jar test/competition/match/*.java

testMatchEvent : test/competition/match/event/*.java
	javac -d classes -cp classes:junit-platform-console-standalone-1.9.1.jar test/competition/match/event/*.java

testJournalist : test/competition/journalist/*.java
	javac -d classes -cp classes:junit-platform-console-standalone-1.9.1.jar test/competition/journalist/*.java

testReport : test/competition/journalist/report/*.java 
	javac -d classes -cp classes:junit-platform-console-standalone-1.9.1.jar test/competition/journalist/report/*.java

testUtil : test/util/*.java
	javac -d classes -cp classes:junit-platform-console-standalone-1.9.1.jar test/util/*.java

docs : src/competition/*.java
	javadoc competition -sourcepath src -d docs -subpackages competition

clean:
	rm -f *.o output input data.bin

.PHONY: all clean

