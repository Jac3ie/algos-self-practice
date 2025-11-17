package ArrayAndStringType.Merge_and_Sort_Intervals;

import java.util.*;

public class MergeAndSortIntervals {
    public static void main(String[] args) {
    List<List<List<Integer>>> tests = Arrays.asList(
        // Basic examples
        Arrays.asList(Arrays.asList(1, 3), Arrays.asList(2, 6), Arrays.asList(8, 10), Arrays.asList(15, 18)),
        Arrays.asList(Arrays.asList(1, 4), Arrays.asList(4, 5)),
        
        // Edge cases
        Arrays.asList(),                                 // empty
        Arrays.asList(Arrays.asList(5, 7)),              // one interval
        
        // No overlap
        Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6)),
        
        // Full containment
        Arrays.asList(Arrays.asList(1, 10), Arrays.asList(2, 3), Arrays.asList(4, 6), Arrays.asList(8, 9)),
        
        // Touching boundaries
        Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4)),
        
        // Unsorted input
        Arrays.asList(Arrays.asList(10, 12), Arrays.asList(1, 3), Arrays.asList(2, 6), Arrays.asList(8, 9)),
        
        // Mixed overlapping + non-overlapping
        Arrays.asList(Arrays.asList(1,4), Arrays.asList(0,2), Arrays.asList(3,5))
    );
    
    int caseNum = 1;
    for (List<List<Integer>> input : tests) {

        // Deep copy input (since your algorithm modifies the list you pass in)
        List<List<Integer>> copy = new ArrayList<>();
        for (List<Integer> pair : input) {
            copy.add(new ArrayList<>(pair));
        }

        List<List<Integer>> result = Result.mergeHighDefinitionIntervals(copy);

        System.out.println("Test Case " + caseNum++);
        System.out.println("Input:    " + input);
        System.out.println("Result:   " + result);
        System.out.println("----------------------------------");
    }
}
}

class Result {

    /*
     * Complete the 'mergeHighDefinitionIntervals' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts 2D_INTEGER_ARRAY intervals as parameter.
     */

    public static List<List<Integer>> mergeHighDefinitionIntervals(List<List<Integer>> intervals) {
        // handle base cases
        if (intervals.size() == 0) return new ArrayList<>(); // able to return [] as empty case of [[]] and meet the List<List<Integer>>
        if (intervals.size() == 1) return intervals;
        
        // sort intervals by start time
        intervals.sort(Comparator.comparingInt((List<Integer> a) -> a.get(0)));
        
        // forming output
        List<List<Integer>> op = new ArrayList<>();
        op.add(intervals.get(0));
        
        for (int i = 1; i < intervals.size(); i++){
            int prevFirst = op.get(op.size() - 1).get(0);
            int prevLast = op.get(op.size() - 1).get(1);
            int laterFirst = intervals.get(i).get(0);
            int laterLast = intervals.get(i).get(1);
            
            if (prevLast >= laterFirst) {
                // in this case need to replace the last merged one with new one
                // need to set the range up to the greatest number between[prevLast,laterLast]
                op.set(op.size() - 1, Arrays.asList(prevFirst, Math.max(prevLast, laterLast)));
            } else {
                op.add(Arrays.asList(laterFirst,laterLast));
            }
        }
        
        return op;
    }

}
