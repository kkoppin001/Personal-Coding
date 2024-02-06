/*
 * kalli koppin
 *
 * Basic Genetic Algorithim
 * CS 1181
 * Friday, September 29
 */


import java.util.*;
import java.io.*;

public class GeneticAlgorithim {

    public static void main(String[] args) throws Exception {

        // for loop to copy 20 times
        for (int i = 0; i < 20; i++) {

            // variable declaration
            String fileName = "Items";

            ArrayList<Item> items = readData(fileName);
            ArrayList<Chromosome> currentPop = initialPopulation(items, 10);
            ArrayList<Chromosome> nextGen = new ArrayList<>();

            Collections.shuffle(currentPop);

            for (int l = 0; l < 2; l++) {

                for (int j = 0; j < currentPop.size(); j += 2) {

                    if (currentPop.get(j + 1) != null && currentPop.get(j) != null) {
                        nextGen.add(currentPop.get(j).crossover(currentPop.get(j + 1)));

                    } // end of if statement

                } // for loop to pair chromosomes

            } // end of for loop to make sure there are 10 new children

            Collections.shuffle(currentPop);

            // tenpercent for mutation

            int totalCount = nextGen.size();

            int tenPercent = (int) (0.1 * totalCount);

            for (int k = 0; k < tenPercent; k++) {
                nextGen.get(k).mutate(nextGen);

            }

            Collections.sort(nextGen);

            // get fitness and add to current population and then start over

            for (Chromosome fitness : nextGen) {

                Integer fit = 0;

                for (Integer y = 0; y < nextGen.size(); y++) {
                    nextGen.get(y).getFitness();

                    if (fitness.getFitness() < 10 && fitness.getFitness() > 0) {

                        currentPop.clear();

                        currentPop.add(nextGen.get(y));

                    }

                }

                // output statements

                System.out.println("The fittest Chromosome contains: " + currentPop);

                System.out.println("This chromosome's fitness is: " + fitness);

            }
        }

    } // main method end

    // read data method from file

    public static ArrayList<Item> readData(String fileName) {

        ArrayList<Item> items = new ArrayList<Item>();

        try {

            Scanner in = new Scanner(new File(fileName)).useDelimiter(", |\\R");

            while (in.hasNext()) {

                String line = in.nextLine();
                String[] parts = line.split(", ");

                String name = parts[0];
                double weight = Double.parseDouble(parts[1]);
                int value = Integer.parseInt(parts[2]);

                Item i = new Item(line, weight, value);

                items.add(i);

            } // end of while loop

            in.close();

        } catch (FileNotFoundException e) {
            System.out.print("Error: File not found");
            System.exit(-1);

        } // end of try-catch

        return items;

    }// end of read data

    // initial population method
    public static ArrayList<Chromosome> initialPopulation(ArrayList<Item> items, int populationSize) {

        ArrayList<Chromosome> chromes = new ArrayList<Chromosome>();

        for (int i = 0; i < populationSize; i++) {
            Chromosome c = new Chromosome(items);
            chromes.add(c);
        } // end of adding chromosomes

        return chromes;
    }// end of initial population

}// end of GeneticAlgorithim