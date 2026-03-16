import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Usage: java Main input.json output.json");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        ObjectMapper mapper = new ObjectMapper();

        // Read input JSON
        InputData input = mapper.readValue(
                new File(inputFile),
                InputData.class
        );

        List<Process> processes = new ArrayList<>();

        for (Job j : input.jobs) {

            processes.add(new Process(
                    j.pid,
                    j.arrival,
                    j.burst,
                    j.priority
            ));
        }

        List<GanttEntry> gantt = new ArrayList<>();

        // Run scheduler
        switch (input.policy) {

            case "FIFO": {

                FIFOScheduler scheduler = new FIFOScheduler(processes);
                scheduler.runFIFO();
                gantt = scheduler.gantt;

                break;
            }

            case "SJF": {

                SJFScheduler scheduler = new SJFScheduler(processes);
                scheduler.runSJF();
                gantt = scheduler.gantt;

                break;
            }

            case "PRIORITY": {

                PriorityScheduler scheduler = new PriorityScheduler(processes);
                scheduler.runPriority();
                gantt = scheduler.gantt;

                break;
            }

            case "RR": {

                RoundRobinScheduler scheduler =
                        new RoundRobinScheduler(input.quantum, processes);

                scheduler.runRoundRobin();
                gantt = scheduler.gantt;

                break;
            }

            default:
                System.out.println("Unknown scheduling policy: " + input.policy);
                return;
        }

        // Compute metrics
        Map<String,Integer> turnaround = new HashMap<>();
        Map<String,Integer> waiting = new HashMap<>();

        double totalTurnaround = 0;
        double totalWaiting = 0;

        for (Process p : processes) {

            int tat = p.completionTime - p.arrivalTime;

            turnaround.put(p.pid, tat);
            waiting.put(p.pid, p.waitTime);

            totalTurnaround += tat;
            totalWaiting += p.waitTime;
        }

        Metrics metrics = new Metrics();

        metrics.turnaround = turnaround;
        metrics.waiting = waiting;
        metrics.avg_turnaround = totalTurnaround / processes.size();
        metrics.avg_waiting = totalWaiting / processes.size();

        OutputData output = new OutputData();

        output.policy = input.policy;
        output.gantt = gantt;
        output.metrics = metrics;

        // Write output JSON
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(outputFile), output);

        System.out.println("Output written to: " + outputFile);
    }
}