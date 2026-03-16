public class Process {

    public String pid;
    public int arrivalTime;
    public int burstTime;
    public int remainingTime;
    public int waitTime;
    public int completionTime;
    public int priority;

    Process(String pid, int arrivalTime, int burstTime, int priority)
    {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.waitTime = 0;
        this.priority = priority;
    }
}
