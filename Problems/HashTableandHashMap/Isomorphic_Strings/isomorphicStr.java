package HashTableandHashMap.Isomorphic_Strings;

import java.util.*;

public class isomorphicStr {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------------- Test Case 1 ----------------
        // Expected: true  (egg -> add)
        String s1 = "egg", t1 = "add";
        boolean r1 = sol.isIsomorphic(s1, t1);
        System.out.println("Test 1");
        System.out.println("  s = " + s1);
        System.out.println("  t = " + t1);
        System.out.println("  result = " + r1 + " (expected true)");
        System.out.println("----------------------------------");

        // ---------------- Test Case 2 ----------------
        // Expected: false (foo -> bar, because o can't map to both a and r)
        String s2 = "foo", t2 = "bar";
        boolean r2 = sol.isIsomorphic(s2, t2);
        System.out.println("Test 2");
        System.out.println("  s = " + s2);
        System.out.println("  t = " + t2);
        System.out.println("  result = " + r2 + " (expected false)");
        System.out.println("----------------------------------");

        // ---------------- Test Case 3 ----------------
        // Expected: false (ab -> aa, because a and b would both map to a)
        String s3 = "ab", t3 = "aa";
        boolean r3 = sol.isIsomorphic(s3, t3);
        System.out.println("Test 3");
        System.out.println("  s = " + s3);
        System.out.println("  t = " + t3);
        System.out.println("  result = " + r3 + " (expected false)");
        System.out.println("----------------------------------");

        // ---------------- Test Case 4 ----------------
        // Expected: true (paper -> title)
        String s4 = "paper", t4 = "title";
        boolean r4 = sol.isIsomorphic(s4, t4);
        System.out.println("Test 4");
        System.out.println("  s = " + s4);
        System.out.println("  t = " + t4);
        System.out.println("  result = " + r4 + " (expected true)");
        System.out.println("----------------------------------");

        // ---------------- Test Case 5 ----------------
        // Expected: false (different lengths)
        String s5 = "abc", t5 = "ab";
        boolean r5 = sol.isIsomorphic(s5, t5);
        System.out.println("Test 5");
        System.out.println("  s = " + s5);
        System.out.println("  t = " + t5);
        System.out.println("  result = " + r5 + " (expected false)");
        System.out.println("----------------------------------");

        // ---------------- Test Case 6 ----------------
        // Expected: true (both empty)
        String s6 = "", t6 = "";
        boolean r6 = sol.isIsomorphic(s6, t6);
        System.out.println("Test 6");
        System.out.println("  s = \"" + s6 + "\"");
        System.out.println("  t = \"" + t6 + "\"");
        System.out.println("  result = " + r6 + " (expected true)");
        System.out.println("----------------------------------");
    }
}

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> stMap = new HashMap<>();
        Map<Character, Character> tsMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++){
            char currS = s.charAt(i), currT = t.charAt(i);

            if (stMap.get(currS) == null){
                if (tsMap.get(currT) != null) return false;
                stMap.put(currS, currT);
                tsMap.put(currT, currS);
            } else {
                if (stMap.get(currS) != currT) return false;
            }
            
        }
        return true;
    }
}
