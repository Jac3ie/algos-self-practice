package Binary_Search.Pivot_Or_Rotated_BS;

import java.util.*;

public class pivotedSearch {
    public static void main(String[] args) {
        // Test cases: each pair = (nums, target)
        List<List<Integer>> testArrays = Arrays.asList(
            // Example from description (adapted to int)
            Arrays.asList(1609466400, 1609470000, 1609473600, 1609459200, 1609462800),
            // Simple rotated sorted arrays
            Arrays.asList(4, 5, 6, 7, 0, 1, 2),
            Arrays.asList(6, 7, 0, 1, 2, 3, 4, 5),
            // No rotation
            Arrays.asList(1, 2, 3, 4, 5, 6),
            // Single element
            Arrays.asList(10),
            // Empty
            Collections.emptyList()
        );

        // Corresponding targets to search
        int[] targets = {
            1609459200,  // should be index 3
            0,           // should be 4
            3,           // should be 5
            4,           // should be 3
            10,          // should be 0
            5            // should be -1 (not found in empty)
        };

        for (int i = 0; i < testArrays.size(); i++) {
            List<Integer> nums = testArrays.get(i);
            int target = targets[i];

            int idx = Result.searchRotatedTimestamps(nums, target);

            System.out.println("Test Case " + (i + 1));
            System.out.println("  nums   = " + nums);
            System.out.println("  target = " + target);
            System.out.println("  index  = " + idx);
            System.out.println("------------------------------------");
        }
    }
}

class Result {

    /*
     * Complete the 'searchRotatedTimestamps' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY nums
     *  2. INTEGER target
     */

    public static int searchRotatedTimestamps(List<Integer> nums, int target) {
        // base cases
        if (nums.size() == 0) return -1;
        if (nums.size() == 1) return nums.get(0) == target ? 0 : -1;
        
        // use binary search thought to perform searching on a sorted collection
        int left = 0;
        int right = nums.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums.get(mid) == target) return mid;
                        
            // check which side is sorted
            if (nums.get(left) <= nums.get(mid)){
                // know left side is sorted in ascending order, pivot is on the right side
                // check the target with the range of sorted side, determine which side to preceed
                if (target >= nums.get(left) && target <= nums.get(mid)){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // pivot is on the left side, and sorted side is right side
                if (target >= nums.get(mid) && target <= nums.get(right)){
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }

}
