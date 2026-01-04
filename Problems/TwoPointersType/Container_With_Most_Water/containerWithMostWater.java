package TwoPointersType.Container_With_Most_Water;

import java.util.Arrays;

public class containerWithMostWater {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------- Case 1: LeetCode classic example ----------
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int ans1 = sol.maxArea(height1);
        System.out.println("Case 1");
        System.out.println("Input heights: " + Arrays.toString(height1));
        System.out.println("Expected: 49");
        System.out.println("Got     : " + ans1);
        System.out.println("Explanation: best pair is index 1 (height 8) and index 8 (height 7)");
        System.out.println("width = 8-1 = 7, minHeight = 7, area = 7*7 = 49");
        System.out.println();

        // ---------- Case 2: minimal length ----------
        int[] height2 = {1, 1};
        int ans2 = sol.maxArea(height2);
        System.out.println("Case 2");
        System.out.println("Input heights: " + Arrays.toString(height2));
        System.out.println("Expected: 1");
        System.out.println("Got     : " + ans2);
        System.out.println("Explanation: width = 1, minHeight = 1, area = 1");
        System.out.println();

        // ---------- Case 3: strictly increasing ----------
        int[] height3 = {1, 2, 3, 4, 5};
        int ans3 = sol.maxArea(height3);
        System.out.println("Case 3");
        System.out.println("Input heights: " + Arrays.toString(height3));
        System.out.println("Got: " + ans3);
        System.out.println("Note: maximum is not necessarily the two tallest next to each other.");
        System.out.println();

        // ---------- Case 4: contains zeros ----------
        int[] height4 = {0, 2, 0, 3, 0, 4};
        int ans4 = sol.maxArea(height4);
        System.out.println("Case 4");
        System.out.println("Input heights: " + Arrays.toString(height4));
        System.out.println("Got: " + ans4);
        System.out.println("Explanation: zero heights can still be present; area uses min(height[left], height[right]).");
        System.out.println();

        // ---------- Case 5: all equal ----------
        int[] height5 = {5, 5, 5, 5};
        int ans5 = sol.maxArea(height5);
        System.out.println("Case 5");
        System.out.println("Input heights: " + Arrays.toString(height5));
        System.out.println("Expected: 15");
        System.out.println("Got     : " + ans5);
        System.out.println("Explanation: best is farthest apart: width=3, minHeight=5, area=15");
    }
}

class Solution {
    public int maxArea(int[] height) {
        int len = height.length;
        int left = 0, right = len - 1;
        int maxSoFar = 0;

        while (left < right) {
            int width = right - left;
            int leftVal = height[left], rightVal = height[right];
            
            // calculate and update the trappedwater = smaller height * currHeight
            int trappedWater = Math.min(leftVal, rightVal) * width;
            maxSoFar = Math.max(trappedWater, maxSoFar);

            // move the smaller height side
            if (leftVal < rightVal) left++;
            else right--;
        }
        return maxSoFar;
    }
}