class Train extends Event {
    private double duration;
    private double startTime;
    boolean isBlocking;

    public Train(double startTime, double duration) {
        this.startTime = startTime;
        this.duration = duration;
        isBlocking = true;
        this.setTime(startTime);
    }

    //GETTERS
    public double getEndTime() {
        return startTime + duration;
    }

    public double getStartTime() {
        return startTime;
    }

    public boolean getIsBlocking(){
        return isBlocking;
     }

     //SETTER
    public void setNotBlocking(){
        isBlocking = false;
        super.setTime(this.duration);
    }
}
