package Sliding_Window.Longest_Alt_Binary_Substring_With_kFilps;

public class longestAltBinarySubstrWithKFlips {
    public static void main(String[] args) {
        // Case 1: Already alternating, k = 0
        String s1 = "010101";
        int k1 = 0;
        int ans1 = Result.longestAlternatingSubstring(s1, k1);

        System.out.println("========================================");
        System.out.println("CASE 1");
        System.out.println("Input s  : \"" + s1 + "\"");
        System.out.println("Input k  : " + k1);
        System.out.println("Expected : 6");
        System.out.println("Actual   : " + ans1);
        System.out.println("PASS?    : " + (ans1 == 6));
        System.out.println("========================================\n");

        // Case 2: k is enough to cover whole string
        String s2 = "1001101";
        int k2 = 2;
        int ans2 = Result.longestAlternatingSubstring(s2, k2);

        System.out.println("========================================");
        System.out.println("CASE 2");
        System.out.println("Input s  : \"" + s2 + "\"");
        System.out.println("Input k  : " + k2);
        System.out.println("Expected : 7");
        System.out.println("Actual   : " + ans2);
        System.out.println("PASS?    : " + (ans2 == 7));
        System.out.println("========================================\n");

        // Case 3: Limited k, answer should be <= length
        String s3 = "111000";
        int k3 = 1;
        int ans3 = Result.longestAlternatingSubstring(s3, k3);

        System.out.println("========================================");
        System.out.println("CASE 3");
        System.out.println("Input s  : \"" + s3 + "\"");
        System.out.println("Input k  : " + k3);
        System.out.println("Expected : <= " + s3.length() + " (best valid window)");
        System.out.println("Actual   : " + ans3);
        System.out.println("In range?: " + (0 <= ans3 && ans3 <= s3.length()));
        System.out.println("========================================\n");

        // Case 4: Empty string
        String s4 = "";
        int k4 = 3;
        int ans4 = Result.longestAlternatingSubstring(s4, k4);

        System.out.println("========================================");
        System.out.println("CASE 4");
        System.out.println("Input s  : \"" + s4 + "\"");
        System.out.println("Input k  : " + k4);
        System.out.println("Expected : 0");
        System.out.println("Actual   : " + ans4);
        System.out.println("PASS?    : " + (ans4 == 0));
        System.out.println("========================================\n");
    }
}

class Result {

    /*
     * Complete the 'longestAlternatingSubstring' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER k
     */

    public static int longestAlternatingSubstring(String s, int k) {
        int len = s.length();
        if (len == 0) return 0;
        
        // set up sliding window
        int left = 0;
        int mismatch0 = 0;
        int mismatch1 = 0;
        int maxWindowSize = 0;
        
        for (int right = 0; right < len; right++){
            char currChr = s.charAt(right);
            
            // list two patterns expectation
            char expected0 = (right % 2 == 0) ? '0' : '1';
            char expected1 = (right % 2 == 0) ? '1' : '0';
            
            if (expected0 != currChr) mismatch0++;
            if (expected1 != currChr) mismatch1++;
            
            // make sure min(mismatch0, mismatch1) dont exceed k, so we get valid maxWindowSize
            // as long as k is not exceeded, then we keep grow right to maximize windowSize
            while (Math.min(mismatch0, mismatch1) > k) {
                char leftChr = s.charAt(left);
                
                // check the expect and leftPosChr, if equal then mismatched--
                char expectedLeft0 = (left % 2 == 0) ? '0' : '1';
                char expectedLeft1 = (left % 2 == 0) ? '1' : '0';
                
                // decrement it when its also mismatched
                if (expectedLeft0 != leftChr) mismatch0--;
                if (expectedLeft1 != leftChr) mismatch1--;
                
                left++;
            }
            
            maxWindowSize = Math.max(maxWindowSize, right - left + 1);
        }
        
        return maxWindowSize;
    }

}