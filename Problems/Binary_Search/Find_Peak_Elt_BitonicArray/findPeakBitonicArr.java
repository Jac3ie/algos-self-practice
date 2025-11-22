package Binary_Search.Find_Peak_Elt_BitonicArray;

import java.util.*;

public class findPeakBitonicArr {
    public static void main(String[] args) {
        
    }
}

class Result {

    /*
     * Complete the 'findPeakIndex' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY counts as parameter.
     */

    public static int findPeakIndex(List<Integer> counts) {
        // O(logN) -> BS
        int left = 0;
        int right = counts.size() - 1;
        while (left != right){
            int mid = (left + right) / 2;
            int curr = counts.get(mid);
            
            // decide which side to go next
            if (mid + 1 < counts.size()){
                // can reach right side
                int rightOne = counts.get(mid + 1);
                if (rightOne < curr){
                    // right side is descending, go left by right = mid
                    // since we know at least right side wont be the correct answer, but mid maybe
                    right = mid;
                } else {
                    // since we know rightOne > mid, then mid wont be the correct answer, but mid + 1 maybe
                    left = mid + 1;
                }
            }
        }
        return left;
    }

}

