package pl.aaugustyniak.sequencing.alg;


/**
 * @author artur
 */
interface SimpleQueue<T> extends Iterable<T> {

    void enqueue(T item);

    T dequeue();

    boolean isEmpty();

    int size();

}