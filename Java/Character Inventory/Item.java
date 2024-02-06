public class Item implements Comparable<Item> {

    private String name;
    private int value;

    public Item(String n, int c){
        this.name = n;
        this.value = c;
    }

    public String getItemName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return this.name + " (" + this.value + ")";
    }

    public int compareTo(Item other) {
        if (this.value > other.getValue()) {
            return -1;
        } else if (this.value < other.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }


  

}
