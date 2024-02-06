/*@author Kalli Koppin
 * December 6th, 2023
 * CS 1181
 * 
 * Password Cracker for Five Characters
 * 
 * This code will run a number of threads and will 
 * try to break the password that is put onto the
 * Zip file and then extract the contents and put it in a separate 
 * folder
 * 
 * link for object lock and synchronize block -> https://www.javatpoint.com/synchronized-block-example
 * link for files.walk -> https://stackoverflow.com/questions/29574167/how-to-use-files-walk-to-get-a-graph-of-files-based-on-conditions
 * 
 * Time with 3 Threads: 1676296 ms -> ~27 minutes
 * Time with 4 Threads: 419735 ms -> ~7
 */
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import net.lingala.zip4j.core.*;
import net.lingala.zip4j.exception.*;

public class crackItForFive{
    //static variable declaration
    static final int numThreads = 4;
    static volatile boolean foundPassword = false;
    static final Object lock = new Object();
    public static void main(String[] args){
        //variable declaration
        String zipPath = "protected5.zip";
        ArrayList<String> combos = generateCombos("", 5);
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[numThreads];

        //separating the threads so they are doing equal work
        int sections = combos.size() / numThreads;
        for(int i = 0; i < numThreads; i ++){
            int startIndex = i * sections;
            int endIndex = (i == numThreads - 1) ? combos.size() : (i + 1) * sections;

            ArrayList<String> subset = new ArrayList<>(combos.subList(startIndex, endIndex));
            String outputFolder = "contents" + i;

            //threads inner class
            threads[i] = new Thread(() -> {
                try {
                    //for loop to find password in the combinations arraylist
                    for(String password : subset){
                         //if the password is found, the extract it method will be called and set to true
                         // and then the methods to do some work before the program finished
                        if(extractIt(zipPath, password, outputFolder) == true){
                            foundPassword = true;
                            System.out.println(Thread.currentThread().getName() + " found the password");
                            System.out.println("Correct password found: " + password);
                            long endTime = System.currentTimeMillis();
                            System.out.println("Time took: " + (endTime - startTime) + " ms");
                            moveContentsToDone(outputFolder);
                            checkAndExit(outputFolder);
                            break;
                        }
                    }
                    //removes the original folder that had the correct password
                } finally {
                   cleanUp(outputFolder);
                }
            });

            threads[i].start();
               }
//starting threads
               try{
                for(Thread thread : threads){
                    thread.join();
                }
               }catch(InterruptedException e){
                e.printStackTrace();
               }


    }
// recursion method to generate combos for potential passwords
       public static ArrayList<String> generateCombos(String part, int length) {
       ArrayList<String> combinations = new ArrayList<>();
       if (length == 0) {
           combinations.add(part);
       } else {
           for (char c = 'a'; c <= 'z'; c++) {
               combinations.addAll(generateCombos(part + c, length - 1));
           }
       }


       return combinations;
   }

   //boolean method for comparing and extrating
      public static boolean extractIt(String zipPath, String password, String outputFolder) {
       try {
           ZipFile zipFile = new ZipFile(zipPath);
           zipFile.setPassword(password);
           zipFile.extractAll(outputFolder);
           System.out.println("Contents Extracted");
           System.out.println("Message File in Done folder");
           return true;
       } catch (ZipException e) {
           return false;
       }
   }

   //cleans up files and folders(i understand that this does not work 100%)
   public static void cleanUp(String outputFolder) {
    try {
        Files.walk(Paths.get(outputFolder))
                .filter(Files::isRegularFile)
                .forEach(file -> {
                });

        Files.walk(Paths.get(outputFolder))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(folder -> {
                });
    } catch (IOException e) {
        e.printStackTrace();
    }
}


// checks to make sure if the boolean is true, and then will terminate all threads
    public static void checkAndExit(String outputFolder){
        synchronized(lock){
            if(foundPassword == true){
                moveContentsToDone(outputFolder);
                cleanUp(outputFolder);
                System.exit(0);
            }
        }
    }

    // this method moves contents from the cracked password folder to a separate folder
    public static void moveContentsToDone(String outputFolder) {
        try {
            Path sourcePath = Paths.get(outputFolder);
            Path targetPath = Paths.get("done");
    
            // Create the "done" folder if it doesn't exist
            if (!Files.exists(targetPath)) {
                Files.createDirectory(targetPath);
            }
    
            Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path targetFile = targetPath.resolve(sourcePath.relativize(file));
    
                    // check if the source file exists before moving
                    if (Files.exists(file)) {
                        Files.move(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                    }
    
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}