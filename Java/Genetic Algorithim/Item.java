public class Item {

    // private variable declaration
    private final String name;
    private final double weight;
    private final int value;
    private boolean included;

    // constructor, initial daya
    public Item(String name, double weight, int value) {

        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    // copy constructor
    public Item(Item other) {

        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
        this.included = other.included;

    }

    // getters

    public double getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    // included methods
    public boolean isIncluded() {
        return included;
    }

    public void setIncluded(boolean included) {

        this.included = included;

    }

    // to string method
    public String toString() {

        return name + " (" + weight + " lbs, $" + value + ")";
    }

}
