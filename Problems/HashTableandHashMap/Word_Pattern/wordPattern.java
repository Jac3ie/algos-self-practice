package HashTableandHashMap.Word_Pattern;

import java.util.*;

public class wordPattern {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ----------------------------
        // Test 1: Basic true case
        // pattern = "abba"
        // s       = "dog cat cat dog"
        // Expected: true
        // ----------------------------
        String pattern1 = "abba";
        String s1 = "dog cat cat dog";
        boolean ans1 = sol.wordPattern(pattern1, s1);
        System.out.println("Test 1");
        System.out.println("  pattern : " + pattern1);
        System.out.println("  s       : " + s1);
        System.out.println("  expected: true");
        System.out.println("  actual  : " + ans1);
        System.out.println("----------------------------------");

        // ----------------------------
        // Test 2: Value mismatch (should be false)
        // pattern = "abba"
        // s       = "dog cat cat fish"
        // Expected: false
        // ----------------------------
        String pattern2 = "abba";
        String s2 = "dog cat cat fish";
        boolean ans2 = sol.wordPattern(pattern2, s2);
        System.out.println("Test 2");
        System.out.println("  pattern : " + pattern2);
        System.out.println("  s       : " + s2);
        System.out.println("  expected: false");
        System.out.println("  actual  : " + ans2);
        System.out.println("----------------------------------");

        // ----------------------------
        // Test 3: Two letters map to same word (bijection violated)
        // pattern = "abba"
        // s       = "dog dog dog dog"
        // a->dog, b->dog (not allowed)
        // Expected: false
        // ----------------------------
        String pattern3 = "abba";
        String s3 = "dog dog dog dog";
        boolean ans3 = sol.wordPattern(pattern3, s3);
        System.out.println("Test 3");
        System.out.println("  pattern : " + pattern3);
        System.out.println("  s       : " + s3);
        System.out.println("  expected: false");
        System.out.println("  actual  : " + ans3);
        System.out.println("----------------------------------");

        // ----------------------------
        // Test 4: Length mismatch
        // pattern = "abc"
        // s       = "one two"
        // Expected: false
        // ----------------------------
        String pattern4 = "abc";
        String s4 = "one two";
        boolean ans4 = sol.wordPattern(pattern4, s4);
        System.out.println("Test 4");
        System.out.println("  pattern : " + pattern4);
        System.out.println("  s       : " + s4);
        System.out.println("  expected: false");
        System.out.println("  actual  : " + ans4);
        System.out.println("----------------------------------");

        // ----------------------------
        // Test 5: Single character + single word
        // pattern = "a"
        // s       = "hello"
        // Expected: true
        // ----------------------------
        String pattern5 = "a";
        String s5 = "hello";
        boolean ans5 = sol.wordPattern(pattern5, s5);
        System.out.println("Test 5");
        System.out.println("  pattern : " + pattern5);
        System.out.println("  s       : " + s5);
        System.out.println("  expected: true");
        System.out.println("  actual  : " + ans5);
        System.out.println("----------------------------------");
    }
}

class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] arr = s.split(" ");
        if (pattern.length() != arr.length) return false;

        Map<Character, String> psMap = new HashMap<>();
        Map<String, Character> spMap = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++){
            char currChr = pattern.charAt(i);
            String currStr = arr[i];

            if (psMap.get(currChr) == null) {
                if (spMap.get(currStr) != null) return false;
                psMap.put(currChr, currStr);
                spMap.put(currStr, currChr);
            } else {
                if (!psMap.get(currChr).equals(currStr)) return false;
            }
        }
        return true;
    }
}
