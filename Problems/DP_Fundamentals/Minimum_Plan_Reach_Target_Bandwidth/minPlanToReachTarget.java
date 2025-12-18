package DP_Fundamentals.Minimum_Plan_Reach_Target_Bandwidth;

import java.util.*;

public class minPlanToReachTarget {
    
}

class Result {

    /*
     * Complete the 'findMinimumPlansForBandwidth' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY planSizes
     *  2. INTEGER targetBandwidth
     */

    public static int findMinimumPlansForBandwidth(List<Integer> planSizes, int targetBandwidth) {
        // use dp thinking to achieve it:
        // dp[x] is the minimum steps to build target x, we build all the way to targetBandwidth
        // dp[x] = dp[x-p for all p in planSizes where dp[x-p] is reacheable, meaning >=0]
        // in the standard dp, for each x we will have to iterate all entry in planSize once
        // therefore TC: O(size(candidate arr) * size(target))
        
        int INF = Integer.MAX_VALUE;
        int[] dp = new int[targetBandwidth + 1]; // 0...target inclusive
        Arrays.fill(dp, INF);
        dp[0] = 0; // form 0 needs 0 plan
        
        for (int i = 1; i <= targetBandwidth; i++){
            for (int plan : planSizes) {
                if (i - plan >= 0 && dp[i - plan] != INF) {
                    dp[i] = Math.min(dp[i - plan] + 1, dp[i]); // dp[x] = dp[x - p] + 1, one more step
                }
            }
        }
        
        return dp[targetBandwidth] == INF ? -1 : dp[targetBandwidth];
    }

}