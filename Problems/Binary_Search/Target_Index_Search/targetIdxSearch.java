package Binary_Search.Target_Index_Search;

import java.util.*;

public class targetIdxSearch {
    public static void main(String[] args) {
        // Test case 1: normal hit (target exists)
        List<Integer> nums1 = Arrays.asList(-5, -1, 0, 3, 9, 12);
        int target1 = 9;
        System.out.println("Test 1");
        System.out.println("  nums   = " + nums1);
        System.out.println("  target = " + target1);
        int ans1 = Result.binarySearch(nums1, target1);
        System.out.println("  index  = " + ans1 + "  (value at index = " + (ans1 == -1 ? "N/A" : nums1.get(ans1)) + ")");
        System.out.println("----------------------------------");

        // Test case 2: miss (target not exists)
        List<Integer> nums2 = Arrays.asList(1, 2, 4, 6, 8, 10);
        int target2 = 7;
        System.out.println("Test 2");
        System.out.println("  nums   = " + nums2);
        System.out.println("  target = " + target2);
        int ans2 = Result.binarySearch(nums2, target2);
        System.out.println("  index  = " + ans2 + "  (not found -> -1 expected)");
        System.out.println("----------------------------------");

        // Test case 3: empty list
        List<Integer> nums3 = new ArrayList<>();
        int target3 = 5;
        System.out.println("Test 3");
        System.out.println("  nums   = " + nums3);
        System.out.println("  target = " + target3);
        int ans3 = Result.binarySearch(nums3, target3);
        System.out.println("  index  = " + ans3 + "  (empty -> -1 expected)");
        System.out.println("----------------------------------");

        // Test case 4: single element list (hit + miss)
        List<Integer> nums4 = Arrays.asList(42);
        System.out.println("Test 4A");
        System.out.println("  nums   = " + nums4);
        System.out.println("  target = 42");
        int ans4a = Result.binarySearch(nums4, 42);
        System.out.println("  index  = " + ans4a + "  (value at index = " + (ans4a == -1 ? "N/A" : nums4.get(ans4a)) + ")");
        System.out.println("----------------------------------");

        System.out.println("Test 4B");
        System.out.println("  nums   = " + nums4);
        System.out.println("  target = 7");
        int ans4b = Result.binarySearch(nums4, 7);
        System.out.println("  index  = " + ans4b + "  (not found -> -1 expected)");
        System.out.println("----------------------------------");
    }
}

class Result {

    /*
     * Complete the 'binarySearch' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY nums
     *  2. INTEGER target
     */

    public static int binarySearch(List<Integer> nums, int target) {
        int len = nums.size();
        if (len == 0) return -1;
        
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            int midVal = nums.get(mid);
            
            if (midVal == target) return mid;
            if (target > midVal) {
                // search right side
                left = mid + 1;
            } else {
                // search left side
                right = mid - 1;
            }
        }
        return -1;
    }

}
