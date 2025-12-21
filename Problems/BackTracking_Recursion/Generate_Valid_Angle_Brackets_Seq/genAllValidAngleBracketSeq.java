package BackTracking_Recursion.Generate_Valid_Angle_Brackets_Seq;

import java.util.*;

public class genAllValidAngleBracketSeq {
    public static void main(String[] args) {
        // Run several test cases
        int[] tests = {0, 1, 2, 3, 4};

        for (int t = 0; t < tests.length; t++) {
            int n = tests[t];
            List<String> ans = Result.generateAngleBracketSequences(n);

            System.out.println("====================================");
            System.out.println("Test " + (t + 1) + ": n = " + n);
            System.out.println("Total sequences: " + ans.size());

            // Print each sequence on its own line with an index
            for (int i = 0; i < ans.size(); i++) {
                System.out.println("  " + (i + 1) + ") " + ans.get(i));
            }
        }
        System.out.println("====================================");
    }
}

class Result {

    /*
     * Complete the 'generateAngleBracketSequences' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts INTEGER n as parameter.
     */

    public static List<String> generateAngleBracketSequences(int n) {
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        
        backTrack(sb, 0, 0, result, n);
        return result;
    }
    
    private static void backTrack(StringBuilder sb, int openUsed, int closeUsed, List<String> result, int n) {
        // base case of recursion
        if (openUsed == n && closeUsed == n) {
            // when the cond met, record sb to remember this state
            result.add(sb.toString());
            
            return; // need to return to prev level
        }
        
        if (openUsed < n) { // still left openUsed
            sb.append("<");
            
            // recurse to deeper from this direction to explore this path
            backTrack(sb, openUsed + 1, closeUsed, result, n);
            
            // undo the move we made in this path, after execution of this line
            // if closedUsed < openUsed, it will try another way by going into the underneath block
            sb.deleteCharAt(sb.length() - 1);
        }
        
        if (closeUsed < openUsed) {
            sb.append(">");
            
            // also recurse on this path, to explore every posibilities
            backTrack(sb, openUsed, closeUsed + 1, result, n);
            
            // undo the move similarly
            sb.deleteCharAt(sb.length() - 1);
        }
    } 

}