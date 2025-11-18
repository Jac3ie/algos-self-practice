package DP_Fundamentals.Huge_Fibnacci;

public class HugeFibDPWay {
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
     
     
     * DP way, which means keep updating the latest values and ignore the previous ones,
     * No need to use memo table and recursion, simpliest and easiest => runs in O(n), Spaces in O(1);
     */
    
    public static long getAutoSaveInterval(int n) {
        if (n == 0) return 1l;
        if (n == 1) return 2l;
        
        // keep remembering latest two values
        long a = 1l;
        long b = 2l;
        
        for (int i = 2; i <= n; i++){
            long c = a + b; // compute f(n) = f(n - 2) + f(n - 1) here
            a = b;
            b = c; // b will contain curr n result => f(n)
        }
        return b;
    }
    
}
