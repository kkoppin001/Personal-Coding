class Truck extends Event {
   private int truckID;
   private double startTime;
   private String event;
   
   public Truck(double startTime, int truckID, String event){
    this.startTime = startTime;
    this.truckID = truckID;
    this.event = event;
    this.setTime(startTime);
   }

   // GETTERS
   public String getEvent(){
    return event;
   }

   public int getTruckID(){
    return truckID;
   }

   public double getStartTime(){
    return startTime;
   }

   public double getTotalTime(){
    return this.getTime() - startTime;
   }

   //SETTERS
   public void setEvent(String event){
    this.event = event;
   }

   public void setTruckID(int truckID){
    this.truckID = truckID;
   }

   public void setStartTime(double startTime){
    this.startTime = startTime;
   }

   @Override
   public String toString(){
    return "TRUCK #" + truckID + " " + event;
   }

 
}