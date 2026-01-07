package ArrayAndStringType.Insert_Interval;

import java.util.*;

public class insertInterval {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ------------------- Test 1: Example 1 -------------------
        int[][] intervals1 = { {1, 3}, {6, 9} };
        int[] newInterval1 = { 2, 5 };
        System.out.println("Test 1");
        System.out.println("  intervals   = " + Arrays.deepToString(intervals1));
        System.out.println("  newInterval = " + Arrays.toString(newInterval1));
        int[][] out1 = sol.insert(intervals1, newInterval1);
        System.out.println("  output      = " + Arrays.deepToString(out1));
        System.out.println("  expected    = [[1, 5], [6, 9]]");
        System.out.println("--------------------------------------------------");

        // ------------------- Test 2: Example 2 -------------------
        int[][] intervals2 = { {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16} };
        int[] newInterval2 = { 4, 8 };
        System.out.println("Test 2");
        System.out.println("  intervals   = " + Arrays.deepToString(intervals2));
        System.out.println("  newInterval = " + Arrays.toString(newInterval2));
        int[][] out2 = sol.insert(intervals2, newInterval2);
        System.out.println("  output      = " + Arrays.deepToString(out2));
        System.out.println("  expected    = [[1, 2], [3, 10], [12, 16]]");
        System.out.println("--------------------------------------------------");

        // ------------------- Test 3: Empty intervals -------------------
        int[][] intervals3 = { };
        int[] newInterval3 = { 5, 7 };
        System.out.println("Test 3");
        System.out.println("  intervals   = " + Arrays.deepToString(intervals3));
        System.out.println("  newInterval = " + Arrays.toString(newInterval3));
        int[][] out3 = sol.insert(intervals3, newInterval3);
        System.out.println("  output      = " + Arrays.deepToString(out3));
        System.out.println("  expected    = [[5, 7]]");
        System.out.println("--------------------------------------------------");

        // ------------------- Test 4: Insert before all (no overlap) -------------------
        int[][] intervals4 = { {3, 5}, {7, 9} };
        int[] newInterval4 = { 0, 1 };
        System.out.println("Test 4");
        System.out.println("  intervals   = " + Arrays.deepToString(intervals4));
        System.out.println("  newInterval = " + Arrays.toString(newInterval4));
        int[][] out4 = sol.insert(intervals4, newInterval4);
        System.out.println("  output      = " + Arrays.deepToString(out4));
        System.out.println("  expected    = [[0, 1], [3, 5], [7, 9]]");
        System.out.println("--------------------------------------------------");

        // ------------------- Test 5: Insert after all (no overlap) -------------------
        int[][] intervals5 = { {1, 2}, {3, 4} };
        int[] newInterval5 = { 6, 8 };
        System.out.println("Test 5");
        System.out.println("  intervals   = " + Arrays.deepToString(intervals5));
        System.out.println("  newInterval = " + Arrays.toString(newInterval5));
        int[][] out5 = sol.insert(intervals5, newInterval5);
        System.out.println("  output      = " + Arrays.deepToString(out5));
        System.out.println("  expected    = [[1, 2], [3, 4], [6, 8]]");
        System.out.println("--------------------------------------------------");

        // ------------------- Test 6: New interval covers everything -------------------
        int[][] intervals6 = { {2, 3}, {5, 7}, {8, 10} };
        int[] newInterval6 = { 1, 12 };
        System.out.println("Test 6");
        System.out.println("  intervals   = " + Arrays.deepToString(intervals6));
        System.out.println("  newInterval = " + Arrays.toString(newInterval6));
        int[][] out6 = sol.insert(intervals6, newInterval6);
        System.out.println("  output      = " + Arrays.deepToString(out6));
        System.out.println("  expected    = [[1, 12]]");
        System.out.println("--------------------------------------------------");
    }
}

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int len = intervals.length;
        int i = 0;

        // for currEnd < newIntervalStart, keep adding curr to result
        while (i < len && intervals[i][1] < newInterval[0]){
            result.add(intervals[i]);
            i++;
        }

        // at this point, currEnd >= newIntervalStart
        // so if currStart <= newIntervalEnd, merge needed!
        // we make decisions by min/max
        while (i < len && intervals[i][0] <= newInterval[1]){
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }

        result.add(newInterval);

        // deal with the rest if any
        while (i < len){
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }
}