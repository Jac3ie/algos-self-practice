package Binary_Tree_General.Is_Same_Tree;

public class isSameTree {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------------------- Test Case 1 ----------------------
        // p and q are identical:
        //      1
        //     / \
        //    2   3
        TreeNode p1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        System.out.println("Test 1 (identical trees):");
        System.out.println("  expected = true");
        System.out.println("  result   = " + sol.isSameTree(p1, q1));
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 2 ----------------------
        // Different structure:
        // p:
        //    1
        //   /
        //  2
        //
        // q:
        //    1
        //     \
        //      2
        TreeNode p2 = new TreeNode(1, new TreeNode(2), null);
        TreeNode q2 = new TreeNode(1, null, new TreeNode(2));

        System.out.println("Test 2 (different structure):");
        System.out.println("  expected = false");
        System.out.println("  result   = " + sol.isSameTree(p2, q2));
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 3 ----------------------
        // Different values:
        // p:  1
        // q:  1
        //    /
        //   2
        TreeNode p3 = new TreeNode(1);
        TreeNode q3 = new TreeNode(1, new TreeNode(2), null);

        System.out.println("Test 3 (different values/size):");
        System.out.println("  expected = false");
        System.out.println("  result   = " + sol.isSameTree(p3, q3));
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 4 ----------------------
        // Both null
        TreeNode p4 = null, q4 = null;

        System.out.println("Test 4 (both null):");
        System.out.println("  expected = true");
        System.out.println("  result   = " + sol.isSameTree(p4, q4));
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null && q != null || p != null && q == null) return false;
        int valP = p.val, valQ = q.val;
        if (valP != valQ) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
