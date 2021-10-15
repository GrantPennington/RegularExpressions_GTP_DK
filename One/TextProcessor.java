package One;
/**
 * This class uses the text of Dracula and regex patterns to find patterns.
 * @author Grant Pennington, Dalton Kilner
 * @version 1.0
 * Compiler Project 3
 * CS322 - Compiler Construction
 * Fall 2021
 */

// REGEX PATTERNS
/* 
 * 1. Articles 'a', 'an', 'the' -> "\ba\b|\ban\b|\bthe\b"
 * 2. The phrase ‘Mina Harker’ or ‘Mrs. Harker’ -> "\bMina\b\s\bHarker\b"
 * 3. Phrases containing the word "Transylvania" -> "\bTransylvania\b"
 * 4. infinitive phrases -> "\bto\b\s[a-zA-Z]*\b"
 * 5. Words ending in "ing" except Godalming and Helsing -> "(?!(\bGodalming\b|\bHelsing\b))\b(\w+ing)\b"
 */

/** 
 * To run this program in the command line -> java TextProcessor.java Dracula.txt <RegexPattern>
 * Where RegexPattern is one of the patterns listed above
 */ 


import java.util.regex.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextProcessor {

    static int count = 0;
    static int lineNumber = 0;

    public static void processLine(String regex, String line){
        lineNumber++; // variable to keep track of the line number for each match
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE); // compile the regex pattern that is inputted as an argument
        Matcher matcher = pattern.matcher(line); // matcher class to match the pattern against each line
        
        while (matcher.find()) { // while loop to find the start and end index of each match
            System.out.print("Line: "+lineNumber+" Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end() + " ");
            System.out.println(matcher.group());
            count++; // increment the count every time a match is found
        }
    }
    public static void main(String[] args) throws IOException {

        try (Stream<String> lines = Files.lines(Paths.get(args[0]))) { // read all lines from command line argument[0]
            String regex = args[1]; // save the regex pattern from command line input
            lines.forEach((String line) -> { // using forEach() to read each line from the text file
                processLine(regex, line); // passing the regex pattern and line to the processLine() method
            });
            
            System.out.println("Number of occurences: "+count);

        } catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }
}
