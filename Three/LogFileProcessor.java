package Three;

/**
* Description of this class.
* @author Grant Pennington, Dalton Kilner
* @version 1.0
* Compiler Project 3
* CS322 - Compiler Construction
* Fall 2021
*/
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.util.HashMap;

public class LogFileProcessor {
    static int count;

    // Create a HashMap object called IPAddress
    static HashMap<String, Integer> IPAddress = new HashMap<String, Integer>();


    // Create a HashMap object called users
    static HashMap<String, Integer> Users = new HashMap<String, Integer>();


    static void printDetails() {
        System.out.println(count + " lines in the file were parsed.");
        System.out.println("There are " + IPAddress.size() + " unique IP addresses in the log.");
        System.out.println("There are " + Users.size() + " unique users in the log.");
    }
    public static void main(String[] args) {
        count = 0;
        if(args.length != 2) {
            System.exit(-1);
        }
        //file 1 fileReader
        try {

            Scanner scanner1 = new Scanner(new File(args[0]));
            while (scanner1.hasNextLine()){
                String line = scanner1.nextLine();
                String address = ipparser(line);
                if (!address.equals("")) {
                    if (IPAddress.containsKey(address)) {
                        IPAddress.put(address, IPAddress.get(address) +1);
                    } else {
                        IPAddress.put(address, 1);
                    }
                }
                String name = userparser(line);
                if (!name.equals("")) {
                    if (Users.containsKey(name)) {
                        Users.put(name, Users.get(name) +1);
                    } else {
                        Users.put(name, 1);
                    }
                }
                
                count = count + 1;
            }
            scanner1.close();
        }
        
        catch(FileNotFoundException e) {

        }

        //if statements  
        if (args[1].equals("0") ) {
            printDetails();
        }

        else if (args[1].equals("1")) {
            printIPAddress();
        }

        else if (args[1].equals("2")) {
            printUsers();
        }

        else System.out.println("invalid argument");

        }

        static void printUsers() {
            for (String i : Users.keySet()) {
                System.out.println( i +" : " + Users.get(i));
            }
            printDetails();
        }

        static void printIPAddress() {
            for (String i : IPAddress.keySet()) {
                System.out.println( i +" : " + IPAddress.get(i));
                }
            printDetails();
        }

        static String ipparser(String str) {
            String IPPattern = "(?:(?:25[0:5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?";
            Pattern pattern = Pattern.compile(IPPattern);
            Matcher match = pattern.matcher(str);
            if (match.find() ) {
                return match.group();
                
            } else return ("");
        }

        static String userparser(String str) {
            String userPattern = "(user):\\s*(\\w+)";
            Pattern pattern = Pattern.compile(userPattern);
            Matcher match = pattern.matcher(str);
            if (match.find()) {
                return match.group(2);
            } else return ("");
        }


}
