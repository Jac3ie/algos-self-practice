package ArrayAndStringType.Longest_Arithmetic_Subsequence_With_K;

import java.util.*;

public class LASWithDiffK {
    public static void main(String[] args) {
        
    }
    
}

class Result {

    /*
     * Complete the 'findLongestArithmeticProgression' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY arr
     *  2. INTEGER k
     * 
     * Make use of chain walk thinking in HashSet, think HashSet as a circle, we keep finding x - k until we reach a root start
     * Use that start to walk back x + k until cannot find more, record the longest length
     * 
     * There is no way for the inner while to “loop over” the same element multiple times from different starts, 
     * because we deliberately don’t start from non-minimal positions.
     * This is, for each elt in the set, theres only one visit from a unique start for the inner loop,
     * for another chain we wont be able to reach what we ve reached before. think of disjoint;
     * 
     * Runs in O(N), Space is O(N)
     */

    public static int findLongestArithmeticProgression(List<Integer> arr, int k) {
        // base cases
        if (arr.size() == 0 || arr.size() == 1) return arr.size() == 0 ? 0 : 1;
        
        // change it into Set to remove duplicate;
        // this problem looks for longest possible subsequence, so order does not matter as well
        // therefore use HashSet is most official
        Set<Integer> noDup = new HashSet<>(arr);
        
        int longestLen = 0;
        for (int elt : noDup){
            // since we know if x - k exist in set, then its part of the chain
            // we will start with a true root base --- where x - k does not exit
            if (noDup.contains(elt - k)) continue;
            
            // until we find a root start
            int count = 1;
            int curr = elt;
            // perform chain walk
            while (noDup.contains(curr + k)){
                count++;
                curr += k;
            }
            longestLen = Math.max(longestLen, count);
        }
        return longestLen;

    }

}


