package DP_Fundamentals.Longest_Increasing_Sequence_Length;

import java.util.*;

public class LISLength_NSquared {
    public static void main(String[] args) {

        List<List<Integer>> testCases = Arrays.asList(
            // Example 1
            Arrays.asList(10, 9, 2, 5, 3, 7, 101, 18),
            // Example 2
            Arrays.asList(-2, -1, 0, 1, -3, 2, 2, 3),
            // Fully sorted increasing
            Arrays.asList(1, 2, 3, 4, 5, 6),
            // Fully sorted decreasing
            Arrays.asList(6, 5, 4, 3, 2, 1),
            // Random mix
            Arrays.asList(3, 10, 2, 1, 20),
            // Single element
            Arrays.asList(7),
            // Empty list
            Arrays.asList(),
            // Many duplicates (strictly increasing means duplicates don't extend)
            Arrays.asList(2, 2, 2, 2, 2)
        );

        int caseNumber = 1;
        for (List<Integer> arr : testCases) {
            int n = arr.size();
            int lisLength = Result.computeLongestIncreasingSubsequenceLength(n, arr);

            System.out.println("Test Case " + caseNumber++);
            System.out.println("  Input:  " + arr);
            System.out.println("  LIS Length = " + lisLength);
            System.out.println("-----------------------------------------");
        }
    }
}

class Result {

    /*
     * Complete the 'computeLongestIncreasingSubsequenceLength' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY quality
     * 
     * Overall takes O(N^2), if we want O(NlogN) then inner loop we need to use BS!
     */

    public static int computeLongestIncreasingSubsequenceLength(int n, List<Integer> quality) {
        // base cases
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        List<Integer> lst = new ArrayList<>(Arrays.asList(quality.get(0)));
        for (int i = 1; i < n; i++){
            // outer loop takes O(N)
            int currQuality = quality.get(i);
            int lstSize = lst.size();
            int lstTail = lst.get(lstSize - 1);
            
            if (currQuality > lstTail){
                lst.add(quality.get(i));
            } else {
                for (int j = 0; j < lst.size(); j++){
                    // inner loop takes O(N) as well - Linear Search

                    // find the smallest idx to replace with this val - currQuality
                    int currLst = lst.get(j);
                    if (currQuality < currLst){
                        lst.set(j, currQuality);
                        break;
                    }
                }
            }
        }
        
        return lst.size();
    }

}
