import java.util.*;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {
    private static Random rng = new Random();

    // empty constructor
    public Chromosome() {

    }

    // random items per chromosome
    public Chromosome(ArrayList<Item> items) {

        for (Item currentItem : items) {

            int randNum = (rng.nextInt(11));

            if (randNum < 6) {
                currentItem.setIncluded(false);
            } else {

                currentItem.setIncluded(true);
            }

            this.add(new Item(currentItem));

        }
    }

    // crossover method for child
    public Chromosome crossover(Chromosome other) {

        Chromosome child = new Chromosome();

        for (int i = 0; i < this.size(); i++) {
            int randNum = (rng.nextInt(11));

            if (randNum <= 5) {
                child.add(this.get(i));
            } else if (randNum > 5) {
                child.add(other.get(i));
            }

        }

        return child;

    }

    // randomly potentially mutates a chromosome
    public void mutate(ArrayList<Chromosome> nextGen) {

        for (Item i : this) {
            int randNum = (rng.nextInt(10));

            if (randNum == 1 && i.isIncluded() == true) {
                i.setIncluded(false);

            } else if (randNum == 1 && i.isIncluded() == false) {
                i.setIncluded(true);
            }

        }

    }

    // get fitness method
    public int getFitness() {

        int fit = 0;
        double totalWeight = 0;
        int totalValue = 0;

        for (Item i : this) {

            if (i.isIncluded() == true) {

                totalWeight += i.getWeight();
            }
        }

        if (totalWeight > 10) {
            fit = 0;
        } else if (totalWeight <= 10) {

            for (Item i : this) {
                if (i.isIncluded() == true) {
                    totalWeight += i.getValue();
                }
                fit = totalValue;
            }
        }
        return fit;
    }

    // compare to method for the fittest chromosome
    public int compareTo(Chromosome other) {

        if (this.getFitness() > other.getFitness()) {
            return -1;
        } else if (this.getFitness() < other.getFitness()) {
            return 1;
        } else {
            return 0;
        }

    }

    public String toString() {

        String returnString = "";
        for (Item i : this) {
            if (i.isIncluded() == true) {
                returnString += i.toString() + " ";
            }

        }
        this.getFitness();

        return returnString;
    }

}
