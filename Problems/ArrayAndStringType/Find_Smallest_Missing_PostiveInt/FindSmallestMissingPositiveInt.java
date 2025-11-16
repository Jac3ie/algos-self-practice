package ArrayAndStringType.Find_Smallest_Missing_PostiveInt;

import java.util.*;

public class FindSmallestMissingPositiveInt{
    public static void main(String[] args) {
        List<List<Integer>> tests = Arrays.asList(
            Arrays.asList(3, 4, -1, 1),        // expected 2
            Arrays.asList(1, 2, 0),            // expected 3
            Arrays.asList(7, 8, 9, 11, 12),    // expected 1
            Arrays.asList(1, 2, 3, 4),         // expected 5
            Arrays.asList(2, 2),               // expected 1
            Arrays.asList(1, 1),               // expected 2
            Arrays.asList(2, 3, 4),            // expected 1
            Arrays.asList(1),                  // expected 2
            Arrays.asList(),                   // expected 1
            Arrays.asList(1, 2, 3, 5, 6),      // expected 4
            Arrays.asList(3, 3, 1, 2),         // expected 4
            Arrays.asList(4, 3, 2, 1),         // expected 5
            Arrays.asList(1, 1000),            // expected 2
            Arrays.asList(1, 2, 3, 10),        // expected 4
            Arrays.asList(10, 1, 2, 3)         // expected 4
        );

        for (List<Integer> test : tests) {
            // Copy list because your algorithm mutates the list in-place
            List<Integer> inputCopy = new ArrayList<>(test);

            int result = Solution.findSmallestMissingPositive(inputCopy);

            System.out.println(
                "Input:  " + test +
                "\nOutput: " + result +
                "\n------------------------------"
            );
        }
    }
}

class Solution{
    /*
     * Complete the 'findSmallestMissingPositive' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY orderNumbers as parameter.
     */
    public static int findSmallestMissingPositive(List<Integer> orderNumbers) {
        int len = orderNumbers.size();
        
        for (int i = 0; i < len; i++){
            // valid candidate values are [1, len] inclusively!
            while (orderNumbers.get(i) >= 1 && orderNumbers.get(i) <= len && orderNumbers.get(i) != i + 1){
                int val = orderNumbers.get(i);
                int correctIdx = val - 1;
                
                // prevent duplicates causing infinite loop
                if (orderNumbers.get(correctIdx) == val) break;
                
                int to_swap = orderNumbers.get(correctIdx);
                orderNumbers.set(correctIdx, val);
                orderNumbers.set(i, to_swap);
            }
        }
        
        for (int i = 0; i < len; i++){
            if (orderNumbers.get(i) != i + 1){
                return i + 1;
            }
        }
        return len + 1;
    }
}