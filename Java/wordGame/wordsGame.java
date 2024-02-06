// Name: Kalli Koppin
// Class: CS 1180L
// Due Date: 25 March 2023
// This	program	involves one turn in a simple word
// game based on one from the New York Times
// In the game,	a player tries to make as many valid 
// words as possible from a list of letters.

import java.util.*;
import java.io.*;

class wordsGame {
    public static void main(String[] args) throws Exception {

        //variable declaration
        Scanner in = new Scanner(new File("words.txt"));
        ArrayList<String> recordedWords = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int score = 0;
        String inpt = "m";
        ArrayList<String> words = new ArrayList<>();

        //calling methods line
        String word = randoWords(in, words);
        output(word, score);

        while(!inpt.equals("bye")) {
            inpt = input(sc, word, score, recordedWords);

            switch (inpt){
                case "bye":
                    break;

                default:
                    recordedWords.add(inpt);
                    score = trackingScore(inpt, score);
                    output(word, score);
            }
        }
        System.out.println("Goodbye...");

    }// end of main

    //method to keep track of score as you go on in the game
    public static int trackingScore(String inpt, int score){

        switch (inpt.length()){
            case 4:
                score += 1;
                break;
            default:
                score += inpt.length();
                break;
        }
        return score;
        
    }// end of tracking score

    //shuffles random letters and words
    public static String randoWords(Scanner in, ArrayList<String> words) throws Exception{

        String randomWord = null;

        while(in.hasNext()) {
            words.add(in.nextLine());
            Collections.shuffle(words);
            randomWord = words.get(0);
        }
        while(uniqueLetters(randomWord)) {
            Collections.shuffle(words);
            randomWord = words.get(0);
        }

        return randomWord;
    }// end of randoWords

    //checks to see if there are less than seven letters in the word to choose which word to pick
    public static boolean uniqueLetters(String randomLine) throws Exception {

        Set<Character> newSet = new HashSet<>();

        if (randomLine.length() != 7) {
            return true;
        }
        for (int letter = 0; letter < randomLine.length(); letter++) {
            newSet.add(randomLine.charAt(letter));
        }
        return !(newSet.size() == randomLine.length());
    }// end of unique letters

    //method to space out word
    public static void output(String words, int score) throws Exception {
        List<String> word = Arrays.asList(words.split(""));
        java.util.Collections.shuffle(word);
        int counter = 0;

        while(counter != word.size()){
            System.out.print("       " + word.get(counter));
            counter += 1;
        }
        System.out.println("\nscore: " + score);
    }// end of output

    //reads in the user input method
    public static String input(Scanner sc, String randoWord, Integer score,  ArrayList<String> recordedWords) throws Exception{

        String inpt = sc.nextLine();

        while(checking(inpt, randoWord, score, recordedWords)) {
            inpt = sc.nextLine();
        }
        return inpt;
    }//end of input

    // checks for commands and if the input os valid or not
    public static boolean checking(String inpt, String randoWord, Integer score,  ArrayList<String> recordedWords) throws Exception{
        ArrayList<Character> character = new ArrayList<>();
        Boolean same = false;
        int checkInputWord = 0;
        int checkingLetters = 0;
        int checkingRecordedWord = 0;
        int checkingLength = 0;

        for (int i = 0; i < randoWord.length(); i++) {
            character.add(randoWord.charAt(i));
        }// end of for loop

        switch(inpt){
            case "bye":
                return false;
            case "mix":
                Collections.shuffle(character);
                dispOut(character, score);
                return true;
            case "ls":
                if (recordedWords.isEmpty()){
                    System.out.println("Please enter a valid word, try again");
                    return true;
                } else{
                    for(String yuh: recordedWords){
                        System.out.println(yuh);
                    }
                    return true;
                }
        }// end of switch

        // checks if its less than 4
        if (inpt.length() < 4){
            checkingLength = 1;
        }
        switch (checkingLength){
            case 1:
                System.out.println("Word must be atleast 4 letters, please try again");
                return true;
        }

        // sees if the input has the same letters for the unique letters
        while(checkInputWord != inpt.length()){
            // resets counters
            same = false;
            checkingLetters = 0;

            while(checkingLetters != character.size()){
                if (inpt.charAt(checkInputWord) == character.get(checkingLetters)){
                    same = true;
                }
                checkingLetters += 1;
            }
            if (!same){
                System.out.println("Word is using letters not listed in the given ones, please try again");
                return true;
            }
            checkInputWord += 1;
        }

        // already recorded words
        while (checkingRecordedWord != recordedWords.size()){

            if (inpt.equals(recordedWords.get(checkingRecordedWord))){
                System.out.println("Word is already used, please try again");
                return true;
            }
            checkingRecordedWord += 1;
        }// end of while loop

        // reading in the file to check if user word is listed in the file
        FileReader fileReader = new FileReader("words.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String s = br.readLine();
        while((s = br.readLine())!=null){
            if (inpt.equals(s)){
                return false;
            }

        }// end of while loop
        System.out.println("Could not find word, please try again");
        
        return true;
    }// end of checking method

    //output method
    public static void dispOut(ArrayList<Character> character, Integer score) {
        int counter = 0;

        while (counter != character.size()){
            System.out.print("       " + character.get(counter));
            counter++;
        }// end of while loop
        System.out.println("\nscore: " + score);

    }// end of dispOut
}// end of class
