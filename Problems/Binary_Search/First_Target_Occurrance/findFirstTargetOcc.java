package Binary_Search.First_Target_Occurrance;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class findFirstTargetOcc {
    public static void main(String[] args) {
        // -------- Test 1: target appears multiple times --------
        List<Integer> nums1 = Arrays.asList(1, 2, 2, 2, 3, 4);
        int target1 = 2;
        int ans1 = Result.findFirstOccurrence(nums1, target1);
        System.out.println("Test 1");
        System.out.println("  nums   = " + nums1);
        System.out.println("  target = " + target1);
        System.out.println("  first index = " + ans1); // expected 1
        System.out.println("----------------------------------");

        // -------- Test 2: target is the first element --------
        List<Integer> nums2 = Arrays.asList(5, 5, 5, 6, 7);
        int target2 = 5;
        int ans2 = Result.findFirstOccurrence(nums2, target2);
        System.out.println("Test 2");
        System.out.println("  nums   = " + nums2);
        System.out.println("  target = " + target2);
        System.out.println("  first index = " + ans2); // expected 0
        System.out.println("----------------------------------");

        // -------- Test 3: target does not exist --------
        List<Integer> nums3 = Arrays.asList(1, 3, 4, 8, 10);
        int target3 = 6;
        int ans3 = Result.findFirstOccurrence(nums3, target3);
        System.out.println("Test 3");
        System.out.println("  nums   = " + nums3);
        System.out.println("  target = " + target3);
        System.out.println("  first index = " + ans3); // expected -1
        System.out.println("----------------------------------");

        // -------- Test 4: empty list --------
        List<Integer> nums4 = Collections.emptyList();
        int target4 = 1;
        int ans4 = Result.findFirstOccurrence(nums4, target4);
        System.out.println("Test 4");
        System.out.println("  nums   = " + nums4);
        System.out.println("  target = " + target4);
        System.out.println("  first index = " + ans4); // expected -1
        System.out.println("----------------------------------");

        // -------- Test 5: single element list --------
        List<Integer> nums5 = Arrays.asList(9);
        int target5 = 9;
        int ans5 = Result.findFirstOccurrence(nums5, target5);
        System.out.println("Test 5");
        System.out.println("  nums   = " + nums5);
        System.out.println("  target = " + target5);
        System.out.println("  first index = " + ans5); // expected 0
        System.out.println("----------------------------------");
    }
}

class Result {

    /*
     * Complete the 'findFirstOccurrence' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY nums
     *  2. INTEGER target
     */

    public static int findFirstOccurrence(List<Integer> nums, int target) {
        // use an variant of BS
        int len = nums.size();
        if (len == 0) return -1;
        
        int left = 0;
        int right = len - 1;
        int targetIdx = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;
            int midVal = nums.get(mid);
            
            if (midVal == target) {
                targetIdx = Math.min(targetIdx, mid);
                // need to shrink to the left o/w will infinite loop
                right = mid - 1;
            }
            else if (midVal < target) left = mid + 1;
            else right = mid - 1;
        }
        
        return targetIdx == Integer.MAX_VALUE ? -1 : targetIdx;
    }

}