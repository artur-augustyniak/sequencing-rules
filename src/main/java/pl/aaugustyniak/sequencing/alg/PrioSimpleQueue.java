package pl.aaugustyniak.sequencing.alg;


/**
 * @param <T>
 * @author aaugustyniak
 */
public interface PrioSimpleQueue<T extends Comparable> extends SimpleQueue<T> {

    /**
     * Get max item from queue total order
     *
     * @return T
     */
    T dequeueMax();

    /**
     * Get m item from queue total order
     *
     * @return T
     */
    T dequeueMin();

}
