package HomeworkFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static HomeworkFiles.HeapUtils.*;

public class MinPQ implements HeapInterface {

    private final List<Integer> heap;

    public MinPQ(){
        this.heap = new ArrayList<>(); // we aren't using List here because the List library is an interface while ArrayList is a concrete class
                                        //ArrayList is implemented using List anyways and by using List, we get access to the methods provided by List
    }

    // Additional constructor to initialize a heap with an ArrayList
    public MinPQ(ArrayList<Integer> numbers) {
        // We could simply assign this local heap reference to the incoming ArrayList
        // but that can lead to unintended side effects because both would point to the same memory location
        this.heap = new ArrayList<>(numbers);  // copies all elements from numbers to heap

        // heapify process after copying elements
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            heapify(heap.size(), i);
        }
    }

    public List<Integer> getHeap(){
        return new ArrayList<>(heap);  // this is our basic constructor function
    }

    public void swap(int index1, int index2){
        int temp = heap.get(index1); //sets a temporary integer in memory of index 1
        heap.set(index1, heap.get(index2)); //swaps index 1 with index 2
        heap.set(index2, temp); //utilizes the temp value in memory to set index 2 to the original index 1
    }

    public void insert(int value){
        heap.add(value); //adds the value to the Array List

        int current = heap.size() - 1; // gets the index position of the newly inserted method

        //loop will run until either the head of the heap is reached or the current node is smaller than the parent
        while(current > 0 && heap.get(current) < heap.get(parent(current))){
            swap(current, parent(current)); //swaps the current node with the parent node
            current = parent(current); //sets the current node to the parent node.
        }
    }

    // Remove the root of the heap
    public Integer remove() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("The heap is empty, nothing to remove.");
        }

        // Swap the root with the last node
        int lastNode = heap.size() - 1;
        swap(0, lastNode);
        // Remove the last node and save it to return later
        int removed = heap.remove(lastNode);

        // If any elements are left, restore the heap property
        if (!heap.isEmpty()) {
            heapify(heap.size(), 0);
        }

        return removed;
    }

    // heapify method
    // heapify method is used to enforce the heap property on a tree where this property may currently be violated at the root

    public void heapify(int size, int i) {
        int smallest = i;  // Assume the current (root) is the smallest

        int left = leftChild(i);  // find index of left child node
        int right = rightChild(i);  // find index of right child node

        // If left child is larger than root
        if (left < size && heap.get(left) < heap.get(smallest)){
            smallest = left; // then point `smallest` to left child
        }

        // If right child is larger than the smallest so far
        if (right < size && heap.get(right) < heap.get(smallest)){
            smallest = right; // then point `smallest` to right child
        }

        // If largest is not root (i.e., either left or right child is larger than root)
        if (smallest != i) {
            swap(i, smallest); // swap the root with the smaller child
            heapify(size, smallest); // recursively heapify the affected subtree
        }
    }

}
