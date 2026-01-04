package Sliding_Window.Minimum_Size_Subarr_Sum;

import java.util.Arrays;

public class minSizeSubarrSum {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // -------- Case 1: LeetCode classic example --------
        int target1 = 7;
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        int ans1 = sol.minSubArrayLen(target1, nums1);
        System.out.println("===================================");
        System.out.println("Case 1");
        System.out.println("target = " + target1);
        System.out.println("nums   = " + Arrays.toString(nums1));
        System.out.println("Expected: 2  (subarray [4,3])");
        System.out.println("Got     : " + ans1);
        System.out.println("===================================");
        System.out.println();

        // -------- Case 2: exact hit with single element --------
        int target2 = 4;
        int[] nums2 = {1, 4, 4};
        int ans2 = sol.minSubArrayLen(target2, nums2);
        System.out.println("Case 2");
        System.out.println("target = " + target2);
        System.out.println("nums   = " + Arrays.toString(nums2));
        System.out.println("Expected: 1  (subarray [4])");
        System.out.println("Got     : " + ans2);
        System.out.println("===================================");
        System.out.println();

        // -------- Case 3: impossible to reach target --------
        int target3 = 11;
        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int ans3 = sol.minSubArrayLen(target3, nums3);
        System.out.println("Case 3");
        System.out.println("target = " + target3);
        System.out.println("nums   = " + Arrays.toString(nums3));
        System.out.println("Expected: 0  (no subarray sum >= target)");
        System.out.println("Got     : " + ans3);
        System.out.println("===================================");
        System.out.println();

        // -------- Case 4: whole array needed --------
        int target4 = 15;
        int[] nums4 = {1, 2, 3, 4, 5};
        int ans4 = sol.minSubArrayLen(target4, nums4);
        System.out.println("Case 4");
        System.out.println("target = " + target4);
        System.out.println("nums   = " + Arrays.toString(nums4));
        System.out.println("Expected: 5  (whole array sums to 15)");
        System.out.println("Got     : " + ans4);
        System.out.println("===================================");
        System.out.println();

        // -------- Case 5: multiple shrink steps in one right move --------
        int target5 = 9;
        int[] nums5 = {2, 3, 1, 1, 1, 6};
        int ans5 = sol.minSubArrayLen(target5, nums5);
        System.out.println("Case 5");
        System.out.println("target = " + target5);
        System.out.println("nums   = " + Arrays.toString(nums5));
        System.out.println("Expected: 3  (subarray [1,1,6] sum=8? actually not enough, best is [3,1,1,1,6]=12 -> minimal is [1,1,6]=8 no, so answer should be 2 with [3,6]=9? not contiguous; best contiguous is [2,3,1,1,1,6]=14, minimal is [1,6]=7, [1,1,6]=8, [1,1,1,6]=9 => length 4)");
        System.out.println("Got     : " + ans5);
        System.out.println("===================================");
    }
}

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int minSoFar = nums.length + 1;
        int currTotal = 0;

        for (int right = 0; right < nums.length; right++) {
            int rightVal = nums[right];
            currTotal += rightVal;

            if (currTotal >= target) {
                while (currTotal >= target && left <= right) {
                    if (currTotal >= target) minSoFar = Math.min(minSoFar, right - left + 1);
                    currTotal -= nums[left];
                    left++;
                }
            }
        }
        return minSoFar == nums.length + 1 ? 0 : minSoFar;
    }
}
