package HashTableandHashMap.Check_Valid_Anagram;

import java.util.HashMap;
import java.util.Map;

public class CheckValidAnagram {
    public static void main(String[] args) {
        String[] sTests = {
            "anagram",
            "rat",
            "Listen",
            "Triangle",
            "AABBCC",
            "aaab",
            "",
            "a"
        };

        String[] tTests = {
            "nagaram",   // anagram
            "car",       // not anagram
            "Silent",    // anagram (case-sensitive check will fail)
            "Integral",  // anagram (case-sensitive)
            "ABCABC",    // anagram
            "abaa",      // anagram
            "",          // anagram
            "b"          // not anagram
        };

        for (int i = 0; i < sTests.length; i++) {
            int result = Result.isAnagram(sTests[i], tTests[i]);
            System.out.println("Test " + (i + 1) + ":");
            System.out.println("  s = \"" + sTests[i] + "\"");
            System.out.println("  t = \"" + tTests[i] + "\"");
            System.out.println("  Is anagram? " + (result == 1 ? "YES" : "NO"));
            System.out.println("-----------------------------------------");
        }
    }
}

class Result {

    /*
     * Complete the 'isAnagram' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. STRING t
     */

    public static int isAnagram(String s, String t) {
        // length not the same, return imm
        if (s.length() != t.length()) return 0;
        
        // build a frequency map from the source s
        Map<Character, Integer> freMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            freMap.put(s.charAt(i), freMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        
        // check existence
        for (int i = 0; i < t.length(); i++){
            char currC = t.charAt(i);
            
            // dont have this key / after subtraction become negative, not anagram
            // use Integer instead of int since its optional null
            Integer currVal = freMap.get(currC);
            if (currVal == null || currVal == 0) return 0;
            freMap.put(currC, currVal - 1);
        }
        
        return 1;

    }

}
