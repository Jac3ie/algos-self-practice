package Greedy.Max_Non_Overlap_Meeting;

import java.util.*;

public class maxNonOverlapMeeting {
    public static void main(String[] args) {
        List<List<List<Integer>>> testCases = new ArrayList<>();

        // Test 1: classic example
        testCases.add(Arrays.asList(
            Arrays.asList(1, 3),
            Arrays.asList(2, 4),
            Arrays.asList(3, 5),
            Arrays.asList(0, 6),
            Arrays.asList(5, 7),
            Arrays.asList(8, 9)
        ));

        // Test 2: already non-overlapping
        testCases.add(Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(2, 3),
            Arrays.asList(3, 4),
            Arrays.asList(4, 5)
        ));

        // Test 3: all overlap heavily
        testCases.add(Arrays.asList(
            Arrays.asList(1, 10),
            Arrays.asList(2, 9),
            Arrays.asList(3, 8),
            Arrays.asList(4, 7)
        ));

        // Test 4: same end times (edge-ish)
        testCases.add(Arrays.asList(
            Arrays.asList(1, 3),
            Arrays.asList(2, 3),
            Arrays.asList(0, 3),
            Arrays.asList(3, 5)
        ));

        // Test 5: empty input
        testCases.add(new ArrayList<>());

        int caseNo = 1;
        for (List<List<Integer>> meetings : testCases) {
            // make a copy because your function sorts in-place
            List<List<Integer>> copy = new ArrayList<>();
            for (List<Integer> m : meetings) copy.add(new ArrayList<>(m));

            int ans = Result.maximizeNonOverlappingMeetings(copy);

            System.out.println("Case " + (caseNo++));
            System.out.println("  meetings = " + meetings);
            System.out.println("  max non-overlapping = " + ans);
            System.out.println("------------------------------------");
        }
    }
}

class Result {

    /*
     * Complete the 'maximizeNonOverlappingMeetings' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY meetings as parameter.
     */

    public static int maximizeNonOverlappingMeetings(List<List<Integer>> meetings) {
        int count = 0;
        // filter base cases
        int len = meetings.size();
        if (len == 0) return count;
        
        // sort the meetings by end time ASC
        // TimeSort => O(NlogN)
        meetings.sort(Comparator.comparingInt((List<Integer> a) -> a.get(1)));
        
        // apply greedy here by use the ealiest end time first
        int endTimeMax = meetings.get(0).get(1);
        count++;
        for (int i = 1; i < len; i++){
            int currEndTime = meetings.get(i).get(1);
            int currStartTime = meetings.get(i).get(0);
            
            // choose as many meetings as possible
            // by selecting meetings endTime as many as possible
            // so we care about only strictly >
            if (currEndTime > endTimeMax){
                // check start time to ensure valid
                // less than endTimeMax will be less meetings => invalid
                if (currStartTime >= endTimeMax) {
                    count++;
                    endTimeMax = currEndTime;
                }
            }
        }
        return count;
    }

}
