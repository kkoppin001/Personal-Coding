/*@author Kalli Koppin
 * December 6th, 2023
 * CS 1181
 * 
 * Password Cracker for Three Characters
 * 
 * This code will run try to crack the password that is put onto the
 * Zip file and then extract the contents and put it in a separate 
 * folder
 */
import java.util.*;
import java.io.*;
import net.lingala.zip4j.core.*;
import net.lingala.zip4j.exception.*;

public class crackItForThree {
    public static void main(String[] args) {

        // variable declaration
        String zipPath = "protected3.zip";
        ArrayList<String> combinations = generateCombos("", 3);
        long startTime = System.currentTimeMillis();
        boolean foundPassword = false;

        //while the foundPassword boolean is false, run through this loop
        while (foundPassword == false) {

            //for loop to find password in the combinations arraylist
            for (String password : combinations) {
                //if the password is found, the extract it method will be called and set to true
                if (extractIt(zipPath, password) == true) {
                    foundPassword = true;
                    long endTime = System.currentTimeMillis();
                    
                System.out.println("Found password: " + password);
                System.out.println("Time taken: " + (endTime - startTime) + " millis");
                    break;
                }
            }
    }
    }
// recurrsion method to add all combinations from aaa-zzz added to an arrayList
    public static ArrayList<String> generateCombos(String part, int length) {
       // variable declaration
        ArrayList<String> combinations = new ArrayList<>();

        // base case
        if (length == 0) {
            combinations.add(part);

        } else {
            // add all combinations
            for (char c = 'a'; c <= 'z'; c++) {
                combinations.addAll(generateCombos(part + c, length - 1));
            }
        }

        return combinations;
    }

    //extract it method
    public static boolean extractIt(String zipPath, String password) {

      /* if the passwordfound is true, this method will extract its contents to a "contents folder
       * and the password will be used to so
      */
        try {
            ZipFile zipFile = new ZipFile(zipPath);
            zipFile.setPassword(password);
            zipFile.extractAll("contents");
            return true;
            //if the password is not found, the method is false
        } catch (ZipException e) {
            return false;
        }
    }

}
