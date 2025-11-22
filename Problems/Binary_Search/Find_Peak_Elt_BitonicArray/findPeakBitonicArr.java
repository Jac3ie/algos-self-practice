package Binary_Search.Find_Peak_Elt_BitonicArray;

import java.util.*;

public class findPeakBitonicArr {
    public static void main(String[] args) {
        // Prepare some bitonic (mountain-shaped) test cases
        List<List<Integer>> testCases = Arrays.asList(
            Arrays.asList(1, 3, 8, 12, 4, 2),        // peak at index 3 -> 12
            Arrays.asList(0, 2, 4, 6, 8, 10, 9, 5),  // peak at index 5 -> 10
            Arrays.asList(1, 2, 3, 4, 5, 3, 1),      // peak at index 4 -> 5
            Arrays.asList(5, 4, 3, 2, 1),            // strictly decreasing, peak at index 0
            Arrays.asList(1, 2, 3, 4, 5),            // strictly increasing, peak at last index
            Arrays.asList(2)                         // single element, peak at index 0
        );

        int caseNo = 1;
        for (List<Integer> arr : testCases) {
            int peakIndex = Result.findPeakIndex(arr);
            Integer peakValue = arr.get(peakIndex);

            System.out.println("Test Case " + caseNo++);
            System.out.println("  counts = " + arr);
            System.out.println("  Peak index = " + peakIndex);
            System.out.println("  Peak value = " + peakValue);
            System.out.println("-----------------------------------");
        }
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

