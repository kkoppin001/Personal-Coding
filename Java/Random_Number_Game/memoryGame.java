// Name: Kalli Koppin
// Class: CS 1180L
// Due Date: 25 February 2023
// This program is a This	program	is	a	simple	memory	game. 
// where the objective is to answer the different types of questions 
// with 3 unique numbers each round
// if the user gets the answer correct, they will add a point to their score
// if their answer is wrong, it will tell them the right answer
// and the user does not get the point

import java.util.Scanner;
import java.util.Random;

class memoryGame {
  public static void main(String[] args) {

    // declaring integer variables
    Random n = new Random();
    int up = 101;
    int num1 = 0;
    int num2 = 0;
    int num3 = 0;
    int randq = 0;
    int ans = 0;
    int wins = 0;
    int smallNum = 0;
    int largeNum = 0;
    int midNum = 0;
    int total = 0;
    int i = 0;

// loop ten times so you can repeat the game
for (i = 0; i < 10; i++) {

  // random number generator
      num1 = n.nextInt(up);
      num2 = n.nextInt(up);
      num3 = n.nextInt(up);
      while (num1 == num2 || num1 == num3 || num2 == num3) {
        if (num1 == num2) {
          num2 = n.nextInt(up);
        } else if (num1 == num3) {
          num3 = n.nextInt(up);
        } else if (num2 == num3) {
          num1 = n.nextInt(up);
        }
      }

    //Number checker to make sure no numbers are the same
    if ((num1 != num2) || (num1 != num3) && (num2 != num1) || (num2 != num3) && (num3 != num1) || (num3 != num1)) {
      System.out.print("The numbers for this round are: " + num1 + " " + num2 + " " + num3);
    }

    // print 100 lines so you can not see the numbers
  try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // loop so it will give ten rounds
    for (int j = 0; j < 100; j++) {
      System.out.println(" ");
    }

// math for the smallest numbers
 ans = smallNum;
      if ((num1 < num2) && (num1 < num3)) {
      smallNum = num1;
    }
    if ((num2 < num1) && (num2 < num3)) {
      smallNum = num2;
    }
    if ((num3 < num1) && (num3 < num2)) {
      smallNum = num3;
    }

// math for the largest numbers
    if ((num1 > num2) && (num1 > num3)) {
      largeNum = num1;
    }
    if ((num2 > num1) && (num2 > num3)) {
      largeNum = num2;
    }
    if ((num3 > num1) && (num3 > num2)) {
      largeNum = num3;
    }
      ans = largeNum;

  // math for the median numbers numbers
  if (num1 > num2 && num1 < num3) {
      midNum = num1;      
    }
    if (num2 > num1 && num2 < num3) {
      midNum = num2;
    }
    if ((num3 > num1) && (num3 < num2)) {
      midNum = num3;
} 

  // math for the total of the numbers
      total = num1 + num2 + num3;
      ans = total;

    //random question generator for each round with a checker to see if it is right
        randq = n.nextInt(4);
    if (randq == 0) {
      System.out.print("What is the smallest number? ");
      Scanner in = new Scanner(System.in);
     String useAns = in.next();
    int answer = Integer.parseInt(useAns);
    if (answer == smallNum ){
        System.out.println("thats right!");
      wins++;
    } else {
    System.out.println("thats wrong. The correct answer was " + smallNum);
    } 
    } else if (randq == 1) {
      System.out.print("What is the largest number? ");
      Scanner in = new Scanner(System.in);
     String useAns = in.next();
    int answer = Integer.parseInt(useAns);
    if (answer == largeNum){
        System.out.println("thats right!");
      wins++;
    } else {
    System.out.println("thats wrong. The correct answer was " + largeNum);
    } 
    } else if (randq == 2) {
      System.out.print("What is the median number? ");
      Scanner in = new Scanner(System.in);
     String useAns = in.next();
    int answer = Integer.parseInt(useAns);
    if (answer == midNum){
        System.out.println("thats right!");
      wins++;
    } else {
    System.out.println("thats wrong. The correct answer was " + midNum);
    }
    } else if (randq == 3) {
      System.out.print("What is the sum of all numbers? ");
      Scanner in = new Scanner(System.in);
     String useAns = in.next();
    int answer = Integer.parseInt(useAns);
    if (answer == total){
        System.out.println("thats right!");
      wins++;
    } else {
    System.out.println("thats wrong. The correct answer was " + total);
    }
    }

}// closing the for loop

    // total rounds won
    System.out.print("you got " + wins + " out of 10 right!");
    
  }// end of main method
}// end of Main class