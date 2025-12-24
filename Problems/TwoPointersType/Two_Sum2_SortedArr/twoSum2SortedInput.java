package TwoPointersType.Two_Sum2_SortedArr;

import java.util.Arrays;

public class twoSum2SortedInput {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] testArrays = {
            {2, 7, 11, 15},
            {2, 3, 4},
            {-3, -1, 0, 2, 4, 5},
            {1, 2, 3, 4, 4, 9},
            {1, 2, 3, 4, 5}
        };

        int[] targets = {9, 6, 1, 8, 100};

        for (int t = 0; t < testArrays.length; t++) {
            int[] numbers = testArrays[t];
            int target = targets[t];

            System.out.println("====================================");
            System.out.println("Test " + (t + 1));
            System.out.println("numbers = " + Arrays.toString(numbers));
            System.out.println("target  = " + target);

            int[] ans = sol.twoSum(numbers, target);

            System.out.println("output indices (1-based) = " + Arrays.toString(ans));

            // extra clarity: show the chosen values if found
            if (ans[0] != -1) {
                int i = ans[0] - 1; // convert back to 0-based
                int j = ans[1] - 1;
                System.out.println("picked values = " + numbers[i] + " + " + numbers[j] + " = " + (numbers[i] + numbers[j]));
            } else {
                System.out.println("no valid pair found");
            }
        }

        System.out.println("====================================");
    }
}

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // use two pointers thhinking to solve it
        // since its a sorted arr, therefore 
        // left + right < target => left++
        // left + right > target => right--

        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) return new int[] {left + 1, right + 1};
            else if (sum < target) left++;
            else right--;
        }
        return new int[] {-1, -1};
    }
}