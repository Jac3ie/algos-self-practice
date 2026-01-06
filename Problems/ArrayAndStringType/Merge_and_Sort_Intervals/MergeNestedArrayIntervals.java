package ArrayAndStringType.Merge_and_Sort_Intervals;

import java.util.*;

public class MergeNestedArrayIntervals {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // =========================
        // Case 1: Standard overlaps
        // Input : [[1,3],[2,6],[8,10],[15,18]]
        // Expect: [[1,6],[8,10],[15,18]]
        // =========================
        int[][] intervals1 = {
            {1, 3},
            {2, 6},
            {8, 10},
            {15, 18}
        };

        System.out.println("Case 1 input : " + Arrays.deepToString(intervals1));
        int[][] merged1 = sol.merge(intervals1);
        System.out.println("Case 1 output: " + Arrays.deepToString(merged1));
        System.out.println();

        // =========================
        // Case 2: Touching intervals (end == start) counts as overlap in your logic
        // Input : [[1,4],[4,5]]
        // Expect: [[1,5]]
        // =========================
        int[][] intervals2 = {
            {1, 4},
            {4, 5}
        };

        System.out.println("Case 2 input : " + Arrays.deepToString(intervals2));
        int[][] merged2 = sol.merge(intervals2);
        System.out.println("Case 2 output: " + Arrays.deepToString(merged2));
        System.out.println();

        // =========================
        // Case 3: Unsorted + contained intervals
        // Input : [[5,7],[1,10],[2,3],[11,12]]
        // Expect: [[1,10],[11,12]]
        // Explanation:
        //   [1,10] covers [2,3] and overlaps [5,7]
        // =========================
        int[][] intervals3 = {
            {5, 7},
            {1, 10},
            {2, 3},
            {11, 12}
        };

        System.out.println("Case 3 input : " + Arrays.deepToString(intervals3));
        int[][] merged3 = sol.merge(intervals3);
        System.out.println("Case 3 output: " + Arrays.deepToString(merged3));
        System.out.println();

        // =========================
        // Case 4: Single interval
        // Input : [[2,3]]
        // Expect: [[2,3]]
        // =========================
        int[][] intervals4 = {
            {2, 3}
        };

        System.out.println("Case 4 input : " + Arrays.deepToString(intervals4));
        int[][] merged4 = sol.merge(intervals4);
        System.out.println("Case 4 output: " + Arrays.deepToString(merged4));
        System.out.println();
    }
}

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) return intervals;

        // sort them by the int[i][0] so we deal the interval in asending order
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // use dynamic List first
        List<int[]> toMerged = new ArrayList<>();
        toMerged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int leftStart = toMerged.get(toMerged.size() - 1)[0];
            int leftEnd = toMerged.get(toMerged.size() - 1)[1];
            int rightStart = intervals[i][0];
            int rightEnd = intervals[i][1];

            if (rightStart <= leftEnd) {
                toMerged.set(toMerged.size() - 1, new int[]{leftStart, Math.max(leftEnd, rightEnd)});
            } else {
                toMerged.add(intervals[i]);
            }
        }
        return toMerged.toArray(new int[toMerged.size()][]);
    }
}