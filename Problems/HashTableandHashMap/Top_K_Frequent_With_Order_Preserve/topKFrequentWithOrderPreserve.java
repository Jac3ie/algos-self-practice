package HashTableandHashMap.Top_K_Frequent_With_Order_Preserve;

import java.util.*;

public class topKFrequentWithOrderPreserve {
    public static void main(String[] args) {
        // -------------------- Test Case 1 --------------------
        // Example from prompt
        List<Integer> events1 = Arrays.asList(1, 2, 1, 3, 2, 1);
        int k1 = 2;
        System.out.println("Test 1");
        System.out.println("events = " + events1);
        System.out.println("k = " + k1);
        System.out.println("Output = " + Result.getTopKFrequentEvents(events1, k1));
        System.out.println("Expected ~ [1, 2]");
        System.out.println("--------------------------------------------------");

        // -------------------- Test Case 2 --------------------
        // Tie on frequency: 4 and 5 appear 2 times, but 4 appears earlier
        List<Integer> events2 = Arrays.asList(5, 4, 5, 4, 6);
        int k2 = 2;
        System.out.println("Test 2");
        System.out.println("events = " + events2);
        System.out.println("k = " + k2);
        System.out.println("Output = " + Result.getTopKFrequentEvents(events2, k2));
        System.out.println("Expected ~ [5, 4] (same freq, 5 first appears at idx 0, 4 at idx 1)");
        System.out.println("--------------------------------------------------");

        // -------------------- Test Case 3 --------------------
        // Many ties: all freq=1, so output is purely by first appearance order
        List<Integer> events3 = Arrays.asList(9, 8, 7, 6);
        int k3 = 3;
        System.out.println("Test 3");
        System.out.println("events = " + events3);
        System.out.println("k = " + k3);
        System.out.println("Output = " + Result.getTopKFrequentEvents(events3, k3));
        System.out.println("Expected ~ [9, 8, 7]");
        System.out.println("--------------------------------------------------");

        // -------------------- Test Case 4 --------------------
        // k >= number of unique events (IMPORTANT edge case)
        // Your current code will throw IndexOutOfBoundsException unless you clamp k.
        List<Integer> events4 = Arrays.asList(1, 1, 2);
        int k4 = 10;
        System.out.println("Test 4");
        System.out.println("events = " + events4);
        System.out.println("k = " + k4);
        try {
            System.out.println("Output = " + Result.getTopKFrequentEvents(events4, k4));
            System.out.println("Expected behavior: either [1, 2] OR clamp k to unique count.");
        } catch (Exception e) {
            System.out.println("ERROR (expected with current implementation): " + e);
            System.out.println("Fix: use k = Math.min(k, keys.size()) before taking first k items.");
        }
        System.out.println("--------------------------------------------------");
    }
}

class Result {

    /*
     * Complete the 'getTopKFrequentEvents' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY events
     *  2. INTEGER k
     */

    public static List<Integer> getTopKFrequentEvents(List<Integer> events, int k) {
        List<Integer> result = new ArrayList<>();
        int len = events.size();
        if (len == 0) return result;
        
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> firstIdx = new HashMap<>();
        
        // update both maps, to record the frequency & first index
        for (int i = 0; i < len; i++) {
            int curr = events.get(i);
            firstIdx.putIfAbsent(curr, i);
            freq.put(curr, freq.getOrDefault(curr, 0) + 1);
        }
        
        // use a list to store the keys of map
        List<Integer> keys = new ArrayList<>(firstIdx.keySet());
        
        // define a comparator combining two ordering
        // frequency DESC & firstIdx ASC if tie on frequency
        
        /* first way - using manual comparator */
        keys.sort((Integer a, Integer b) -> {
            int freqA = freq.get(a);
            int freqB = freq.get(b);
            
            if (freqA == freqB) return Integer.compare(firstIdx.get(a), firstIdx.get(b));
            return Integer.compare(freqB, freqA); 
        });
        
        /* second way - using comparingInt */
        keys.sort(
            Comparator.comparingInt(a -> freq.get(a)).reversed()
                .thenComparingInt(a -> firstIdx.get(a))
        );
        
        // get first k elts
        for (int i = 0; i < k; i++){
            result.add(keys.get(i));
        }
        
        return result;
    }

}
