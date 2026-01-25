package Binary_Tree_General.BT_Right_View;

import java.util.*;

public class treeRightView {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------------------- Test Case 1 ----------------------
        // Tree:
        //        1
        //      /   \
        //     2     3
        //      \     \
        //       5     4
        //
        // Right view = [1, 3, 4]
        TreeNode root1 =
            new TreeNode(1,
                new TreeNode(2, null, new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(4))
            );

        List<Integer> ans1 = sol.rightSideView(root1);

        System.out.println("Test 1:");
        System.out.println("  Tree (conceptually): [1,2,3,null,5,null,4]");
        System.out.println("  rightSideView result = " + ans1);
        System.out.println("  expect               = [1, 3, 4]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 2 ----------------------
        // Single node:
        //   7
        // Right view = [7]
        TreeNode root2 = new TreeNode(7);

        List<Integer> ans2 = sol.rightSideView(root2);

        System.out.println("Test 2:");
        System.out.println("  Tree: [7]");
        System.out.println("  rightSideView result = " + ans2);
        System.out.println("  expect               = [7]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 3 ----------------------
        // Left-skewed:
        //     1
        //    /
        //   2
        //  /
        // 3
        // Right view = [1, 2, 3]  (each level only has one node)
        TreeNode root3 =
            new TreeNode(1,
                new TreeNode(2,
                    new TreeNode(3),
                    null
                ),
                null
            );

        List<Integer> ans3 = sol.rightSideView(root3);

        System.out.println("Test 3:");
        System.out.println("  Tree: [1,2,null,3]");
        System.out.println("  rightSideView result = " + ans3);
        System.out.println("  expect               = [1, 2, 3]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 4 ----------------------
        // Mixed shape:
        //        10
        //       /  \
        //      6    15
        //     /    /  \
        //    3    12  20
        //           \
        //           13
        //
        // Levels:
        // level 0: 10 -> take 10
        // level 1: 6, 15 -> take 15
        // level 2: 3, 12, 20 -> take 20
        // level 3: 13 -> take 13
        // Right view = [10, 15, 20, 13]
        TreeNode root4 =
            new TreeNode(10,
                new TreeNode(6,
                    new TreeNode(3),
                    null
                ),
                new TreeNode(15,
                    new TreeNode(12, null, new TreeNode(13)),
                    new TreeNode(20)
                )
            );

        List<Integer> ans4 = sol.rightSideView(root4);

        System.out.println("Test 4:");
        System.out.println("  Tree (conceptually): [10,6,15,3,null,12,20,null,null,null,13]");
        System.out.println("  rightSideView result = " + ans4);
        System.out.println("  expect               = [10, 15, 20, 13]");
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightView = new ArrayList<>();
        if (root == null) return rightView;

        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);

        while (!dq.isEmpty()) {
            int size = dq.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = dq.poll();
                if (i == size - 1) rightView.add(curr.val);

                if (curr.left != null) dq.offer(curr.left);
                if (curr.right != null) dq.offer(curr.right);
            }
        }
        return rightView;
    }
}
