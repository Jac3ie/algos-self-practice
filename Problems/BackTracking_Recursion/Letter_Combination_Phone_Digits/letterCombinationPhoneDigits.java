package BackTracking_Recursion.Letter_Combination_Phone_Digits;

import java.util.*;

public class letterCombinationPhoneDigits {
    public static void main(String[] args) {

        // -------------------- Test 1 --------------------
        String digits1 = "23";
        System.out.println("Test 1 digits = " + digits1);
        List<String> out1 = Result.minTasksToCancelForNoConflict(digits1);
        System.out.println("Output size = " + out1.size());
        System.out.println("Output = " + out1);
        System.out.println("Expected (order): [ad, ae, af, bd, be, bf, cd, ce, cf]");
        System.out.println("--------------------------------------------------\n");

        // -------------------- Test 2 --------------------
        String digits2 = "203";
        System.out.println("Test 2 digits = " + digits2);
        List<String> out2 = Result.minTasksToCancelForNoConflict(digits2);
        System.out.println("Output size = " + out2.size());
        System.out.println("Output = " + out2);
        System.out.println("Expected (order): [a0d, a0e, a0f, b0d, b0e, b0f, c0d, c0e, c0f]");
        System.out.println("--------------------------------------------------\n");

        // -------------------- Test 3 --------------------
        String digits3 = "171";
        System.out.println("Test 3 digits = " + digits3);
        List<String> out3 = Result.minTasksToCancelForNoConflict(digits3);
        System.out.println("Output size = " + out3.size());
        System.out.println("Output = " + out3);
        System.out.println("Expected: [1p1, 1q1, 1r1, 1s1]");
        System.out.println("--------------------------------------------------\n");

        // -------------------- Test 4 --------------------
        String digits4 = "";
        System.out.println("Test 4 digits = \"\" (empty)");
        List<String> out4 = Result.minTasksToCancelForNoConflict(digits4);
        System.out.println("Output size = " + out4.size());
        System.out.println("Output = " + out4);
        System.out.println("Expected: []");
        System.out.println("--------------------------------------------------\n");
    }
}

class Result {

    /*
     * Complete the 'minTasksToCancelForNoConflict' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING digits as parameter.
     */

    public static List<String> minTasksToCancelForNoConflict(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.equals("")) return result;
        
        StringBuilder sb = new StringBuilder();
        Map<Character, String> letterMap = Map.of(
            '0', "0",
            '1', "1",
            '2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz"
        );
        int currIdx = 0;
        
        letterCombineDFS(result, sb, currIdx, letterMap, digits);
        return result;
    }
    
    private static void letterCombineDFS(List<String> result, StringBuilder sb, int currIdx, Map<Character, String> map, String digits) {
        // base case of BT
        if (currIdx == digits.length()) {
            // stop at a idx that couldnt reach: length 3 => idx 3
            result.add(sb.toString());
            return;
        }
        
        char digit = digits.charAt(currIdx);
        String mappedStr = map.get(digit);
        
        // try every combination in a loop
        for (int i = 0; i < mappedStr.length(); i++) {
            sb.append(mappedStr.charAt(i));
            
            // start DFS by going into the deeper
            letterCombineDFS(result, sb, currIdx + 1, map, digits);
            
            // undo the previous step
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}