package TwoPointersType.Count_Pair_Sum_LE_K;

import java.util.*;

public class countPairsLETarget {
    public static void main(String[] args) {
        List<List<Integer>> testPrices = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),          // normal
                Arrays.asList(3, 1, 2, 2),          // duplicates
                Arrays.asList(5),                   // too short
                Arrays.asList(0, 0, 0, 0),          // all zeros
                Arrays.asList(10, 20, 30, 40, 50)   // larger values
        );

        int[] budgets = new int[]{5, 4, 100, 0, 55};

        for (int t = 0; t < testPrices.size(); t++) {
            List<Integer> prices = new ArrayList<>(testPrices.get(t)); // copy so we can sort safely
            int budget = budgets[t];

            // IMPORTANT: two-pointer counting assumes the list is sorted
            Collections.sort(prices);

            int ans = Result.countAffordablePairs(prices, budget);

            System.out.println("Test " + (t + 1));
            System.out.println("  prices(sorted) = " + prices);
            System.out.println("  budget         = " + budget);
            System.out.println("  pairs count    = " + ans);
            System.out.println("--------------------------------");
        }
    }
}

class Result {

    /*
     * Complete the 'countAffordablePairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY prices
     *  2. INTEGER budget
     */

    public static int countAffordablePairs(List<Integer> prices, int budget) {
        // base cases filter chip
        if (prices.size() <= 1) return 0;
        
        int len = prices.size();
        int left = 0;
        int right = len - 1;
        
        int count = 0;
        
        while (left < right) {
            int leftVal = prices.get(left);
            int rightVal = prices.get(right);
            int sum = leftVal + rightVal;
            
            if (sum <= budget) {
                // that means all pairs in this range with [left, ...] valid
                count += right - left;
                left++;
            } else {
                // curr sum is too big, shrink
                right--;
            }
        }
        
        return count;
    }

}