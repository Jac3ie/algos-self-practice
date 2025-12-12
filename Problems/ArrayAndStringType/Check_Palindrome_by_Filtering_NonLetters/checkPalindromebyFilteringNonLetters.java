package ArrayAndStringType.Check_Palindrome_by_Filtering_NonLetters;

public class checkPalindromebyFilteringNonLetters {
    public static void main(String[] args) {
        String[] tests = {
            "",                    // empty -> true
            "a",                   // single char -> true
            "A1b2B1a",             // letters only: "AbBa" -> true
            "A1b2B!",              // letters "AbB" -> false
            "code = A1b2B1a",      // example style -> true
            "No 'x' in Nixon",     // classic palindrome -> true
            "Hello, World!",       // not palindrome -> false
        };

        for (String s : tests) {
            boolean res = Result.isAlphabeticPalindrome(s);
            System.out.println("Input: \"" + s + "\" -> " + res);
        }
    }
}

class Result {

    /*
     * Complete the 'isAlphabeticPalindrome' function below.
     *
     * The function is expected to return a BOOLEAN.
     * The function accepts STRING code as parameter.
     */

    public static boolean isAlphabeticPalindrome(String code) {
        int left = 0;
        int right = code.length() - 1;
        while (left < right) {
            char leftChr = code.charAt(left);
            char rightChr = code.charAt(right);
            if (!Character.isAlphabetic(leftChr)){
                left++;
                continue;
            }
            if (!Character.isAlphabetic(rightChr)){
                right--;
                continue;
            }
            if (Character.toLowerCase(leftChr) != Character.toLowerCase(rightChr)) return false;
            right--;
            left++;
        }
        return true;
    }

}
