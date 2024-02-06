// Name: Kalli Koppin
// Class: CS 1180L
// Due Date: 11 February 2023
// This program is a domino-like tile game, 
// where the objective is to determine the maximum 
// score possible by arranging the tiles according 
// to the scoring rules of the game.

import java.util.*;

class TileGame{
    public static void main(String[] args){
        
        // index verification for potential tiles
        // 3 | 2 , 2 | 3
        // 0123456789112 (index numbers condensed) 
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 (index numbers expanded)

        // 3 | 1 , 2 | 3
        // 0123456789112 (index numbers condensed) 
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 (index numbers expanded)

        // 2 | 3 , 3 | 1
        // 0123456789112 (index numbers condensed) 
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 (index numbers expanded)
        
        
        //variable declaration
        Scanner in = new Scanner(System.in);
        String input;
        int score;// this is if both tiles can be scored at once
        int tile1Score;// this is if tile 1 can score greater than tile 2
        int tile2Score;// this is if tile 2 can score greater than tile 1

        System.out.print("Input: ");
        input = in.nextLine();
       

 
        if(input.length() > 13){ //this if statement checks to see if there is too long of an input

            System.out.println("Output: Invalid input. quitting…");

        }else if(input.length() < 12){//this if statement checks to see if there is too short of an input

            System.out.println("Output: Invalid input. quitting…");

        }else if(input.charAt(2) != '|' || input.charAt(10) != '|'){ 
            //this if statement checks to see if there is the correct symbol at index 2 and 10 

            System.out.println("Output: Invalid input, does not use the \"|\" symbol, quitting…");

        }else if(input.charAt(6) != ','){
             //this if statement checks to see if there is the correct symbol at index 6

            System.out.println("Output: Invalid input, does not use the \",\" symbol, quitting…");

        }else if(Character.getNumericValue(input.charAt(0)) < 1 || Character.getNumericValue(input.charAt(0)) > 3){
             //this if statement checks to see if the number is less than 1 or greater than 3

            System.out.println("Output: Invalid input. quitting…");

        }else if(Character.getNumericValue(input.charAt(4)) < 1 || Character.getNumericValue(input.charAt(4)) > 3){
            //this if statement checks to see if the number is less than 1 or greater than 3

            System.out.println("Output: Invalid input. quitting…");

        }else if(Character.getNumericValue(input.charAt(8)) < 1 || Character.getNumericValue(input.charAt(8)) > 3){
            //this if statement checks to see if the number is less than 1 or greater than 3

            System.out.println("Output: Invalid input. quitting…");

        }else if(Character.getNumericValue(input.charAt(12)) < 1 || Character.getNumericValue(input.charAt(12)) > 3){
            //this if statement checks to see if the number is less than 1 or greater than 3

            System.out.println("Output: Invalid input. quitting…");

        }else if (input.charAt(4) == input.charAt(8)){
            //this if statement checks to see if the two inner numbers are matching, that will determine a score
        
           // calculation for the total score if applicable
            score = (Character.getNumericValue(input.charAt(0)) + Character.getNumericValue(input.charAt(12))) * 2;
            System.out.print("Output: " + input + " -> " + score + " points");

        }else if (input.charAt(0) == input.charAt(12)){
            //this if statement checks to see if the two outter numbers are matching, that will determine a score by rearranging the tiles
            score = (Character.getNumericValue(input.charAt(4)) + Character.getNumericValue(input.charAt(8))) * 2;
            System.out.print("Output: " + (input.substring(8,12)) +  (input.substring(0,9)) +" -> " + score + " points");


        }else if(input.charAt(4) != input.charAt(8)){
             //this if statement checks to see if the two inner numbers are matching, if not, it will pick the highest scoring tile

                  tile1Score = (Character.getNumericValue(input.charAt(0)) + Character.getNumericValue(input.charAt(4)));// calculating tile 1 score

        tile2Score = (Character.getNumericValue(input.charAt(8)) + Character.getNumericValue(input.charAt(12)));// calculating tile 2 score

        if(tile1Score > tile2Score){// checks if the first tile has a greater score than tile two
            System.out.print("Output: " + input.substring(0,5) + " -> " + tile1Score + " points");

        }else if(tile2Score > tile1Score){// checks if the second tile has a greater score than tile one
            System.out.print("Output: " + input.substring(8,12) + input.charAt(12) + " -> " + tile2Score + " points");
        }

        }

    in.close();
        
    }

}