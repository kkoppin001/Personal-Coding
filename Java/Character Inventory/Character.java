import java.util.*;

public class Character
{

    private String name;
    private int credits;
    private ArrayList<Item> items;

    public Character(String name, int credits, ArrayList<Item> items){
        this.name = name;
        this.credits = credits;
        this.items = items;

     
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getCredits() {
        return this.credits;
    }

    public boolean dropItem(String itemName) {

        for (Item item : this.items) {
            if (item.getItemName().equals(itemName)) {
                this.items.remove(item);
                return true;
            }
        }
        return false;

    }

    public void setCredits(int newCredits) {
        this.credits = newCredits;
    }

    public String toString() {
        return this.name + " " + credits;
    }


  public void addItem(String itemName, int itemValue){
    Item i = new Item(itemName, itemValue);
    this.items.add(i);
  }

public boolean sellItemToVendor(String itemName) {
         
      for (Item item : this.items) {
            if (item.getItemName().equals(itemName)) {
                this.items.remove(item);

              this.credits += item.getValue();
                return true;
            }
        }
        return false;
}

  public boolean sellToCharacter(String itemName){

    for (Item item : this.items) {
            if (item.getItemName().equals(itemName)) {
                this.items.remove(item);

              this.credits += item.getValue();
                return true;
            }
        }
        return false;
    
  }
  public void printItemsInOrder() {
        Collections.sort(this.items);
        for (Item i : this.items) {
            System.out.println(i);
      }
    }

  
    
  }

