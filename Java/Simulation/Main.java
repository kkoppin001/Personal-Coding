import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //lists and queues
        PriorityQueue<Event> pq = new PriorityQueue<>();
        ArrayList<Truck> allTrucks = new ArrayList<Truck>();
        ArrayList<Truck> waitList = new ArrayList<Truck>();
        ArrayList<Train> allTrains = new ArrayList<Train>();

        // truck declaration
        double truckSpeed = 30.0;
        double truckStartDelay = 15.0;
        int truckCapacity = 10;
        double simulationTime = 0.0;
        int TOTAL_PACKAGES = 1500;
        int distanceToTrainCrossing = 3000;
        int distanceLeft = 270000;

        //drone declaration
        double percentByDrone = 0.25;
        int droneSpeed = 500;
        int droneDelay = 3;
        double individualDroneTime = (distanceToTrainCrossing + distanceLeft) / droneSpeed;


        // How many drones and trucks will be needed
        int NUMBER_OF_DRONES = (int) (TOTAL_PACKAGES * percentByDrone);
        int NUMBER_OF_TRUCKS = (int) (TOTAL_PACKAGES - NUMBER_OF_DRONES) / 10;
        double totalDroneTime = ((NUMBER_OF_DRONES * droneDelay) + (individualDroneTime)) - droneDelay;

        if (NUMBER_OF_TRUCKS % 10 != 0) {
            NUMBER_OF_TRUCKS++;
        }

//other declarations
        double[] totalTripTime = new double[NUMBER_OF_TRUCKS];
        boolean isTrain = false;

        System.out.println("With " + (percentByDrone * 100) + "% of drones and " + TOTAL_PACKAGES + " packages,\nThere will be:\n- " + NUMBER_OF_DRONES + " drones\n- " + NUMBER_OF_TRUCKS + " trucks");

        // Reading train schedule text file
        try{
            BufferedReader br = new BufferedReader(new FileReader("trainSchedule.txt"));
            String line;
            Train train;
            while((line = br.readLine()) != null){
                String[] parts = line.split(" ");
                int startTime = Integer.parseInt(parts[0]);
                int duration = Integer.parseInt(parts[1]);
                train = new Train(startTime, duration);
                allTrains.add(train);
                pq.offer(train);
            }
// printout train schedule
            System.out.println(" ");
            System.out.println("TRAIN SCHEDULE");
            System.out.println("---------------");

            br = new BufferedReader(new FileReader("trainSchedule.txt"));
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
            System.out.println(" ");
                
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("An Error Occurred...");
        }

// initialize trucks
       double startTime;
       Truck trucks;
       for(int numTruck = 0; numTruck < NUMBER_OF_TRUCKS; numTruck ++){
        startTime =  (double) numTruck * truckStartDelay;
        trucks = new Truck(startTime, numTruck, "begin");
        allTrucks.add(trucks);
        pq.offer(trucks);
       }

        // Main simulation loop

        Event event;

        while(!pq.isEmpty()){
            event = pq.poll();

            if (event instanceof Train) { // handle trains
                Train trainEvent = (Train) event;
// train arriving at crossing
                if (trainEvent.getIsBlocking()) {
                    System.out.println(trainEvent.getTime() + ": TRAIN ARRIVES AT CROSSING");
                    isTrain = true;
                    trainEvent.setNotBlocking();
                    pq.offer(trainEvent); 

                    // train leaves the crossing
                } else {
                    System.out.println(trainEvent.getEndTime() + ": TRAIN LEAVES CROSSING");
                    isTrain = false;
                    if(!waitList.isEmpty()){
                        updatingTruckInfo(pq, waitList.get(0), trainEvent.getTime() + 1, "crosses crossing");

                    }
                }
            } else { // handle trucks
                Truck truckEvent = (Truck) event;

                //trucks begin
                if(truckEvent.getEvent().equals("begin")){
                    System.out.println(truckEvent.getTime() + ": TRUCK #" + truckEvent.getTruckID() + " begins journey");
                   double newTime = distanceToTrainCrossing / truckSpeed;
                    updatingTruckInfo(pq, truckEvent, newTime + truckEvent.getTime(), "arrive at crossing");
//trucks arrive at crossing
                } else if(truckEvent.getEvent().equals("arrive at crossing")){
                    System.out.println(truckEvent.getTime() + ": TRUCK #" + truckEvent.getTruckID() + " arrives at crossing");
                    
                    if(isTrain == true || !waitList.isEmpty()){
                        waitList.add(truckEvent);
                    } else{
                        updatingTruckInfo(pq, truckEvent, truckEvent.getTime(), "crossed crossing");
                    }
//truck crosses crossing
                } else if(truckEvent.getEvent().equals("crossed crossing")){
                    System.out.println(truckEvent.getTime() + ": TRUCK #" + truckEvent.getTruckID() + " crosses crossing");

                    double currentTime = distanceLeft / truckSpeed;
                    double newCurrentTime = truckEvent.getTime();

                    updatingTruckInfo(pq, truckEvent, truckEvent.getTime() + currentTime, "done");

                    if(!waitList.isEmpty()){
                        waitList.remove(0);
                    }

                    if(!waitList.isEmpty()){
                        updatingTruckInfo(pq, truckEvent, newCurrentTime + 1, "done");
                    }
                    
// truck finishes journey
                } else if(truckEvent.getEvent().equals("done")){
                    System.out.println(truckEvent.getTime() + ": TRUCK #" + truckEvent.getTruckID() + " finished");

                     totalTripTime[truckEvent.getTruckID()] += truckEvent.getTime();


                }
            }
        
        }
// print out stats
        System.out.println(" ");
        System.out.println("STATS");
        System.out.println("-----");
        for (int i = 0; i < NUMBER_OF_TRUCKS; i++) {
            System.out.printf("TRUCK #%d total trip time: %.1f minutes\n", i, totalTripTime[i]);
        }

        // calculate average and total trip time
double totalTripTimeSum = 0.0;
double maxTotalTripTime = 0.0;

for (int i = 0; i < NUMBER_OF_TRUCKS; i++) {
    totalTripTimeSum += totalTripTime[i];
    if (totalTripTime[i] > maxTotalTripTime) {
        maxTotalTripTime = totalTripTime[i];
    }
}

double averageTripTime = totalTripTimeSum / NUMBER_OF_TRUCKS;

// Print TRUCK AVG TRIP TIME and TRUCK TOTAL TIME
System.out.println(" ");
System.out.println("TRUCK AVG TRIP TIME: " + averageTripTime + " minutes");
System.out.println("TRUCK MAX TOTAL TIME: " + maxTotalTripTime + " minutes");

// drone calculations
double totalDroneTripTime = individualDroneTime * NUMBER_OF_DRONES;
System.out.println(" ");
System.out.println("DRONE TRIP TIME: " + individualDroneTime + " minutes");
System.out.println("DRONE TOTAL TIME: " + totalDroneTripTime + " minutes");

// total time calculations
double totalTime = Arrays.stream(totalTripTime).sum();
System.out.println(" ");
System.out.println("TOTAL TIME: " + totalTime + " minutes");
System.out.println("BUILD SUCCESSFUL (total time: 0 seconds)");


    }
    public static void updatingTruckInfo(PriorityQueue<Event> pq, Truck truck, double setTime, String event) {
        truck.setTime(setTime);
        truck.setEvent(event);
        pq.offer(truck);
    }
   
    }


