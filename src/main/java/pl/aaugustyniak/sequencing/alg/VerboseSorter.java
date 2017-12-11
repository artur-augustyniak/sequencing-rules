package pl.aaugustyniak.sequencing.alg;

/**
 * @param <T>
 * @author artur
 */
public interface VerboseSorter<T extends Comparable<T>> extends Sorter<T> {

    boolean less(T v, T w);

    void swap(T[] a, Object idx, Object withIdx);

    boolean isSorted(T[] arr);

    long getMicrotime();

    Integer getCost();
}
