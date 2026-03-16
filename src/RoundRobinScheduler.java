import java.util.*;

public class RoundRobinScheduler {

    public List<GanttEntry> gantt = new ArrayList<>();
    int quanta;
    List<Process> processList;
    int currentTime = 0;
    LinkedList<Process> readyQueue = new LinkedList<>();

    RoundRobinScheduler(int quanta, List<Process> processList) {
        this.quanta = quanta;
        this.processList = processList;
    }

    void checkNewArrivals() {
        Iterator<Process> it = processList.iterator();

        while (it.hasNext()) {
            Process p = it.next();

            if (p.arrivalTime <= currentTime) {
                readyQueue.add(p);
                it.remove();
            }
        }
    }

    void runRoundRobin() {

        processList.sort(
                Comparator.comparingInt((Process p) -> p.arrivalTime)
                        .thenComparing(p -> p.pid)
        );

        int totalProcesses = processList.size();
        int completedCount = 0;

        while (completedCount < totalProcesses) {

            checkNewArrivals();

            if (!readyQueue.isEmpty()) {

                Process current = readyQueue.poll();

                int start = currentTime;

                int timeWorked = Math.min(current.remainingTime, quanta);

                current.remainingTime -= timeWorked;

                currentTime += timeWorked;

                int end = currentTime;

                gantt.add(new GanttEntry(current.pid, start, end));

                current.remainingTime -= timeWorked;
                currentTime += timeWorked;

                for (Process waitingProcess : readyQueue) {
                    waitingProcess.waitTime += timeWorked;
                }

                checkNewArrivals();

                if (current.remainingTime <= 0) {
                    completedCount++;
                    current.completionTime = currentTime;
                } else {
                    readyQueue.add(current);
                }

            } else {
                currentTime++;
            }
        }
    }
}