package pl.aaugustyniak.sequencing.alg;

/**
 * @param <T>
 * @author artur
 */
public interface Sorter<T extends Comparable<T>> {

    //compareTo() callback on objects
    T[] sort(T[] arr);

}
