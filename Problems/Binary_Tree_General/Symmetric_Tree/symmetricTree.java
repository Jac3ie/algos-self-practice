package Binary_Tree_General.Symmetric_Tree;

import java.util.*;

public class symmetricTree {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------------------- Test Case 1 ----------------------
        // Symmetric:
        //        1
        //      /   \
        //     2     2
        //    / \   / \
        //   3  4  4  3
        // expected: true
        TreeNode t1 = new TreeNode(
            1,
            new TreeNode(2, new TreeNode(3), new TreeNode(4)),
            new TreeNode(2, new TreeNode(4), new TreeNode(3))
        );

        System.out.println("Test 1 (symmetric full tree):");
        System.out.println("  DFS = " + sol.isSymmetricDFS(t1) + " (expect true)");
        System.out.println("  BFS = " + sol.isSymmetricBFS(t1) + " (expect true)");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 2 ----------------------
        // Not symmetric (structure differs):
        //        1
        //      /   \
        //     2     2
        //      \      \
        //       3      3
        // expected: false
        TreeNode t2 = new TreeNode(
            1,
            new TreeNode(2, null, new TreeNode(3)),
            new TreeNode(2, null, new TreeNode(3))
        );

        System.out.println("Test 2 (not symmetric - structure mismatch):");
        System.out.println("  DFS = " + sol.isSymmetricDFS(t2) + " (expect false)");
        System.out.println("  BFS = " + sol.isSymmetricBFS(t2) + " (expect false)");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 3 ----------------------
        // Not symmetric (values differ):
        //        1
        //      /   \
        //     2     2
        //    /       \
        //   3         4
        // expected: false
        TreeNode t3 = new TreeNode(
            1,
            new TreeNode(2, new TreeNode(3), null),
            new TreeNode(2, null, new TreeNode(4))
        );

        System.out.println("Test 3 (not symmetric - value mismatch):");
        System.out.println("  DFS = " + sol.isSymmetricDFS(t3) + " (expect false)");
        System.out.println("  BFS = " + sol.isSymmetricBFS(t3) + " (expect false)");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 4 ----------------------
        // Single node tree:
        //   1
        // expected: true
        TreeNode t4 = new TreeNode(1);

        System.out.println("Test 4 (single node):");
        System.out.println("  DFS = " + sol.isSymmetricDFS(t4) + " (expect true)");
        System.out.println("  BFS = " + sol.isSymmetricBFS(t4) + " (expect true)");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 5 ----------------------
        // Empty tree:
        // expected: true
        TreeNode t5 = null;

        System.out.println("Test 5 (empty tree):");
        System.out.println("  DFS = " + sol.isSymmetricDFS(t5) + " (expect true)");
        System.out.println("  BFS = " + sol.isSymmetricBFS(t5) + " (expect true)");
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
    public boolean isSymmetricDFS(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode left, TreeNode right){
        if (left == null && right == null) return true;
        if (left == null && right != null || left != null && right == null) return false;
        if (left.val != right.val) return false;

        // recursively call and compare left.left with right.right & left.right with right.left
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    public boolean isSymmetricBFS(TreeNode root) {
        if (root == null) return true;
        
        Deque<TreeNode[]> dq = new ArrayDeque<>();
        // offer in a pair, and wrap them in an arr
        dq.offer(new TreeNode[]{root.left, root.right});

        while (!dq.isEmpty()) {
            TreeNode[] pair = dq.poll();
            TreeNode left = pair[0];
            TreeNode right = pair[1];

            if (left == null && right == null) continue;
            if (left == null && right != null || left != null && right == null) return false;
            if (left.val != right.val) return false;

            // offer in a pair again, left.left with right.right & left.right with right.left
            dq.offer(new TreeNode[]{left.left, right.right}); 
            dq.offer(new TreeNode[]{left.right, right.left});
        }
        return true;
    }
}
