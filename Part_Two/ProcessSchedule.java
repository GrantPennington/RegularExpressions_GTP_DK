package Part_Two;
/**
 * This class will use regex patterns to find certain data elements from the extracted text and write them to files
 * @author Grant Pennington, Dalton Kilner
 * @version 1.0
 * Compiler Project 3
 * CS322 - Compiler Construction
 * Fall 2021
 */

/**
 * REGEX PATTERNS
 * Pattern to find all courses and their info -> "(\\b[A-Z]{2,}\\b-\\d+-\\d+)(.*?)(\\d+[:]\\d+[-]\\d+[:]\\d+)"
 * Pattern for course codes -> "\\b[A-Z]{2,}\\b-\\d+-\\d+"
 * Pattern for course status -> "(\\bOpen\\b|\\bCLOSED\\b)\\s\\d+\\s\\d+"
 */


import java.util.regex.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ProcessSchedule {
    
    static int count = 0;

    // Method to extract course info from the Bellarmine_Schedule.txt generated from the ExtractText class
    public static void GetCourseInfo() throws IOException {
        
        try (Stream<String> lines = Files.lines(Paths.get("/home/cs322/Documents/RegularExpressions/Part_Two/Bellarmine_Schedule.txt"))) { // read all lines from Bellarmine_Schedule.txt
            File courseInfo = new File("/home/cs322/Documents/RegularExpressions/Part_Two/courseInfo.txt");
            FileWriter writer = new FileWriter(courseInfo);
            Pattern pattern = Pattern.compile("(\\b[A-Z]{2,}\\b-\\d+-\\d+)(.*?)(\\d+[:]\\d+[-]\\d+[:]\\d+)", Pattern.CASE_INSENSITIVE); // compile the regex pattern
            lines.forEach((String line) -> { // using forEach() to read each line from the text file
                Matcher matcher = pattern.matcher(line); // matcher class to match the pattern against each line
                while (matcher.find()) { // while loop to find each match
                    try{
                        writer.write(matcher.group()); // write each match to the courseInfo.txt file
                        writer.write("\n"); // write a newline after each match
                    } catch(Exception e){
                        System.out.println(e);
                    }
                }
            });
        } catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    // Method to extract the course code and status of each course from the courseInfo.txt generated by the GetCourseInfo() method
    public static void GetCourseStatus() throws IOException {
        
        try (Stream<String> lines = Files.lines(Paths.get("/home/cs322/Documents/RegularExpressions/Part_Two/courseInfo.txt"))) { // read all lines from the courseInfo.txt generated from the GetClassInfo() method
            
            // File and FileWriter classes
            File status = new File("/home/cs322/Documents/RegularExpressions/Part_Two/courseStatus.txt");
            FileWriter writer = new FileWriter(status);
            
            Pattern courseCodes = Pattern.compile("\\b[A-Z]{2,}\\b-\\d+-\\d+", Pattern.CASE_INSENSITIVE); // compile the regex pattern for course codes
            Pattern courseStatus = Pattern.compile("(\\bOpen\\b|\\bCLOSED\\b)\\s\\d+\\s\\d+", Pattern.CASE_INSENSITIVE); // compile the regex pattern for course status and seats
            
            lines.forEach((String line) -> { // using forEach() to read each line from the text file
                Matcher codeMatcher = courseCodes.matcher(line); // matcher class for courseCodes
                Matcher statusMatcher = courseStatus.matcher(line); //matcher class for courseStatus
                while(codeMatcher.find()){
                    try{
                        writer.write("Course: "+codeMatcher.group());
                    } catch(Exception e){
                        System.out.println(e);
                    }
                }
                while (statusMatcher.find()) { // while loop to find each match
                    try{
                        writer.write(" Status: "+statusMatcher.group());
                        writer.write("\n"); // write a newline after each match
                    } catch(Exception e){
                        System.out.println(e);
                    }
                }
            });
        } catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    // Method to extract all the course codes from Bellarmine_Schedule.txt
    public static void GetCourseCodes(){
        try (Stream<String> lines = Files.lines(Paths.get("/home/cs322/Documents/RegularExpressions/Part_Two/Bellarmine_Schedule.txt"))) { // read all lines from Bellarmine_Schedule.txt
            Pattern pattern = Pattern.compile("\\b[A-Z]{2,}\\b-\\d+-\\d+", Pattern.CASE_INSENSITIVE); // compile the regex pattern
            lines.forEach((String line) -> { // using forEach() to read each line from the text file
                Matcher matcher = pattern.matcher(line); // matcher class to match the pattern against each line
                while (matcher.find()) { // while loop to find each match   
                    System.out.print("Start index: " + matcher.start());
                    System.out.print(" End index: " + matcher.end() + " ");
                    System.out.println(matcher.group());
                    count++;
                }
            });
            System.out.println("Number of course codes: "+count);
        } catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }



    public static void main(String[] args) throws IOException {
        GetCourseInfo(); // get the course info
        GetCourseStatus(); // get course status
        GetCourseCodes(); // get course codes
    }
}
