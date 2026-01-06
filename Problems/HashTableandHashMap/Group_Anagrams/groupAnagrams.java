package HashTableandHashMap.Group_Anagrams;

import java.util.*;

public class groupAnagrams {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------------- Test case 1 ----------------
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Test 1 input  : " + Arrays.toString(strs1));
        List<List<String>> res1 = sol.groupAnagrams(strs1);
        System.out.println("Test 1 output : " + res1);
        System.out.println("------------------------------------------------");

        // ---------------- Test case 2 ----------------
        String[] strs2 = {""};
        System.out.println("Test 2 input  : " + Arrays.toString(strs2));
        List<List<String>> res2 = sol.groupAnagrams(strs2);
        System.out.println("Test 2 output : " + res2);
        System.out.println("------------------------------------------------");

        // ---------------- Test case 3 ----------------
        String[] strs3 = {"a"};
        System.out.println("Test 3 input  : " + Arrays.toString(strs3));
        List<List<String>> res3 = sol.groupAnagrams(strs3);
        System.out.println("Test 3 output : " + res3);
        System.out.println("------------------------------------------------");

        // ---------------- Test case 4 (multiple duplicates) ----------------
        String[] strs4 = {"ab", "ba", "ab", "bA".toLowerCase(), "abc", "bca", "cab"};
        System.out.println("Test 4 input  : " + Arrays.toString(strs4));
        List<List<String>> res4 = sol.groupAnagrams(strs4);
        System.out.println("Test 4 output : " + res4);
        System.out.println("------------------------------------------------");
    }
}

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramGroup = new HashMap<>();

        for (String s : strs) {
            char[] chrArr = s.toCharArray();
            // for anagrams, after sort they have the same signature key
            Arrays.sort(chrArr);
            // use contructor way to form a string from a char[]
            String key = new String(chrArr);
            anagramGroup.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(anagramGroup.values());
    }
}
