package Binary_Search_Tree.Min_Abs_Diff_BST;

import java.util.*;

public class minAbsDiffBST {
    public static void main(String[] args) {
        // ---------------------- Test Case 1 ----------------------
        // Tree:
        //        4
        //       / \
        //      2   6
        //     / \
        //    1   3
        //
        // Inorder: [1,2,3,4,6]
        // Min abs diff = 1 (2-1, 3-2, 4-3)
        TreeNode root1 =
            new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(6)
            );

        Solution sol1 = new Solution();
        int ans1Rec = sol1.getMinimumDifferenceRecursionDFS(root1);

        Solution sol1b = new Solution(); // new instance to reset fields
        int ans1Iter = sol1b.getMinimumDifferenceLoopDFS(root1);

        System.out.println("Test 1:");
        System.out.println("  Inorder expected: [1, 2, 3, 4, 6]");
        System.out.print("  Inorder actual  : ");
        printInorder(root1);
        System.out.println("  Recursion result: " + ans1Rec);
        System.out.println("  Iteration result: " + ans1Iter);
        System.out.println("  Expect          : 1");
        System.out.println("-----------------------------------");


        // ---------------------- Test Case 2 ----------------------
        // Tree:
        //        1
        //         \
        //          3
        //         /
        //        2
        //
        // Inorder: [1,2,3]
        // Min abs diff = 1
        TreeNode root2 =
            new TreeNode(1,
                null,
                new TreeNode(3, new TreeNode(2), null)
            );

        Solution sol2 = new Solution();
        int ans2Rec = sol2.getMinimumDifferenceRecursionDFS(root2);

        Solution sol2b = new Solution();
        int ans2Iter = sol2b.getMinimumDifferenceLoopDFS(root2);

        System.out.println("Test 2:");
        System.out.println("  Inorder expected: [1, 2, 3]");
        System.out.print("  Inorder actual  : ");
        printInorder(root2);
        System.out.println("  Recursion result: " + ans2Rec);
        System.out.println("  Iteration result: " + ans2Iter);
        System.out.println("  Expect          : 1");
        System.out.println("-----------------------------------");


        // ---------------------- Test Case 3 ----------------------
        // Single node
        // Tree: 5
        //
        // Inorder: [5]
        // Min abs diff: not well-defined in typical problems (they usually guarantee >= 2 nodes)
        // Your code returns Integer.MAX_VALUE for this case.
        TreeNode root3 = new TreeNode(5);

        Solution sol3 = new Solution();
        int ans3Rec = sol3.getMinimumDifferenceRecursionDFS(root3);

        Solution sol3b = new Solution();
        int ans3Iter = sol3b.getMinimumDifferenceLoopDFS(root3);

        System.out.println("Test 3:");
        System.out.println("  Inorder expected: [5]");
        System.out.print("  Inorder actual  : ");
        printInorder(root3);
        System.out.println("  Recursion result: " + ans3Rec);
        System.out.println("  Iteration result: " + ans3Iter);
        System.out.println("  Note            : Many platforms guarantee at least 2 nodes.");
        System.out.println("-----------------------------------");


        // ---------------------- Test Case 4 ----------------------
        // Skewed (increasing) BST:
        // 1 -> 2 -> 3 -> 10
        //
        // Inorder: [1,2,3,10]
        // Min abs diff = 1
        TreeNode root4 =
            new TreeNode(1,
                null,
                new TreeNode(2,
                    null,
                    new TreeNode(3,
                        null,
                        new TreeNode(10)
                    )
                )
            );

        Solution sol4 = new Solution();
        int ans4Rec = sol4.getMinimumDifferenceRecursionDFS(root4);

        Solution sol4b = new Solution();
        int ans4Iter = sol4b.getMinimumDifferenceLoopDFS(root4);

        System.out.println("Test 4:");
        System.out.println("  Inorder expected: [1, 2, 3, 10]");
        System.out.print("  Inorder actual  : ");
        printInorder(root4);
        System.out.println("  Recursion result: " + ans4Rec);
        System.out.println("  Iteration result: " + ans4Iter);
        System.out.println("  Expect          : 1");
        System.out.println("-----------------------------------");
    }

    // Print inorder traversal for debugging / verification
    private static void printInorder(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        inorderCollect(root, out);
        System.out.println(out);
    }

    private static void inorderCollect(TreeNode node, List<Integer> out) {
        if (node == null) return;
        inorderCollect(node.left, out);
        out.add(node.val);
        inorderCollect(node.right, out);
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
    Integer prev = null; // record the prev elt val
    int minSoFar = Integer.MAX_VALUE;

    public int getMinimumDifferenceRecursionDFS(TreeNode root) {
        inorder(root);
        return minSoFar;
    }

    public void inorder(TreeNode root) {
        if (root == null) return;

        // .left -> root -> .right
        inorder(root.left);

        // visit root
        if (prev != null) 
            minSoFar = Math.min(minSoFar, root.val - prev); // inorder BST feature => increasing
        prev = root.val;

        inorder(root.right);
    }

    public int getMinimumDifferenceLoopDFS(TreeNode root) {
        // stack for tracking the structure in inorder way
        Deque<TreeNode> stack = new ArrayDeque<>();
        Integer prev = null;
        int minSoFar = Integer.MAX_VALUE;
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) { // use a while loop to simulate DFS
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            if (prev != null) minSoFar = Math.min(minSoFar, curr.val - prev);
            prev = curr.val;

            curr = curr.right;
        }

        return minSoFar;
    }
}
