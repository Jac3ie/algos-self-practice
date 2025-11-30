package TwoPointersType.Three_Sum;

import java.util.*;

public class threeSum {
    public static void main(String[] args) {
        int[][] tests = new int[][]{
            {-1, 0, 1, 2, -1, -4},
            {0, 1, 1},
            {0, 0, 0},
            {-1, 0, 0, -2, 1, 4},
            {2, -2, -2, -4, 3}
        };

        Solution sol = new Solution();

        int caseNo = 1;
        for (int[] arr : tests) {
            // clone so we don't permanently sort the original input
            int[] input = arr.clone();

            System.out.println("Test Case " + caseNo++);
            System.out.println("Input:  " + Arrays.toString(input));

            List<List<Integer>> result = sol.threeSum(input);

            System.out.println("Output: " + result);
            System.out.println("-----------------------------------");
        }
    }
}

class Solution {
    /*
    * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] 
    * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
    * Notice that the solution set must not contain duplicate triplets.
    */

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        if (nums.length == 0 || nums.length == 1) return triplets; // deal with base cases first

        // sort the arr first, so we can make up our rules to ensure uniqueness
        Arrays.sort(nums); // take O(NlogN)

        for (int i = 0; i < nums.length - 1; i++){
            int curr = nums[i];
            // skip the same starting point to ensure uniqueness
            if (i > 0 && nums[i - 1] == curr) continue;

            int left = i + 1;
            int right = nums.length - 1;
            int residual = 0 - curr;

            while (left < right){
                int leftVal = nums[left];
                int rightVal = nums[right];

                if (leftVal + rightVal == residual){
                    triplets.add(List.of(curr, leftVal, rightVal));
                    // filter out the same leftVal so make sure leftVal is unique
                    while (left < right && nums[left + 1] == leftVal) left++;
                    // filter out the same rightVal so rightVal we used to form triplet is unique
                    while (left < right && nums[right - 1] == rightVal) right--;

                    // what happen if left & right no dup, how should we move?
                    left++;
                    right--;
                } else if (leftVal + rightVal < residual) {
                    // if smaller, then since its sorted then we shall left++ to make result bigger
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triplets;
    }
}
