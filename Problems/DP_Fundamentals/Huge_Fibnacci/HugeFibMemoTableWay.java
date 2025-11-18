package DP_Fundamentals.Huge_Fibnacci;

import java.util.*;

public class HugeFibMemoTableWay {
    public static void main(String[] args) {
        int[] testCases = {0, 1, 2, 5, 10, 20, 30, 40, 50};

        for (int n : testCases) {
            long result = Result.getAutoSaveInterval(n);
            System.out.println("Input n = " + n);
            System.out.println("Auto-save interval = " + result);
            System.out.println("------------------------------------");
        }
    }
}

class Result {

    /*
     * Complete the 'getAutoSaveInterval' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER n as parameter.
     
     
     * Memorized way + recursion to prevent pure recursion slow performance => O(n) TC
     */
    
    static long[] memo; 
    // normally make it static, since methods are static, thus they couldnt access non-static field, unless they are not static methods
    // non-static fields belong to objects not the class iteself
    // declare it outside of func, so we dont have to pass it to recurHelper func to make it slow
    
    public static long getAutoSaveInterval(int n) {
        memo = new long[n + 1];
        Arrays.fill(memo, -1);
        return fibRecursion(n);
    }
    
    private static long fibRecursion(int n) {
        // recursion base case
        if (n == 0) return 1l;
        if (n == 1) return 2l;
        
        // show up in the memo table, reuse it!
        if (memo[n] != -1) return memo[n];
        
        // update the memo table to reuse computed val again
        memo[n] = fibRecursion(n - 1) + fibRecursion(n - 2);
        return memo[n];
    }

}
