package Binary_Tree_General.Path_Sum;

public class pathSum {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------------------- Test Case 1 ----------------------
        // Classic example:
        //       5
        //      / \
        //     4   8
        //    /   / \
        //   11  13  4
        //  /  \      \
        // 7    2      1
        //
        // targetSum = 22
        // Valid path: 5 -> 4 -> 11 -> 2 = 22  => true
        TreeNode root1 =
            new TreeNode(5,
                new TreeNode(4,
                    new TreeNode(11,
                        new TreeNode(7),
                        new TreeNode(2)
                    ),
                    null
                ),
                new TreeNode(8,
                    new TreeNode(13),
                    new TreeNode(4,
                        null,
                        new TreeNode(1)
                    )
                )
            );

        int target1 = 22;
        boolean ans1 = sol.hasPathSum(root1, target1);

        System.out.println("Test 1:");
        System.out.println("  targetSum = " + target1);
        System.out.println("  result    = " + ans1);
        System.out.println("  expect    = true (5->4->11->2)");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 2 ----------------------
        // Same tree, targetSum = 26
        // Valid path: 5 -> 8 -> 13 = 26  => true
        int target2 = 26;
        boolean ans2 = sol.hasPathSum(root1, target2);

        System.out.println("Test 2:");
        System.out.println("  targetSum = " + target2);
        System.out.println("  result    = " + ans2);
        System.out.println("  expect    = true (5->8->13)");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 3 ----------------------
        // Same tree, targetSum = 27
        // No root-to-leaf path sums to 27 => false
        int target3 = 27;
        boolean ans3 = sol.hasPathSum(root1, target3);

        System.out.println("Test 3:");
        System.out.println("  targetSum = " + target3);
        System.out.println("  result    = " + ans3);
        System.out.println("  expect    = false");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 4 ----------------------
        // Single node tree:
        //   7
        // targetSum = 7 => true
        TreeNode root4 = new TreeNode(7);

        int target4 = 7;
        boolean ans4 = sol.hasPathSum(root4, target4);

        System.out.println("Test 4:");
        System.out.println("  targetSum = " + target4);
        System.out.println("  result    = " + ans4);
        System.out.println("  expect    = true");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 5 ----------------------
        // Empty tree:
        // root = null => always false
        TreeNode root5 = null;

        int target5 = 0;
        boolean ans5 = sol.hasPathSum(root5, target5);

        System.out.println("Test 5:");
        System.out.println("  targetSum = " + target5);
        System.out.println("  result    = " + ans5);
        System.out.println("  expect    = false (empty tree)");
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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        int currSum = 0;
        return DFS(root, targetSum, currSum);
    }

    public boolean DFS(TreeNode root, int targetSum, int currSum) {
        if (root == null) return false;
        // base case: reaching a leaf
        if (root.left == null && root.right == null) return currSum + root.val == targetSum;

        currSum += root.val;
        return DFS(root.left, targetSum, currSum) || DFS(root.right, targetSum, currSum);
    }
}
