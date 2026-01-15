package StackAndQueue.Simplify_Path;

import java.util.*;

public class simplifyPath {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // -----------------------------
        // Test 1: basic with "." and ".."
        // -----------------------------
        String p1 = "/home/";
        String r1 = sol.simplifyPath(p1);
        System.out.println("Test 1");
        System.out.println("  Input : " + p1);
        System.out.println("  Output: " + r1);
        System.out.println("  Expect: /home");
        System.out.println("-----------------------------");

        // -----------------------------
        // Test 2: multiple slashes + ".."
        // -----------------------------
        String p2 = "/a/./b/../../c/";
        String r2 = sol.simplifyPath(p2);
        System.out.println("Test 2");
        System.out.println("  Input : " + p2);
        System.out.println("  Output: " + r2);
        System.out.println("  Expect: /c");
        System.out.println("-----------------------------");

        // -----------------------------
        // Test 3: going above root should stay at "/"
        // -----------------------------
        String p3 = "/../";
        String r3 = sol.simplifyPath(p3);
        System.out.println("Test 3");
        System.out.println("  Input : " + p3);
        System.out.println("  Output: " + r3);
        System.out.println("  Expect: /");
        System.out.println("-----------------------------");

        // -----------------------------
        // Test 4: mixed cases, extra slashes
        // -----------------------------
        String p4 = "/home//foo/";
        String r4 = sol.simplifyPath(p4);
        System.out.println("Test 4");
        System.out.println("  Input : " + p4);
        System.out.println("  Output: " + r4);
        System.out.println("  Expect: /home/foo");
        System.out.println("-----------------------------");

        // -----------------------------
        // Test 5: only root
        // -----------------------------
        String p5 = "/";
        String r5 = sol.simplifyPath(p5);
        System.out.println("Test 5");
        System.out.println("  Input : " + p5);
        System.out.println("  Output: " + r5);
        System.out.println("  Expect: /");
        System.out.println("-----------------------------");

        // -----------------------------
        // Test 6: tricky: ".." after nested dirs
        // -----------------------------
        String p6 = "/a/b/c/../../";
        String r6 = sol.simplifyPath(p6);
        System.out.println("Test 6");
        System.out.println("  Input : " + p6);
        System.out.println("  Output: " + r6);
        System.out.println("  Expect: /a");
        System.out.println("-----------------------------");
    }
}

class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] pathArr = path.split("/");
        stack.push("/");

        if (pathArr.length == 0) return "/";

        for (int i = 0; i < pathArr.length; i++){
            String currStr = pathArr[i];
            if (currStr.isEmpty()) continue;
            
            if (!currStr.equals("..") && !currStr.equals(".")){
                if (!stack.peek().equals("/")) stack.push("/" + currStr);
                else stack.push(currStr);
            } else if (currStr.equals("..")) {
                if (!stack.peek().equals("/")) {
                    stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.removeLast());
        }
        return sb.toString();
    }
}
