package ArrayAndStringType.SubArr_With_GivenSum_Bounded_Maxima;

import java.util.*;

public class subArrGivenSumBoundedMaximum {
    public static void main(String[] args) {
        // ---------- Prepare multiple test cases ----------
        // Each test case: nums, k, M, expectedAnswer (for your own check)
        List<List<Integer>> numsCases = Arrays.asList(
            Arrays.asList(1, 2, 3, 2),                 // simple
            Arrays.asList(1, 2, 3, 2, 7, 2, -1, 3),    // mixed positive/negative
            Collections.emptyList(),                   // empty
            Arrays.asList(5),                          // single element
            Arrays.asList(2, 2, 2, 2),                 // many equal values
            Arrays.asList(1, 10, 1, 1, 10, 1),         // some values > M split segments
            Arrays.asList(-1, -2, -3, -4)              // all negative
        );

        long[] kCases = new long[] {
            5L,      // [2,3] and [3,2] if max<=M
            5L,
            0L,
            5L,
            4L,
            2L,
            -3L
        };

        long[] MCases = new long[] {
            3L,   // max must be <=3
            3L,
            100L,
            10L,
            3L,
            5L,
            -1L   // max must be <= -1
        };

        // ---------- Run all cases ----------
        for (int t = 0; t < numsCases.size(); t++) {
            List<Integer> nums = numsCases.get(t);
            long k = kCases[t];
            long M = MCases[t];

            long ans = Result.countSubarraysWithSumAndMaxAtMost(nums, k, M);

            System.out.println("Test Case " + (t + 1));
            System.out.println("  nums = " + nums);
            System.out.println("  k = " + k + ", M = " + M);
            System.out.println("  countSubarraysWithSumAndMaxAtMost = " + ans);
            System.out.println("------------------------------------------------");
        }
    }
}

class Result {

    /*
     * Complete the 'countSubarraysWithSumAndMaxAtMost' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY nums
     *  2. LONG_INTEGER k
     *  3. LONG_INTEGER M
     */

    public static long countSubarraysWithSumAndMaxAtMost(List<Integer> nums, long k, long M) {
        long result = 0L;
        
        for (int i = 0; i < nums.size(); i++){
            // if curr > M, then this subArr invalid, continue
            if (nums.get(i) > M) continue;
            
            long localSum = nums.get(i);
            if (localSum == k) result++; // if curr alr satisify k, then result++
            for (int j = i + 1; j < nums.size(); j++){
                int JVal = nums.get(j);
                if (JVal > M) break; // this subArr invalid
                localSum += JVal;
                if (localSum == k) result++;
            }
        }
        
        return result;
    }

}
