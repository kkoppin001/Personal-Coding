// Name: Kalli Koppin
// Class: CS 1180L
// Due Date: 19 April 2023
// This	program	involves the use of classes & objects
// to add characters, add items, remove items by selling
// or dropping items to other characters or vendors


import java.util.*;

class Main {
  public static void main(String[] args) {
//declaration of some array lists
    ArrayList<Character> characters = new ArrayList<>();
    ArrayList<Item> objects = new ArrayList<>();
    Scanner in = new Scanner(System.in).useDelimiter("\\n"); 
    // I found the .useDelimiter from stack overflow so you can enter a name longer than one word
   
  
    while (true) {
// commands menu

      System.out.println("1. Create a character.");
      System.out.println("2. Character adds and item.");
      System.out.println("3. Character drops an item.");
      System.out.println("4. Character sells an item to a vendor.");
      System.out.println("5. Character sells an item to another character.");
      System.out.println("6. List Characters.");
      System.out.println("7. List a character's inventory by value.");
      System.out.println("8. List all characters inventory by value.");
      System.out.println("9. Quit.");
      System.out.print("What would you like to do? ");
      String choice = in.next();
  

      
      // choice 1
      if (choice.equals("1")) {
        System.out.println("What is the character's name?");
        String name = in.next();

// duplicate name checker
        boolean dupeName = false;
        for (Character c : characters) {
          if (c.getName().equals(name)) {
            System.out.println("Error: A character with that name already exists.");
            dupeName = true;
            break;
          } // end of if
          
        } // end of for loop
        // end of duplicate name check

        if (!dupeName) {
          System.out.println("How many credits?");
          int credits = in.nextInt();

          ArrayList<Item> items = new ArrayList<>();


          Character c1 = new Character(name, credits, items);
          characters.add(c1);

          System.out.println(name + " added!");
          
        } 


           // choice 2
      } else if (choice.equals("2")) {
// found character checker
        boolean foundCharacter = false;

        System.out.println("Which character are you adding an item to? ");
        String name = in.next();


        for (Character c : characters) {
          if (c.getName().equals(name)) {
            foundCharacter = true;
           
        System.out.println("What is the name of the item? ");
        String itemName = in.next();

        System.out.println("What is the item's value? ");
        int itemValue = in.nextInt();

        c.addItem(itemName, itemValue);
            
        System.out.println(name + " has aquired " + itemName);
            
            break;
          } // end of if character
        } // end of character
        if (!foundCharacter) {
          System.out.println("Error: Character not found.");
        } // end of error
        
    // choice 3
      } else if (choice.equals("3")) {
        System.out.println("Which character would you like to have drop an item?");
        String name = in.next();

        boolean foundCharacter = false;
        for (Character c : characters) {
          if (c.getName().equals(name)) {
            foundCharacter = true;

            System.out.println("What item would you like to have the character drop?");
            String itemName = in.next();
// checker to see if you can drop item
            boolean dropped = c.dropItem(itemName);
            if (dropped) {
              System.out.println(name + " has dropped " + itemName);
            } else {
              System.out.println(name + " could not drop " + itemName);
            }

            break;
          } // end of if character
        } // end of character
        if (!foundCharacter) {
          System.out.println("Error: Character not found.");
        } // end of error
            // choice 4
      } else if(choice.equals("4")){


   System.out.println("Which character would you like to have sell an item to a vendor?");
        String name = in.next();

        boolean foundCharacter = false;
        for (Character c : characters) {
          if (c.getName().equals(name)) {
            foundCharacter = true;
            

            System.out.println("What item would you like to have the character sell to a vendor?");
            String itemName = in.next();

            boolean sold = c.sellItemToVendor(itemName);
            if (sold) {
              
              System.out.println(name + " has sold " + itemName + " to vendor");
              
            } else {
              System.out.println(name + " could not sell " + itemName + "  to vendor");
            }

            break;
          } // end of if character
        } // end of character
        if (!foundCharacter) {
          System.out.println("Error: Character not found.");
        } // end of error

            // choice 5
            }else if(choice.equals("5")){


   System.out.println("Which character would you like to have sell an item to another character?");
        String name = in.next();

        boolean foundCharacter = false;
        for (Character c : characters) {
          if (c.getName().equals(name)) {
            foundCharacter = true;
            

            System.out.println("What item would you like to have the character sell to another character?");
            String itemName = in.next();

            boolean sold = c.sellToCharacter(itemName);
            if (sold) {
              
              System.out.println(name + " has sold " + itemName + " to another character");
              
            } else {
              System.out.println(name + " could not sell " + itemName + "  to another character");
            }

            break;
          } // end of if character
        } // end of character
        if (!foundCharacter) {
          System.out.println("Error: Character not found.");
        } // end of error


            // choice 6
            }else if(choice.equals("6")){
        
        for(int i = 0; i < characters.size(); i++) {   
    System.out.println(characters.get(i));
}  
System.out.println("");


    // choice 7
      }else if (choice.equals("7")) {
                System.out.println("Which character would you like to list items?");
                String name = in.next();

                boolean foundCharacter = false;
                for (Character c : characters) {
                    if (c.getName().equals(name)) {
                        foundCharacter = true;
                        c.printItemsInOrder();
                        break;
                    }
                }
                
                if (!foundCharacter) {
                    System.out.println("Error: Character not found.");
                }
            // choice 8
      
          }else if(choice.equals("8")){

           boolean foundCharacter = false;
           for(Character c: characters){
            foundCharacter = true;
            System.out.println(c);
            c.printItemsInOrder();
            System.out.println();
           }
            
           
         
    // choice 9
      }else if (choice.equals("9")) {
        System.out.println("Goodbye...");
        break;
      }// end of final choice

// checker in case they type in a command that was not listed
      if(choice.equals("10")){
        System.out.println("Please enter in a command that was listed");
      }
        
    
  }// end of while loop
  }// end of main method
}// end of main class
  
