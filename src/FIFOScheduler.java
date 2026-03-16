import java.util.*;

public class FIFOScheduler {

    List<Process> processList;
    int currentTime = 0;
    LinkedList<Process> readyQueue = new LinkedList<>();

    public List<GanttEntry> gantt = new ArrayList<>();

    FIFOScheduler(List<Process> processList) {
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

    void runFIFO() {

        processList.sort(
                Comparator.comparingInt((Process p) -> p.arrivalTime)
                        .thenComparingInt(p -> Integer.parseInt(p.pid.substring(1)))
        );

        int totalProcesses = processList.size();
        int completedCount = 0;

        while (completedCount < totalProcesses) {

            checkNewArrivals();

            if (!readyQueue.isEmpty()) {

                Process current = readyQueue.removeFirst();

                int start = currentTime;

                int timeWorked = current.remainingTime;

                current.remainingTime = 0;

                currentTime += timeWorked;

                int end = currentTime;

                gantt.add(new GanttEntry(current.pid, start, end));

                for (Process waitingProcess : readyQueue) {
                    waitingProcess.waitTime += timeWorked;
                }

                current.completionTime = currentTime;

                completedCount++;

            }
            else {
                currentTime++;
            }
        }
    }
}