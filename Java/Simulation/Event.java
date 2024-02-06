public class Event implements Comparable<Event>{

    protected double time;

    // compare to
    @Override
    public int compareTo(Event other) {
        return Double.compare(this.time, other.time);
    }
//getter
    public double getTime() {
        return time;
    }
//setter
    public void setTime(double time){
        this.time = time;
    }


    
}
