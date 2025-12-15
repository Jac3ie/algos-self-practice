package ArrayAndStringType.Non_Identical_String_Rotation;

public class nonIdenticalStringRotation {
    public static void main(String[] args) {
        // Each test: {s1, s2, expected}
        String[][] tests = {
            {"abcd", "bcda", "true"},   // rotation by 1
            {"abcd", "dabc", "true"},   // rotation by 3
            {"aaaa", "aaaa", "false"},  // identical -> not non-trivial
            {"abcd", "abcd", "false"},  // identical -> not non-trivial
            {"abcd", "abdc", "false"},  // not a rotation
            {"abc",  "cab",  "true"},   // rotation by 2
            {"abc",  "acb",  "false"},  // not a rotation
            {"a",    "a",    "false"},  // length 1: only identical rotation exists
            {"ab",   "ba",   "true"},   // rotation by 1
            {"ab",   "abc",  "false"}   // different length
        };

        int caseNo = 1;
        for (String[] t : tests) {
            String s1 = t[0], s2 = t[1];
            boolean expected = Boolean.parseBoolean(t[2]);
            boolean got = Result.isNonTrivialRotation(s1, s2);

            System.out.println("Case " + (caseNo++));
            System.out.println("  s1 = " + s1);
            System.out.println("  s2 = " + s2);
            System.out.println("  expected = " + expected + ", got = " + got);
            System.out.println("------------------------------------");
        }
    }
}

class Result {

    /*
     * Complete the 'isNonTrivialRotation' function below.
     *
     * The function is expected to return a BOOLEAN.
     * The function accepts following parameters:
     *  1. STRING s1
     *  2. STRING s2
     */

    public static boolean isNonTrivialRotation(String s1, String s2) {
        // base case filter chip
        if (s1.equals(s2) || s1.length() != s2.length()) return false;
        
        String toConcate = "";
        int len = s1.length();
        for (int i = 0; i < len - 1; i++) { 
            // we dont need to go to len - 1 since at that point we rotate one full round,
            // which alr been filtered out
            char currChr = s1.charAt(i);
            toConcate += currChr;
            String toCompare = s1.substring(i + 1) + toConcate;
            if (toCompare.equals(s2)) return true;
        }
        
        return false;
    }

}