package pl.aaugustyniak.sequencing.alg;

import java.util.Random;


/**
 * @param <T>
 * @author artur
 */
public abstract class BaseSorter<T extends Comparable<T>> implements VerboseSorter<T> {

    protected Long microtime;
    protected Integer arrayCost = 0;

    /**
     * Sort algorithm
     *
     * @param arr
     * @return
     */
    @Override
    public abstract T[] sort(T[] arr);
//    {
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr.length; j++) {
//                arrayCost++;
//                if (less(arr[i], arr[j])) {
//                    swap(arr, i, j);
//                }
//            }
//        }
//        long stopTime = System.currentTimeMillis();
//        this.microtime = stopTime - startTime;
//        return arr;
//    }

    @Override
    public boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    @Override
    public void swap(T[] a, Object idx, Object withIdx) {
        int i = (int) idx;
        int j = (int) withIdx;
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    @Override
    public boolean isSorted(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public void shuffle(T[] arr) {
        Random r = new Random();

        for (int i = 0; i < arr.length; i++) {
            int rIdx = r.nextInt(i + 1);
            swap(arr, i, rIdx);
        }

    }

    @Override
    public long getMicrotime() {
        return microtime;
    }

    @Override
    public Integer getCost() {
        return arrayCost;
    }

}
