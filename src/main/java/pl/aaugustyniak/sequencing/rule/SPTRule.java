package pl.aaugustyniak.sequencing.rule;

import pl.aaugustyniak.sequencing.alg.BaseSorter;
import pl.aaugustyniak.sequencing.alg.PrioArrayHeapQueue;
import pl.aaugustyniak.sequencing.alg.PrioSimpleQueue;
import pl.aaugustyniak.sequencing.alg.QuickSorter;
import pl.aaugustyniak.sequencing.model.Job;
import pl.aaugustyniak.sequencing.model.Worker;

import java.util.Iterator;
import java.util.Random;


public class SPTRule {


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


        for (int i = 0; i < numberOfMachines; i++) {
            workers.enqueue(new Worker());
        }

        BaseSorter<Job> sorter = new QuickSorter<>();
        jobs = sorter.sort(jobs);
        int j = numberOfJobs - 1;
        for (Iterator it = workers.iterator(); it.hasNext(); ) {
            Worker w = (Worker) it.next();
            w.addJob(jobs[j--]);
        }

        for (int k = 0; k <= j; k++) {
            Worker leastBusy = workers.dequeueMin();
            leastBusy.addJob(jobs[k]);
            workers.enqueue(leastBusy);
        }


        System.out.println("Workers in least busy order SPT:");
        System.out.println("----------------------------");
        while (!workers.isEmpty()) {
            System.out.println(workers.dequeueMin());
            System.out.println("----------------------------");
        }


    }

}
