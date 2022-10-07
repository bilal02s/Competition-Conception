## Projet - Compétitions Sportives
---

## Done by

**Bilal EL Safah**


---

### Files

```text
l3s5-projet-coo/
├── src/
│   └── competition/
│       ├── competition/
│       │   ├──Championnat.java
│       │   ├── Competition.java
|       |   └── Tournoi.java
│       ├── exception/
│       │   ├── InsufficientNumberOfPlayersException.java
│       │   ├── IntegerNotPowerOf2Exception.java
│       │   └── WrongNumberOfPlayersException.java
|       ├── match/
|       |   ├── Match.java
|       |   └── RandomWinner.java
│       ├── Competitor.java
│       ├── Main.java
│   └── util/
│       └── MapUtil.java
├── test/
│   └── competition/
│       ├── competition/
|          ├── ChampionnatTest.java
|          ├── CompetitionTest.java
|          ├── TournoiTest.java
|       ├── match/
|
|
│
├── exec.jar
|   MakeFile
├── manifest.
├── test4poo.jar
└── readme.md
```

### Objectives
*  Representing and Manipulating images

We are interested in the representation and manipulation of images. These images will consist of pixels characterized by a color representing a gray level.

*  Using arguments from the executing command



### Getting the files
1. To compile and execute the files, you need to install [JDK Java SE](https://www.oracle.com/java/technologies/javase-downloads.html).

2. Get the project with the following command
```bash
git pull
```





### Generate JavaDoc files
* In a bash terminal

1. To generate the Javadoc for the image package and all its sub packages:

In the src directory, execute the following command:
```bash
javadoc -d ../docs -subpackage image
```


You will notice a new directory docs.

2.  To consult the JavaDoc: in the docs Directory, open the index.html file in a web browser.



### Compile .java files
* In a bash terminal

1. Go to the corresponding folder image/src

2. Compile all the .java file, and store them in the classes folder.
```bash
javac image/*.java -d ../classes
```

3. You will notice that the classes folder contains the structure of the packages containing the compiled files with a .class extension.


-------------------------------------------------------------------------------------------------------

### Compile and execute the tests
* In a bash terminal

1. Go to image directory

2. To compile all the test files, excute the following command :
```bash
javac -classpath test4poo.jar test/*.java
```

3. To Execute a test FileNameTest, execute the following command
```bash
java -jar test4poo.jar FileNameTest
```

4. A graphic interface will pop up. A green line means that all the tests have been passed correctly, a red line means that there are some tests that failed.



### Generate the executable .jar files
1. Go to the image/classes directory, and execute the following command :
```bash
jar cvfm ../image.jar ../manifest-image image images
```

2. You will notice that an image.jar file has been created in image




### Execute the program
* Without using the image.jar file

    1. Go to your image/classes directory

    2. To execute the main class, there are diffrent arguments you can add:

        1. Default form:

           ```bash
           java image.ImageMain
           ```

        2. precising the image file to use:

           ```bash
           java image.ImageMain fileName
           ```
           * Example:
           ```bash
           java image.ImageMain /images/saturn.pgm
           ```

        3. precising the image file to use and the threshold:

           ```bash
           java image.ImageMain fileName threshold
           ```
           * Example:
           ```bash
           java image.ImageMain /images/casablanca.pgm 20
           ```

        4. precising the image file to use, the threshold and the nbGrayLevels:

           ```bash
           java image.ImageMain fileName threshold nbGrayLevels
           ```
           * Example:
           ```bash
           java image.ImageMain /images/body.pgm 25 16
           ```

    3. You will notice that the ImageMain has been executed.

    4. The following will apear in you bash terminal:
       ```text
       usage : java image.ImageMain fileName threshold nbGrayLevels (default : /images/chat.pgm 15 4)
       ```

    5. And five windows will pop up showing:

        1. An Image with 3 rectangles with diffrent gray color

        2. The same image but showing just the edges of the rectangles in black

        3. The chosen image file

        4. The same image file showing just the edges of the image in black

        5. the same image with decreased gray colors

* Using the image.jar file

    1. Go to your image directory

    2. To execute the jar file, you can add the same arguments as above:

        1. Default form:

           ```bash
           java -jar image.jar
           ```

        2. precising the image file to use:

           ```bash
           java -jar image.jar fileName
           ```
           * Example:
           ```bash
           java -jar image.jar /images/hands.pgm
           ```

        3. precising the image file to use and the threshold:

           ```bash
           java -jar image.jar fileName threshold
           ```
           * Example:
           ```bash
           java -jar image.jar /images/frog.pgm 10
           ```

        4. precising the image file to use, the threshold and the nbGrayLevels:

           ```bash
           java -jar image.jar fileName threshold nbGrayLevels
           ```
           * Example:
           ```bash
           java -jar image.jar /images/storm.pgm 15 8
           ```

    3. You will notice that the ImageMain class has been executed.

    4. The following will apear in you bash terminal:
       ```text
       usage : java image.ImageMain fileName threshold nbGrayLevels (default : /images/chat.pgm 15 4)
       ```

    5. And five windows will pop up showing:

        1. An Image with 3 rectangles with diffrent gray color

        2. The same image but showing just the edges of the rectangles in black

        3. The chosen image file

        4. The same image file showing just the edges of the image in black

        5. the same image with decreased gray colors
