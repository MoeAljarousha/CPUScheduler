import java.util.*;

public class SJFScheduler {

    public List<GanttEntry> gantt = new ArrayList<>();
    List<Process> processList;
    int currentTime = 0;
    LinkedList<Process> readyQueue = new LinkedList<>();

    SJFScheduler(List<Process> processList) {
        this.processList = processList;
    }

    void checkNewArrivals() {
        Iterator<Process> it = processList.iterator();

        while (it.hasNext()) {
            Process p = it.next();

            if (p.arrivalTime <= currentTime) {
                readyQueue.add(p);

                System.out.println(
                        "Time " + currentTime + ": " + p.pid +
                                " arrived and entered the Ready Queue."
                );

                it.remove();
            }
        }
    }

    void runSJF() {

        processList.sort(
                Comparator.comparingInt((Process p) -> p.arrivalTime)
                        .thenComparingInt(p -> Integer.parseInt(p.pid.substring(1)))
        );

        int totalProcesses = processList.size();
        int completedCount = 0;

        System.out.println("Starting SJF Scheduler\n");

        while (completedCount < totalProcesses) {

            checkNewArrivals();

            if (!readyQueue.isEmpty()) {

                readyQueue.sort(
                        Comparator.comparingInt((Process p) -> p.burstTime)
                                .thenComparingInt(p -> p.arrivalTime)
                                .thenComparingInt(p -> Integer.parseInt(p.pid.substring(1)))
                );

                Process current = readyQueue.removeFirst();

                int start = currentTime;

                int timeWorked = current.burstTime;

                current.remainingTime = 0;

                currentTime += timeWorked;

                int end = currentTime;

                gantt.add(new GanttEntry(current.pid, start, end));

                System.out.println(
                        "Time " + currentTime + ": Running " +
                                current.pid + " for " + timeWorked + " units."
                );

                current.remainingTime = 0;
                currentTime += timeWorked;

                for (Process waitingProcess : readyQueue) {
                    waitingProcess.waitTime += timeWorked;
                }

                current.completionTime = currentTime;
                completedCount++;

                System.out.println(
                        "Time " + currentTime + ": " + current.pid + " FINISHED."
                );
            } else {
                currentTime++;
            }
        }
    }
}