package Binary_Tree_General.DFS_BFS_Basics;

import java.util.*;

public class maxDepthBT {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------------------- Test Case 1 ----------------------
        // Tree (example from LeetCode 104):
        //        3
        //      /   \
        //     9     20
        //          /  \
        //         15   7
        // Expected depth = 3
        TreeNode root1 = new TreeNode(
                3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );

        System.out.println("Test 1:");
        System.out.println("  Tree: [3,9,20,null,null,15,7]");
        System.out.println("  DFS depth = " + sol.DFS(root1));
        System.out.println("  BFS depth = " + sol.BFS(root1));
        System.out.println("  expect    = 3");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 2 ----------------------
        // Single node:
        //   5
        // Expected depth = 1
        TreeNode root2 = new TreeNode(5);

        System.out.println("Test 2:");
        System.out.println("  Tree: [5]");
        System.out.println("  DFS depth = " + sol.DFS(root2));
        System.out.println("  BFS depth = " + sol.BFS(root2));
        System.out.println("  expect    = 1");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 3 ----------------------
        // Skewed tree (like a linked list):
        //   1
        //    \
        //     2
        //      \
        //       3
        //        \
        //         4
        // Expected depth = 4
        TreeNode root3 = new TreeNode(
                1,
                null,
                new TreeNode(
                        2,
                        null,
                        new TreeNode(
                                3,
                                null,
                                new TreeNode(4)
                        )
                )
        );

        System.out.println("Test 3:");
        System.out.println("  Tree: [1,null,2,null,3,null,4]");
        System.out.println("  DFS depth = " + sol.DFS(root3));
        System.out.println("  BFS depth = " + sol.BFS(root3));
        System.out.println("  expect    = 4");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 4 ----------------------
        // Empty tree
        // Expected depth = 0
        TreeNode root4 = null;

        System.out.println("Test 4:");
        System.out.println("  Tree: []");
        System.out.println("  DFS depth = " + sol.DFS(root4));
        System.out.println("  BFS depth = " + sol.BFS(root4));
        System.out.println("  expect    = 0");
        System.out.println("-----------------------------------");
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(){}
    public TreeNode(int val){this.val = val;}
    public TreeNode(int val, TreeNode left, TreeNode right){
        this.left = left;
        this.right = right;
        this.val = val;
    }
}

class Solution {
    public int BFS(TreeNode root) {
        // BFS way
        if (root == null) return 0;

        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);
        int depth = 0;

        while (!dq.isEmpty()) {
            int size = dq.size();
            depth++;

            for (int i = 0; i < size; i++) {
                TreeNode curr = dq.poll();
                if (curr.left != null) dq.offer(curr.left);
                if (curr.right != null) dq.offer(curr.right);
            }
        }
        return depth;
    }

    public int DFS(TreeNode root) {
        // use a recursion to do it will be easier
        if (root == null) return 0;
        return 1 + Math.max(DFS(root.left), DFS(root.right));
    }
}