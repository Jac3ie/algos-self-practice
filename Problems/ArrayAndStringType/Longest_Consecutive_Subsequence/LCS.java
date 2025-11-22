package ArrayAndStringType.Longest_Consecutive_Subsequence;

import java.util.*;

public class LCS {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] testCases = {
            {},                               // empty
            {100},                            // single element
            {100, 4, 200, 1, 3, 2},           // classic example -> 4 (1,2,3,4)
            {0, 3, 7, 2, 5, 8, 4, 6, 0, 1},   // -> 9 (0..8)
            {9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6}, // -> 7 (-1..5 or 3..9 depending)
            {10, 5, 12, 3, 55, 4, 11, 6},     // -> 4 (3,4,5,6)
            {1, 2, 0, 1},                     // duplicates -> 3 (0,1,2)
        };

        int caseNo = 1;
        for (int[] arr : testCases) {
            int ans = sol.longestConsecutive(arr);
            System.out.println("Test Case " + caseNo++);
            System.out.println("  nums = " + Arrays.toString(arr));
            System.out.println("  Longest consecutive length = " + ans);
            System.out.println("-------------------------------------");
        }
    }
}

class Solution {
    public int longestConsecutive(int[] nums) {
        // base cases
        if (nums.length == 0 || nums.length == 1) return nums.length == 0 ? 0 : 1;

        /*
        * remove duplicate using HashSet
        * int[] -> List is actually anoying to bother, need to use stream()
        * change int[] -> Integer[] and then use Arrays.asList(Integer[])
        * since Arrays.asList(<object arr only>) not primitive arr
        * if primitive arr will produce List<int[]> not List<Integer>!
        */
        Set<Integer> noDup = new HashSet<>();
        for (int each : nums){
            noDup.add(each);
        }

        // use outer loop to find the actual root
        int longestSoFar = 0;
        for (int elt : noDup){
            if (noDup.contains(elt - 1)) continue;

            // founded actual root
            int curr = elt;
            int next = curr + 1;
            int count = 1;
            // perform chain walk back to count the length
            while (noDup.contains(next)){
                count++;
                next++;
            }
            longestSoFar = Math.max(count, longestSoFar);
        }

        return longestSoFar;
    }
}
