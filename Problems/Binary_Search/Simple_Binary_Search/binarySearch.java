package Binary_Search.Simple_Binary_Search;

import java.util.*;

public class binarySearch {
    /*
    * Binary Search:
    * - Use when the input list is already sorted.
    * - Time Complexity: O(log n)
    * - Space Complexity: O(1)
    * - Strategy: repeatedly halve the search space by comparing
    *   the target with the middle element.
    */

    public static int simpleBS(List<Integer> nums, int target){
        int left = 0;
        int right = nums.size() - 1;

        while (left <= right){ // similar condition to stop loop as Two Pointer
            int mid = (left + right) / 2;

            if (nums.get(mid) == target) {
                return mid;
            } else if (nums.get(mid) < target){
                // go to the right side
                left = mid + 1; // +1 since mid we ve evaluated alr
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(2, 5, 8, 12, 15, 20, 23, 30, 42);

        int[] testTargets = {2, 8, 15, 42, 7, 50};

        for (int target : testTargets) {
            int index = simpleBS(nums, target);

            System.out.println("Searching for: " + target);
            System.out.println("Result index: " + index);
            
            if (index != -1) {
                System.out.println("Value found at index " + index + " → " + nums.get(index));
            } else {
                System.out.println("Value not found in list.");
            }

            System.out.println("-----------------------------");
        }
    }
}
