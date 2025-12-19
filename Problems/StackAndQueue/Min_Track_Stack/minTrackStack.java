package StackAndQueue.Min_Track_Stack;

import java.util.*;

public class minTrackStack {
    public static void main(String[] args) {

        // ------------------- Test Case 1 (sample-like) -------------------
        List<String> ops1 = Arrays.asList(
                "push 2", "push 0", "push 3", "push 0",
                "getMin", "pop", "getMin", "pop", "top", "getMin"
        );
        List<Integer> out1 = Result.processCouponStackOperations(ops1);

        System.out.println("Test 1 operations:");
        for (String op : ops1) System.out.println("  " + op);
        System.out.println("Test 1 output (top/getMin results):");
        for (Integer x : out1) System.out.println(x);
        System.out.println("------------------------------------");


        // ------------------- Test Case 2 (many mins + pops) -------------------
        List<String> ops2 = Arrays.asList(
                "push 5", "getMin",
                "push 4", "getMin",
                "push 6", "getMin",
                "pop", "getMin",
                "pop", "top", "getMin"
        );
        List<Integer> out2 = Result.processCouponStackOperations(ops2);

        System.out.println("Test 2 operations:");
        for (String op : ops2) System.out.println("  " + op);
        System.out.println("Test 2 output (top/getMin results):");
        for (Integer x : out2) System.out.println(x);
        System.out.println("------------------------------------");


        // ------------------- Test Case 3 (duplicate minimums) -------------------
        List<String> ops3 = Arrays.asList(
                "push 1", "push 1", "push 2",
                "getMin",
                "pop", "getMin",
                "pop", "getMin",
                "top"
        );
        List<Integer> out3 = Result.processCouponStackOperations(ops3);

        System.out.println("Test 3 operations:");
        for (String op : ops3) System.out.println("  " + op);
        System.out.println("Test 3 output (top/getMin results):");
        for (Integer x : out3) System.out.println(x);
        System.out.println("------------------------------------");
    }
}

class Result {

    /*
     * Complete the 'processCouponStackOperations' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY operations as parameter.
     */

    public static List<Integer> processCouponStackOperations(List<String> operations) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        
        // maintain a minStack to track the smallest element at each depth
        Deque<Integer> minStack = new ArrayDeque<>();
        
        for (String operation : operations) {
            String first3 = operation.substring(0, 3);
            
            switch (first3){
                case "pus":
                    String toPush = operation.substring(5); // index 5 onwards
                    int toPushNum = Integer.parseInt(toPush);
                    stack.push(toPushNum);
                    
                    if (minStack.isEmpty()) minStack.push(toPushNum);
                    else minStack.push(Math.min(toPushNum, minStack.peek())); // record the current minimum
                    
                    break;
                
                case "get":
                    result.add(minStack.peek());
                    break;
                    
                case "pop":
                    stack.pop();
                    minStack.pop();
                    break;
                    
                case "top":
                    result.add(stack.peek());
                    break;
            }  
        }
        return result;
    }

}