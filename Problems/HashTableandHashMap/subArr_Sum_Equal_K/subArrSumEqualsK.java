package HashTableandHashMap.subArr_Sum_Equal_K;

import java.util.*;

public class subArrSumEqualsK {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test cases
        int[][] testNums = new int[][]{
            {1, 1, 1},           // classic example
            {1, 2, 3},           // only one subarray sums to k
            {1, -1, 0},          // includes negatives and zero
            {3, 4, 7, 2, -3, 1, 4, 2}, // multiple subarrays to same k
            {},                  // empty array
        };

        int[] testK = new int[]{
            2,   // for {1,1,1} -> 2 subarrays: [1,1] (0..1), [1,1] (1..2)
            3,   // for {1,2,3} -> 2 subarrays: [1,2], [3]
            0,   // for {1,-1,0} -> 3 subarrays: [1,-1], [0], [1,-1,0]
            7,   // for {3,4,7,2,-3,1,4,2} -> several
            0    // for {} -> 0
        };

        for (int i = 0; i < testNums.length; i++) {
            int[] nums = testNums[i];
            int k = testK[i];
            int result = sol.subarraySum(nums, k);

            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("  nums = " + Arrays.toString(nums));
            System.out.println("  k    = " + k);
            System.out.println("  subarray count = " + result);
            System.out.println("--------------------------------");
        }
    }
}

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        
        // setting up for prefix + HashMap solution
        int prefix = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1); // this is for k = 0?

        for (int num : nums) {
            prefix += num;
            // prefix[i] = prefix[r + 1] - k
            int need = prefix - k;
            Integer intentFreq = freq.get(need);

            if (intentFreq != null) {
                count += intentFreq;
            }
            // record prefix[i]
            freq.put(prefix, freq.getOrDefault(prefix, 0) + 1);
        }
        return count;
    }
}