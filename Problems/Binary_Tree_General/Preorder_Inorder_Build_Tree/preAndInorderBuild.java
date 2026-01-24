package Binary_Tree_General.Preorder_Inorder_Build_Tree;

import java.util.*;

public class preAndInorderBuild {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------------------- Test Case 1 ----------------------
        // preorder = [3,9,20,15,7]
        // inorder  = [9,3,15,20,7]
        // Expected tree (level order): [3,9,20,null,null,15,7]
        int[] preorder1 = {3, 9, 20, 15, 7};
        int[] inorder1  = {9, 3, 15, 20, 7};

        TreeNode root1 = sol.buildTree(preorder1, inorder1);

        System.out.println("Test 1:");
        System.out.println("  preorder = " + Arrays.toString(preorder1));
        System.out.println("  inorder  = " + Arrays.toString(inorder1));
        System.out.print("  levelOrder result = ");
        printLevelOrder(root1);
        System.out.println("  expect            = [3, 9, 20, null, null, 15, 7]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 2 ----------------------
        // Single node
        // preorder = [1], inorder = [1]
        // Expected: [1]
        Solution sol2 = new Solution(); // new instance to reset preIdx
        int[] preorder2 = {1};
        int[] inorder2  = {1};

        TreeNode root2 = sol2.buildTree(preorder2, inorder2);

        System.out.println("Test 2:");
        System.out.println("  preorder = " + Arrays.toString(preorder2));
        System.out.println("  inorder  = " + Arrays.toString(inorder2));
        System.out.print("  levelOrder result = ");
        printLevelOrder(root2);
        System.out.println("  expect            = [1]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 3 ----------------------
        // Skewed right (like a linked list)
        // Tree: 1 -> 2 -> 3 -> 4 (all to the right)
        // preorder = [1,2,3,4]
        // inorder  = [1,2,3,4]
        // Expected level order: [1,null,2,null,3,null,4]
        Solution sol3 = new Solution(); // new instance to reset preIdx
        int[] preorder3 = {1, 2, 3, 4};
        int[] inorder3  = {1, 2, 3, 4};

        TreeNode root3 = sol3.buildTree(preorder3, inorder3);

        System.out.println("Test 3:");
        System.out.println("  preorder = " + Arrays.toString(preorder3));
        System.out.println("  inorder  = " + Arrays.toString(inorder3));
        System.out.print("  levelOrder result = ");
        printLevelOrder(root3);
        System.out.println("  expect            = [1, null, 2, null, 3, null, 4]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 4 ----------------------
        // Skewed left
        // Tree: 4 -> 3 -> 2 -> 1 (all to the left)
        // preorder = [4,3,2,1]
        // inorder  = [1,2,3,4]
        // Expected level order: [4,3,null,2,null,1]
        Solution sol4 = new Solution(); // new instance to reset preIdx
        int[] preorder4 = {4, 3, 2, 1};
        int[] inorder4  = {1, 2, 3, 4};

        TreeNode root4 = sol4.buildTree(preorder4, inorder4);

        System.out.println("Test 4:");
        System.out.println("  preorder = " + Arrays.toString(preorder4));
        System.out.println("  inorder  = " + Arrays.toString(inorder4));
        System.out.print("  levelOrder result = ");
        printLevelOrder(root4);
        System.out.println("  expect            = [4, 3, null, 2, null, 1]");
        System.out.println("-----------------------------------");
    }

    // Level-order print with nulls (trim trailing nulls for nicer output)
    private static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }

        List<String> out = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            out.add(String.valueOf(node.val));

            // left
            if (node.left != null) q.offer(node.left);
            else out.add("null");

            // right
            if (node.right != null) q.offer(node.right);
            else out.add("null");
        }

        // trim trailing nulls
        int i = out.size() - 1;
        while (i >= 0 && out.get(i).equals("null")) i--;
        System.out.println(out.subList(0, i + 1));
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
    int preIdx = 0; // preOrder Idx for getting the root
    Map<Integer, Integer> inMap = new HashMap<>(); // for inOrder[] value with its idx

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++){
            inMap.put(inorder[i], i); // k:v = inOrderVal:inOrderIdx
        }

        return build(preorder, 0, inorder.length - 1);
    }

    public TreeNode build(int[] preorder, int inLeft, int inRight) {
        // use the range to define null child of certain node, note it can be inLeft = inRight
        if (inLeft > inRight) return null; // range not valid => null child

        // the root is always the first val due to preorder feature: root -> left -> right
        int rootVal = preorder[preIdx++];
        TreeNode root = new TreeNode(rootVal);
        int mid = inMap.get(rootVal);

        // recursively build also follow preorder feature
        root.left = build(preorder, inLeft, mid - 1);
        root.right = build(preorder, mid + 1, inRight);
        return root;
    }
}
