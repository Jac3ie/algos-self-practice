package TwoPointersType.Remove_K_Range_elt;

import java.util.*;

public class removeKRangeElt {
    public static void main(String[] args) {

        // Instance 1: Button bounce (ms)
        List<Integer> buttonClicks = new ArrayList<>(Arrays.asList(
                0, 10, 20, 150, 160, 400
        ));
        int K1 = 100;

        // Instance 2: Sensor noise (ms)
        List<Integer> sensorPings = new ArrayList<>(Arrays.asList(
                5, 80, 90, 200, 260, 270, 800
        ));
        int K2 = 50;

        // Instance 3: Rate-limited API calls (seconds)
        // (Same logic; just different unit.)
        List<Integer> apiCalls = new ArrayList<>(Arrays.asList(
                1, 2, 2, 3, 10, 12, 20
        ));
        int K3 = 5;

        runCase("Button Clicks", buttonClicks, K1);
        runCase("Sensor Pings", sensorPings, K2);
        runCase("API Calls", apiCalls, K3);
    }
    
    private static void runCase(String name, List<Integer> timestamps, int K) {
        System.out.println("==== " + name + " ====");
        System.out.println("K = " + K);
        System.out.println("Before: " + timestamps);

        int remaining = Result.debounceTimestamps(timestamps, K);

        System.out.println("After : " + timestamps);
        System.out.println("Remain: " + remaining);
        System.out.println();
    }

}

class Result {

    /*
     * Complete the 'debounceTimestamps' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY timestamps
     *  2. INTEGER K
     */

    public static int debounceTimestamps(List<Integer> timestamps, int K) {
        // base case filter chips
        int len = timestamps.size();
        if (len == 0 || len == 1) return len;
        
        Iterator<Integer> it = timestamps.iterator();
        int left = it.next();
        while (it.hasNext()) {
            int right = it.next();
            if (right - left < K) {
                // left reamin the same, remove right
                it.remove();
            } else {
                // move left to right(val), and continue
                left = right;
            }
        }
        return timestamps.size();
    }

}