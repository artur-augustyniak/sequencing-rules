package pl.aaugustyniak.sequencing.alg;

import java.util.Iterator;


/**
 *
 * @author aaugustyniak
 * @param <T>
 */
public class PrioArrayHeapQueue<T extends Comparable<T>> implements PrioSimpleQueue<T> {

    private T[] heap;
    private int count = 0;

    public PrioArrayHeapQueue() {
        heap = (T[]) new Comparable[1];

    }

    private void resize(int max) {
        //  assert max >= numberOfElements;
        T[] temp = (T[]) new Comparable[max];
        for (int i = 1; i <= count; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }

    private void checkHalving() {
        if (count > 0 && count == heap.length - 1 / 4) {
            resize(heap.length / 2);
        }
    }

    @Override
    public T dequeueMax() {

        return dequeue();
    }

    /**
     * Sprawdzić tylko indeksy ostatniego poziomu
     *
     * @return
     */
    @Override
    public T dequeueMin() {

        int min = 1;
        for (int i = count ; i >= 1; i--) {
            if (less(i, min)) {
                min = i;
            }
        }
        T ret = heap[min];
        swap(min, count);
//        heapInvariantDownFrom(1);
//        heapInvariantUpFrom(count);
        count--;
        heap[count + 1] = null;
        checkHalving();
        //assert isMaxHeap(1);
        return ret;
    }

    // is subtree of pq[1..N] rooted at k a min heap?
    private boolean isMaxHeap(int k) {
        if (k > count) {
            return true;
        }
        int left = 2 * k, right = 2 * k + 1;
        if (left <= count && less(left, k)) {
            return true;
        }
        if (right <= count && less(right, k)) {
            return true;
        }
        return isMaxHeap(left) && isMaxHeap(right);
    }

    @Override
    public void enqueue(T item) {
        if (count == heap.length - 1) {
            resize(2 * heap.length);
        }
        heap[++count] = item;
        heapInvariantUpFrom(count);
    }

    @Override
    public T dequeue() {

        T max = heap[1];
        swap(1, count--);
        heapInvariantDownFrom(1);
        heap[count + 1] = null;
        checkHalving();
        //assert isMaxHeap(1);
        return max;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int itPointer = 1;

            @Override
            public boolean hasNext() {
                return itPointer <= count;
            }

            @Override
            public T next() {
                return heap[itPointer++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported.");
            }
        };
    }

    /**
     * a.k.a. Swim
     *
     * @param heapIdx
     */
    private void heapInvariantUpFrom(int heapIdx) {
        while (heapIdx > 1 && less(heapIdx / 2, heapIdx)) {
            swap(heapIdx, heapIdx / 2);
            heapIdx = heapIdx / 2;
        }
    }

    /**
     * a.k.a. Sink
     *
     * @param heapIdx
     */
    private void heapInvariantDownFrom(int heapIdx) {
        /**
         * iterate over left child
         */
        while (2 * heapIdx <= count) {
            int currentChild = 2 * heapIdx;
            if (currentChild < count && less(currentChild, currentChild + 1)) {
                currentChild++;
            }
            if (!less(heapIdx, currentChild)) {
                break;
            }
            swap(heapIdx, currentChild);
            heapIdx = currentChild;
        }

    }

    private boolean less(int i, int j) {
        return (heap[i].compareTo(heap[j]) < 0);
    }

    private void swap(int i, int j) {
        T swap = heap[i];
        heap[i] = heap[j];
        heap[j] = swap;
    }

}
