package HashTableandHashMap.Two_Sum;

import java.util.*;

public class twoSum {
    public static void main(String[] args) {
        // Prepare some test cases
        List<List<Integer>> testCases = Arrays.asList(
            Arrays.asList(2, 7, 11, 15),       // classic example
            Arrays.asList(1, 2, 3, 4),         // no pair
            Arrays.asList(3, 3),               // pair with same value
            Arrays.asList(1, 5, 3, 3, 4),      // multiple possible pairs
            Arrays.asList(),                   // empty list
            Arrays.asList(10),                 // single element
            Arrays.asList(1, 9, 2, 8, 3, 7)    // several valid pairs
        );

        int[] targets = {
            9,   // 2 + 7
            8,   // no pair
            6,   // 3 + 3
            6,   // 3 + 3 (indices 2,3)
            10,  // no pair
            10,  // no pair
            10   // e.g. 1 + 9 or 2 + 8 or 3 + 7
        };

        for (int k = 0; k < testCases.size(); k++) {
            List<Integer> tasks = testCases.get(k);
            int target = targets[k];

            List<Integer> ans = Result.findTaskPairForSlot(tasks, target);

            System.out.println("Test Case " + (k + 1));
            System.out.println("  taskDurations = " + tasks);
            System.out.println("  slotLength    = " + target);
            System.out.println("  result indices= " + ans);
            if (ans.get(0) != -1) {
                int i = ans.get(0), j = ans.get(1);
                System.out.println("  values        = " 
                        + tasks.get(i) + " + " + tasks.get(j) 
                        + " = " + (tasks.get(i) + tasks.get(j)));
            } else {
                System.out.println("  No valid pair found.");
            }
            System.out.println("--------------------------------------");
        }
    }
}

class Result {

    /*
     * Complete the 'findTaskPairForSlot' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY taskDurations
     *  2. INTEGER slotLength
     */

    public static List<Integer> findTaskPairForSlot(List<Integer> taskDurations, int slotLength) {
        // make use of HashMap to achieve O(N) TC and SC
        // key is using .containsKey()
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < taskDurations.size(); i++){
            int curr = taskDurations.get(i);
            int residual = slotLength - curr;
            
            if (map.containsKey(residual)){
                return List.of(map.get(residual), i); // O(1)
            }
            
            map.put(curr, i);
        }
        return List.of(-1, -1);

    }

}