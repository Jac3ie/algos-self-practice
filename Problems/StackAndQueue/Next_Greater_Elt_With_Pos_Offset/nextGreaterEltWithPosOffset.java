package StackAndQueue.Next_Greater_Elt_With_Pos_Offset;

import java.util.*;

public class nextGreaterEltWithPosOffset {
    public static void main(String[] args) {
        List<List<Integer>> testCases = Arrays.asList(
            Arrays.asList(2, 1, 2, 4, 3),      // example
            Arrays.asList(5),                  // single element
            Arrays.asList(4, 3, 2, 1),          // strictly decreasing
            Arrays.asList(1, 2, 3, 4),          // strictly increasing
            Arrays.asList(2, 2, 2, 2),          // all equal
            Arrays.asList(-1, -2, 0, -3, 5)     // negatives + positives
        );

        int caseNo = 1;
        for (List<Integer> readings : testCases) {
            System.out.println("Test Case " + caseNo++);
            System.out.println("Input:  " + readings);

            List<List<Integer>> ans = Result.findNextGreaterElementsWithDistance(readings);

            System.out.println("Output:");
            for (List<Integer> pair : ans) {
                System.out.println(pair.get(0) + " " + pair.get(1));
            }
            System.out.println("-----------------------------------");
        }
    }
}

class Result {

    /*
     * Complete the 'findNextGreaterElementsWithDistance' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY readings as parameter.
     */

    public static List<List<Integer>> findNextGreaterElementsWithDistance(List<Integer> readings) {
        // base cases filter chips
        List<List<Integer>> result = new ArrayList<>();
        int len = readings.size();
        if (len == 0) return result;
        
        // prepare the result list
        for (int i = 0; i < len; i++) {
            result.add(Arrays.asList(-1, -1));
        }
        
        // create a stack for retriving result in LIFO
        Deque<Integer> stack = new ArrayDeque<>();
        
        stack.push(0);
        for (int i = 1; i < len; i++) {
            int currVal = readings.get(i);
            if (!stack.isEmpty() && currVal > readings.get(stack.peek())){
                while (!stack.isEmpty() && currVal > readings.get(stack.peek())) {
                    int recentIdx = stack.pop();
                    result.set(recentIdx, Arrays.asList(currVal, i - recentIdx));
                }
            }
            // for stack.isEmpty || currVal <= readings.get(stack.peek()) just do push
            stack.push(i);
        }
        return result;
    }

}