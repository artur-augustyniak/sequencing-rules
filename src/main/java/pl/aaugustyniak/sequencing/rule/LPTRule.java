package pl.aaugustyniak.sequencing.rule;

import pl.aaugustyniak.sequencing.alg.BaseSorter;
import pl.aaugustyniak.sequencing.alg.PrioArrayHeapQueue;
import pl.aaugustyniak.sequencing.alg.PrioSimpleQueue;
import pl.aaugustyniak.sequencing.alg.QuickSorter;
import pl.aaugustyniak.sequencing.model.Job;
import pl.aaugustyniak.sequencing.model.Worker;

import java.util.Random;


public class LPTRule {


    public void exec() {
        Random r = new Random(42);

        int numberOfMachines = 10;
        int numberOfJobs = 20;
        Job[] jobs = new Job[numberOfJobs];


        PrioSimpleQueue<Worker> workers = new PrioArrayHeapQueue<>();

        System.out.println("Jobs count: " + numberOfJobs);
        System.out.println("----------------------------");
        for (int i = 0; i < numberOfJobs; i++) {
            String name = Integer.toString(i);
            int cost = r.nextInt(100);
            jobs[i] = new Job(name, cost);
            System.out.println(jobs[i]);
        }

//        BaseSorter<Job> sorter = new QuickSorter<>();
//        jobs = sorter.sort(jobs);

        for (int i = 0; i < numberOfMachines; i++) {
            workers.enqueue(new Worker());
        }

        for (int j = numberOfJobs - 1; j >= 0; j--) {
            Worker leastBusy = workers.dequeueMin();
            leastBusy.addJob(jobs[j]);
            workers.enqueue(leastBusy);
        }

        System.out.println("Workers in least busy order LPT:");
        System.out.println("----------------------------");
        while (!workers.isEmpty()) {
            System.out.println(workers.dequeueMin());
            System.out.println("----------------------------");
        }
    }


}
