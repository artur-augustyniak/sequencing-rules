package pl.aaugustyniak.sequencing.alg;

/**
 * @param <T>
 * @author aaugustyniak
 */
public class QuickSorter<T extends Comparable<T>> extends BaseSorter<T> implements VerboseSorter<T> {

    private static final int INSERTION_CUTOFF = 15;
    protected T[] arr;

    @Override
    public T[] sort(T[] arr) {
        this.arr = arr;
        shuffle(arr);
        quickSort(0, arr.length - 1);
        return arr;
    }

    private void quickSort(int lo, int hi) {
        if (hi <= lo + INSERTION_CUTOFF - 1) {
            insertionSortFor(lo, hi, arr);
            return;
        }

        int median = medianOf3(lo, lo + (hi - lo) / 2, hi);
        if (median != lo) {
            swap(arr, lo, median);
        }
        int j = arrPartition(lo, hi);
        quickSort(lo, j - 1);
        quickSort(j + 1, hi);
        assert isSorted(arr, lo, hi);

    }

    public int arrPartition(int lo, int hi) {
        int i = lo;
        T loArr = arr[lo];
        int j = hi + 1;
        while (true) {
            while (less(arr[++i], loArr)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(loArr, arr[--j])) {
            }
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, lo, j);
        return j;
    }

    private void insertionSortFor(int start, int stop, T[] a) {
        for (int i = start; i <= stop; i++) {
            for (int j = i; j > start; j--) {
                if (less(a[j], a[j - 1])) {
                    swap(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    private boolean isSorted(T[] halvesArr, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            if (less(halvesArr[i], halvesArr[i - 1])) {
                return false;
            }
        }
        return true;
    }

    protected int medianOf3(int i, int j, int k) {
        return (less(arr[i], arr[j])
                ? (less(arr[j], arr[k]) ? j : less(arr[i], arr[k]) ? k : i)
                : (less(arr[k], arr[j]) ? j : less(arr[k], arr[i]) ? k : i));
    }
}
