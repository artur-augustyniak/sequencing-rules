package pl.aaugustyniak.sequencing.model;

public class Job implements Comparable<Job> {

    public final String name;
    public final Integer cost;

    public Job(String name, Integer cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public int compareTo(Job that) {
        if (this.cost > that.cost) {
            return 1;
        } else if (this.cost < that.cost) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "\nJob{" + "id=" + name + ", cost=" + cost + "}\n";
    }

}
