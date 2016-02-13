package xyz.devyang.cs520.astar;

import java.util.Arrays;

/**
 * Created by YangYu on 2/13/16.
 */
public class BinaryHeap {

    private final int DEFAULT_CAPACITY = 10;
    private Cell[] array;
    private int size;

    /**
     * Constructs a new BinaryHeap.
     */
    public BinaryHeap () {
        array = new Cell[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Adds a value to the min-heap.
     */
    public void insert(Cell cell) {
        // grow array if needed
        if (size >= array.length - 1) {
            array = this.resize();
        }

        // place element into heap at bottom
        size++;
        int index = size;
        array[index] = cell;

        bubbleUp();
    }

    /**
     * Returns true if the heap has no elements; false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return minimum element in the heap and delete it.
     * @return
     */
    public Cell pop() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        Cell min = array[1];
        delMin();
        return min;
    }

    /**
     * Returns (but does not remove) the minimum element in the heap.
     */
    public Cell peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        return array[1];
    }

    /**
     * Removes and returns the minimum element in the heap.
     */
    public Cell delMin() {
        Cell result = peek();

        // get rid of the last leaf/decrement
        array[1] = array[size];
        array[size] = null;
        size--;

        bubbleDown();

        return result;
    }


    /**
     * Returns a String representation of BinaryHeap with values stored with
     * heap structure and order properties.
     */
    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    /**
     * Performs the "bubble down" operation to place the element that is at the
     * root of the heap
     */
    private void bubbleDown() {
        int index = 1;

        // bubble down
        while (hasLeftChild(index)) {
            // which of my children is smaller?
            int smallerChild = leftIndex(index);

            // bubble with the smaller child, if I have a smaller child
            if (hasRightChild(index)
                    && array[leftIndex(index)].compareTo(array[rightIndex(index)]) > 0) {
                smallerChild = rightIndex(index);
            }

            if (array[index].compareTo(array[smallerChild]) > 0) {
                swap(index, smallerChild);
            } else {
                // otherwise, get outta here!
                break;
            }

            // make sure to update loop counter/index of where last el is put
            index = smallerChild;
        }
    }


    /**
     * Performs the "bubble up" operation to place a newly inserted element that is at the
     * bottom of the heap
     */
    private void bubbleUp() {
        int index = this.size;
        while (hasParent(index)
                && (parent(index).compareTo(array[index]) > 0)) {
            // parent/child are out of order; swap them
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    private int parentIndex(int i) {
        return i / 2;
    }

    private int leftIndex(int i) {
        return i * 2;
    }

    private int rightIndex(int i) {
        return i * 2 + 1;
    }

    private boolean hasParent(int i) {
        return i > 1;
    }

    private boolean hasLeftChild(int i) {
        return leftIndex(i) <= size;
    }

    private boolean hasRightChild(int i) {
        return rightIndex(i) <= size;
    }

    private Cell parent(int i) {
        return array[parentIndex(i)];
    }

    private Cell[] resize() {
        return Arrays.copyOf(array, array.length * 2);
    }

    private void swap(int index1, int index2) {
        Cell tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }
}
