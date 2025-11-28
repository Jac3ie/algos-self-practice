package Sliding_Window.Longest_Unique_Substring_Length_InSession;

import java.util.*;

public class LUSLengthInSession {
    public static void main(String[] args) {
        // Example session log:
        //  - Session 1: "ABCADE"
        //  - Session 2: "XYZX"
        //  - Session 3: "BBBB"
        String sessionString = "ABCADE*XYZX*BBBB";

        int longest = Result.maxDistinctSubstringLengthInSessions(sessionString);

        System.out.println("Session string: " + sessionString);
        System.out.println("Longest distinct substring length across all sessions = " + longest);
        // Expected output: 5
        // Explanation:
        //   "ABCADE" -> "BCADE" (length 5)
        //   "XYZX"   -> "XYZ"   (length 3)
        //   "BBBB"   -> "B"     (length 1)
        //   max = 5
    }
}

class Result {

    /*
     * Complete the 'maxDistinctSubstringLengthInSessions' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING sessionString as parameter.
     */

    public static int maxDistinctSubstringLengthInSessions(String sessionString) {
        String[] sessions = sessionString.split("\\*");
        
        // record longest length
        int longestSoFar = 0;
        for(String session : sessions){
            if (session.equals("")) continue;
            
            // setting up two pointers for the sliding window
            int left = 0;
            // setting up a Map to trace the pos of every occured char
            Map<Character, Integer> lastPos = new HashMap<>();
            for (int i = 0; i < session.length(); i++){
                // use current i as right pointer idx implicitly
                char currC = session.charAt(i);
                Integer lastPosCurrC = lastPos.get(currC);
                
                // if Map lookup currChar is there, more importantly, lastPosCurrC >= left
                // think of "abcca", at second a we dont want to shrink sliding window
                // we want something truly staying in current sliding window so check uniqueness make sense
                if (lastPosCurrC != null && lastPosCurrC >= left) left = lastPosCurrC + 1;
                    
                // update the Map; calculate currentWindowSize; update longestSoFar;
                lastPos.put(currC, i);
                int currWindowSize = i - left + 1;
                longestSoFar = Math.max(longestSoFar, currWindowSize);
            }
        }
        return longestSoFar;

    }

}
