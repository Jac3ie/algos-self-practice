package Binary_Search.Pivot_Or_Rotated_BS;

import java.util.*;

public class pivotedSearch {
    public static void main(String[] args) {
        // write a usage case here
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
