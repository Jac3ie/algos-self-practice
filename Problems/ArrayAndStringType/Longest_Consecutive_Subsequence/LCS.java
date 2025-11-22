package ArrayAndStringType.Longest_Consecutive_Subsequence;

import java.util.*;

public class LCS {
    public static void main(String[] args) {
        
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
