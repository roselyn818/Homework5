package HomeworkFiles;

import HomeworkFiles.MaxPQ;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class HulkSmash {

    // Hulk always smashes the two biggest items and leaves the difference
    public static int[] objectSmash(int[] objects) {
        // Convert the array into a MaxPQ
        ArrayList<Integer> objectList = new ArrayList<>();
        for (int obj : objects) {
            objectList.add(obj);
        }

        // Create the MaxPQ using the objectList
        MaxPQ maxPQ = new MaxPQ(objectList);

        // Continue smashing while more than one element remains
        while (maxPQ.getHeap().size() > 1) {
            // Step 1: Remove the largest number (root of the max heap)
            int largestNumber = maxPQ.remove();

            // Step 2: Remove the second largest number
            int secondLargestNumber = maxPQ.remove();

            // Step 3: Check if the two numbers are the same
            if (largestNumber == secondLargestNumber) {
                // If they are equal, destroy both (do nothing)
                System.out.println("The two largest numbers are equal (" + largestNumber + "). Both objects are destroyed.");
            } else {
                // Step 4: Smash the two largest numbers and store the difference
                int difference = largestNumber - secondLargestNumber;

                // Step 5: Insert the difference back into the MaxPQ
                maxPQ.insert(difference);

                // Print the heap after each smash to see progress
                System.out.println("After smash: " + maxPQ.getHeap());
            }
        }

        // Convert the final heap to an int[] array
        objects = convertListToArray(maxPQ.getHeap());

        return objects;
    }

    // Method to convert List<Integer> to int[]
    public static int[] convertListToArray(List<Integer> list) {
        // Create an int array with the same size as the list
        int[] array = new int[list.size()];

        // Loop through the list and assign each element to the array
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);  // Assign each List element to the array
        }

        return array;  // Return the populated array
    }

    public static void main(String[] args) {
        int[] objects = {50, 10, 70, 70, 20}; // Sample array of integers

        System.out.println("Original array: " + Arrays.toString(objects));
        objects = objectSmash(objects); // Call the objectSmash method to start smashing

        System.out.println("Final array after smashing: " + Arrays.toString(objects));
    }
}