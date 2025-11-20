package StackAndQueue.ValidateProperlyNestedBrackets;

import java.util.*;

public class validateNestedBrackets {
    public static void main(String[] args) {

        String[] tests = {
            "if (a[0] > b[1]) { doSomething(); }",
            "() {} []",
            "( [ ] )",
            "([{}])",
            "([)]",               // invalid
            "((())",              // invalid
            "int x = 42;",        // no brackets -> valid
            "",                   // empty string -> valid
            "{[()]} extra text",  // valid
            "{[)]}"               // invalid
        };

        for (String s : tests) {
            boolean result = Result.areBracketsProperlyMatched(s);
            System.out.println("Input: \"" + s + "\"");
            System.out.println("Properly nested? → " + result);
            System.out.println("-----------------------------------");
        }
    }
}

class Result {

    /*
     * Complete the 'areBracketsProperlyMatched' function below.
     *
     * The function is expected to return a BOOLEAN.
     * The function accepts STRING code_snippet as parameter.
     * 
     * Overall time complexity: O(n)
     * Extra space for stack in worst case: O(n).
     */

    public static boolean areBracketsProperlyMatched(String code_snippet) {
        Deque<Character> dq = new ArrayDeque<>();
        Map<Character, Character> map = Map.of(
            '(', ')',
            '[', ']',
            '{', '}'
        );
        
        for (char c : code_snippet.toCharArray()){
            if (c == '(' || c == '[' || c == '{'){
                dq.push(c);
            } else if (c == ')' || c == ']' || c == '}'){
                if (dq.isEmpty()) return false; // detect closing bracket, but dq is empty -> malformed
                
                char currC = dq.pop();
                if (map.get(currC) != c) return false; // diff matches -> malformed
            }
        }
        
        return dq.isEmpty();
    }

}
