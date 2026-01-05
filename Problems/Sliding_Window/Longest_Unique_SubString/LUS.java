package Sliding_Window.Longest_Unique_SubString;

import java.util.*;

public class LUS {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // -------------------- Test Case 1 --------------------
        String s1 = "abcabcbb";
        int ans1 = sol.lengthOfLongestSubstring(s1);
        System.out.println("Test 1");
        System.out.println("  Input : \"" + s1 + "\"");
        System.out.println("  Output: " + ans1);
        System.out.println("  Expect: 3  (\"abc\")");
        System.out.println("-----------------------------------");

        // -------------------- Test Case 2 --------------------
        String s2 = "bbbbb";
        int ans2 = sol.lengthOfLongestSubstring(s2);
        System.out.println("Test 2");
        System.out.println("  Input : \"" + s2 + "\"");
        System.out.println("  Output: " + ans2);
        System.out.println("  Expect: 1  (\"b\")");
        System.out.println("-----------------------------------");

        // -------------------- Test Case 3 --------------------
        String s3 = "pwwkew";
        int ans3 = sol.lengthOfLongestSubstring(s3);
        System.out.println("Test 3");
        System.out.println("  Input : \"" + s3 + "\"");
        System.out.println("  Output: " + ans3);
        System.out.println("  Expect: 3  (\"wke\")");
        System.out.println("-----------------------------------");

        // -------------------- Test Case 4 --------------------
        String s4 = ""; // empty string edge case
        int ans4 = sol.lengthOfLongestSubstring(s4);
        System.out.println("Test 4");
        System.out.println("  Input : \"" + s4 + "\"");
        System.out.println("  Output: " + ans4);
        System.out.println("  Expect: 0");
        System.out.println("-----------------------------------");

        // -------------------- Test Case 5 --------------------
        String s5 = "abba"; // common tricky case
        int ans5 = sol.lengthOfLongestSubstring(s5);
        System.out.println("Test 5");
        System.out.println("  Input : \"" + s5 + "\"");
        System.out.println("  Output: " + ans5);
        System.out.println("  Expect: 2  (\"ab\" or \"ba\")");
        System.out.println("-----------------------------------");
    }
}

class Solution {
    public int lengthOfLongestSubstring(String s) {
        /**
        use a sliding window to keep track of longest unique substr
        and use a hashmap to remember the last occurance of each char        
        */
        int len = s.length();
        if (len == 0) return 0;

        int left = 0;
        Map<Character, Integer> occMap = new HashMap<>();
        int maxSoFar = 0;
        
        for (int right = 0; right < len; right++) {
            char currChr = s.charAt(right);
            Integer currChrLoc = occMap.get(currChr);

            // Integer can store null & the loc need to be >= left,
            // the currChrLoc may be right on the left
            if (currChrLoc != null && currChrLoc >= left) {
                maxSoFar = Math.max(right - left, maxSoFar); // dont +1 since we are on a duplicate alr
                left = currChrLoc + 1;
            }

            // unconditionally do
            occMap.put(currChr, right);
            maxSoFar = Math.max(right - left + 1, maxSoFar);
        }

        return maxSoFar;
    }
}
