package Binary_Search_Tree.kTh_Smallest_Element;

import java.util.ArrayDeque;
import java.util.Deque;

public class kThSmallestElt {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------------------- Test Case 1 ----------------------
        // root = [3,1,4,null,2]
        // BST structure:
        //       3
        //     /   \
        //    1     4
        //     \
        //      2
        // inorder = [1,2,3,4]
        TreeNode root1 = new TreeNode(
            3,
            new TreeNode(1, null, new TreeNode(2)),
            new TreeNode(4)
        );

        int k1 = 1;
        int ans1 = sol.kthSmallest(root1, k1);

        System.out.println("Test 1:");
        System.out.print("  inorder = "); printInorder(root1); System.out.println();
        System.out.println("  k       = " + k1);
        System.out.println("  result  = " + ans1);
        System.out.println("  expect  = 1");
        System.out.println("-----------------------------------");

        // IMPORTANT: create a NEW Solution each time because your Solution stores state (k, kSmall)
        sol = new Solution();

        // ---------------------- Test Case 2 ----------------------
        // root = [5,3,6,2,4,null,null,1]
        // BST structure:
        //         5
        //       /   \
        //      3     6
        //     / \
        //    2   4
        //   /
        //  1
        // inorder = [1,2,3,4,5,6]
        TreeNode root2 = new TreeNode(
            5,
            new TreeNode(
                3,
                new TreeNode(2, new TreeNode(1), null),
                new TreeNode(4)
            ),
            new TreeNode(6)
        );

        int k2 = 3;
        int ans2 = sol.kthSmallest(root2, k2);

        System.out.println("Test 2:");
        System.out.print("  inorder = "); printInorder(root2); System.out.println();
        System.out.println("  k       = " + k2);
        System.out.println("  result  = " + ans2);
        System.out.println("  expect  = 3");
        System.out.println("-----------------------------------");

        sol = new Solution();

        // ---------------------- Test Case 3 ----------------------
        // Single node
        // root = [42], k = 1 => 42
        TreeNode root3 = new TreeNode(42);

        int k3 = 1;
        int ans3 = sol.kthSmallest(root3, k3);

        System.out.println("Test 3:");
        System.out.print("  inorder = "); printInorder(root3); System.out.println();
        System.out.println("  k       = " + k3);
        System.out.println("  result  = " + ans3);
        System.out.println("  expect  = 42");
        System.out.println("-----------------------------------");

        sol = new Solution();

        // ---------------------- Test Case 4 ----------------------
        // Skewed right: 1 -> 2 -> 3 -> 4
        // inorder = [1,2,3,4]
        TreeNode root4 = new TreeNode(1, null,
            new TreeNode(2, null,
                new TreeNode(3, null,
                    new TreeNode(4)
                )
            )
        );

        int k4 = 4;
        int ans4 = sol.kthSmallest(root4, k4);

        System.out.println("Test 4:");
        System.out.print("  inorder = "); printInorder(root4); System.out.println();
        System.out.println("  k       = " + k4);
        System.out.println("  result  = " + ans4);
        System.out.println("  expect  = 4");
        System.out.println("-----------------------------------");
    }

    private static void printInorder(TreeNode root) {
        System.out.print("[");
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        boolean first = true;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (!first) System.out.print(", ");
            System.out.print(curr.val);
            first = false;

            curr = curr.right;
        }

        System.out.print("]");
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
    int kSmall;
    int k;
    
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        inorder(root);
        return kSmall;
    }

    public void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        if (--k == 0) {
            kSmall = root.val;
            return;
        }

        inorder(root.right);
    }
}
