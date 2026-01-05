package ArrayAndStringType.Merge_Sorted_Arrays_InPlace;

import java.util.Arrays;

public class mergeSortedArrs {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------- Test 1: normal merge ----------
        int[] nums1a = {1, 2, 3, 0, 0, 0};
        int m1 = 3;
        int[] nums2a = {2, 5, 6};
        int n1 = 3;

        System.out.println("Test 1");
        System.out.println("  Before nums1: " + Arrays.toString(nums1a) + " (m=" + m1 + ")");
        System.out.println("  nums2       : " + Arrays.toString(nums2a) + " (n=" + n1 + ")");
        sol.merge(nums1a, m1, nums2a, n1);
        System.out.println("  After  nums1: " + Arrays.toString(nums1a));
        System.out.println("--------------------------------------------------");

        // ---------- Test 2: nums2 empty ----------
        int[] nums1b = {1, 2, 3};
        int m2 = 3;
        int[] nums2b = {};
        int n2 = 0;

        System.out.println("Test 2");
        System.out.println("  Before nums1: " + Arrays.toString(nums1b) + " (m=" + m2 + ")");
        System.out.println("  nums2       : " + Arrays.toString(nums2b) + " (n=" + n2 + ")");
        sol.merge(nums1b, m2, nums2b, n2);
        System.out.println("  After  nums1: " + Arrays.toString(nums1b));
        System.out.println("--------------------------------------------------");

        // ---------- Test 3: nums1 has no valid elements (m = 0) ----------
        int[] nums1c = {0, 0, 0};
        int m3 = 0;
        int[] nums2c = {2, 4, 7};
        int n3 = 3;

        System.out.println("Test 3");
        System.out.println("  Before nums1: " + Arrays.toString(nums1c) + " (m=" + m3 + ")");
        System.out.println("  nums2       : " + Arrays.toString(nums2c) + " (n=" + n3 + ")");
        sol.merge(nums1c, m3, nums2c, n3);
        System.out.println("  After  nums1: " + Arrays.toString(nums1c));
        System.out.println("--------------------------------------------------");

        // ---------- Test 4: nums2 all smaller ----------
        int[] nums1d = {5, 6, 7, 0, 0, 0};
        int m4 = 3;
        int[] nums2d = {1, 2, 3};
        int n4 = 3;

        System.out.println("Test 4");
        System.out.println("  Before nums1: " + Arrays.toString(nums1d) + " (m=" + m4 + ")");
        System.out.println("  nums2       : " + Arrays.toString(nums2d) + " (n=" + n4 + ")");
        sol.merge(nums1d, m4, nums2d, n4);
        System.out.println("  After  nums1: " + Arrays.toString(nums1d));
        System.out.println("--------------------------------------------------");
    }
}

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;

        int[] nums3 = new int[m + n];
        int left1 = 0, left2 = 0;
        int targetIdx = 0;
        while (left1 < m && left2 < n) {
            nums3[targetIdx++] = nums1[left1] < nums2[left2] ? nums1[left1++] : nums2[left2++];
        }
        while (left1 < m) nums3[targetIdx++] = nums1[left1++];
        while (left2 < n) nums3[targetIdx++] = nums2[left2++];

        System.arraycopy(nums3, 0, nums1, 0, m + n);
    }
}