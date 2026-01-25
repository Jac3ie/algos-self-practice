package Binary_Tree_General.Binary_Tree_Level_Order_Traversal;

import java.util.*;

public class treeLevelOrderTravel {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------------------- Test Case 1 ----------------------
        // Tree:
        //        3
        //      /   \
        //     9     20
        //          /  \
        //         15   7
        //
        // Expected levels: [[3],[9,20],[15,7]]
        TreeNode root1 =
            new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7))
            );

        List<List<Integer>> ans1 = sol.levelOrder(root1);

        System.out.println("Test 1:");
        System.out.println("  Tree (conceptually): [3,9,20,null,null,15,7]");
        System.out.println("  levelOrder result   = " + ans1);
        System.out.println("  expect              = [[3], [9, 20], [15, 7]]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 2 ----------------------
        // Single node:
        //   1
        // Expected: [[1]]
        TreeNode root2 = new TreeNode(1);

        List<List<Integer>> ans2 = sol.levelOrder(root2);

        System.out.println("Test 2:");
        System.out.println("  Tree: [1]");
        System.out.println("  levelOrder result   = " + ans2);
        System.out.println("  expect              = [[1]]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 3 ----------------------
        // Empty tree:
        // root = null
        // Expected: []
        TreeNode root3 = null;

        List<List<Integer>> ans3 = sol.levelOrder(root3);

        System.out.println("Test 3:");
        System.out.println("  Tree: null");
        System.out.println("  levelOrder result   = " + ans3);
        System.out.println("  expect              = []");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 4 ----------------------
        // Skewed left:
        //     1
        //    /
        //   2
        //  /
        // 3
        // Expected: [[1],[2],[3]]
        TreeNode root4 =
            new TreeNode(1,
                new TreeNode(2,
                    new TreeNode(3),
                    null
                ),
                null
            );

        List<List<Integer>> ans4 = sol.levelOrder(root4);

        System.out.println("Test 4:");
        System.out.println("  Tree (conceptually): [1,2,null,3]");
        System.out.println("  levelOrder result   = " + ans4);
        System.out.println("  expect              = [[1], [2], [3]]");
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) return levels;

        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);
        while (!dq.isEmpty()) {
            int size = dq.size();
            List<Integer> currLevel = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode curr = dq.poll();
                int currVal = curr.val;
                currLevel.add(currVal);

                if (curr.left != null) dq.offer(curr.left);
                if (curr.right != null) dq.offer(curr.right);
            }

            levels.add(currLevel);
        }
        return levels;
    }
}