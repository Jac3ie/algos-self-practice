package Binary_Tree_General.Postorder_Inorder_Build_Tree;

import java.util.*;

public class postInOrderBuildTree {
    public static void main(String[] args) {
        // ---------------------- Test Case 1 ----------------------
        // inorder    = [9, 3, 15, 20, 7]
        // postorder  = [9, 15, 7, 20, 3]
        // Expected tree (level order): [3, 9, 20, null, null, 15, 7]
        int[] inorder1   = {9, 3, 15, 20, 7};
        int[] postorder1 = {9, 15, 7, 20, 3};

        Solution sol1 = new Solution();
        TreeNode root1 = sol1.buildTree(inorder1, postorder1);

        System.out.println("Test 1:");
        System.out.println("  inorder    = " + Arrays.toString(inorder1));
        System.out.println("  postorder  = " + Arrays.toString(postorder1));
        System.out.print("  levelOrder result = ");
        printLevelOrder(root1);
        System.out.println("  expect            = [3, 9, 20, null, null, 15, 7]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 2 ----------------------
        // Single node:
        // inorder = [1], postorder = [1]
        // Expected: [1]
        int[] inorder2   = {1};
        int[] postorder2 = {1};

        Solution sol2 = new Solution();
        TreeNode root2 = sol2.buildTree(inorder2, postorder2);

        System.out.println("Test 2:");
        System.out.println("  inorder    = " + Arrays.toString(inorder2));
        System.out.println("  postorder  = " + Arrays.toString(postorder2));
        System.out.print("  levelOrder result = ");
        printLevelOrder(root2);
        System.out.println("  expect            = [1]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 3 ----------------------
        // Skewed right:
        // Tree: 1 -> 2 -> 3 -> 4 (all to the right)
        // inorder   = [1, 2, 3, 4]
        // postorder = [4, 3, 2, 1]
        // Expected level order: [1, null, 2, null, 3, null, 4]
        int[] inorder3   = {1, 2, 3, 4};
        int[] postorder3 = {4, 3, 2, 1};

        Solution sol3 = new Solution();
        TreeNode root3 = sol3.buildTree(inorder3, postorder3);

        System.out.println("Test 3:");
        System.out.println("  inorder    = " + Arrays.toString(inorder3));
        System.out.println("  postorder  = " + Arrays.toString(postorder3));
        System.out.print("  levelOrder result = ");
        printLevelOrder(root3);
        System.out.println("  expect            = [1, null, 2, null, 3, null, 4]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 4 ----------------------
        // Skewed left:
        // Tree: 4 -> 3 -> 2 -> 1 (all to the left)
        // inorder   = [1, 2, 3, 4]
        // postorder = [1, 2, 3, 4]
        // Expected level order: [4, 3, null, 2, null, 1]
        int[] inorder4   = {1, 2, 3, 4};
        int[] postorder4 = {1, 2, 3, 4};

        Solution sol4 = new Solution();
        TreeNode root4 = sol4.buildTree(inorder4, postorder4);

        System.out.println("Test 4:");
        System.out.println("  inorder    = " + Arrays.toString(inorder4));
        System.out.println("  postorder  = " + Arrays.toString(postorder4));
        System.out.print("  levelOrder result = ");
        printLevelOrder(root4);
        System.out.println("  expect            = [4, 3, null, 2, null, 1]");
        System.out.println("-----------------------------------");
    }

    private static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }

        List<String> out = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>(); // LinkedList allows null
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                out.add("null");
                continue;
            }

            out.add(String.valueOf(node.val));
            q.offer(node.left);
            q.offer(node.right);
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
    Map<Integer, Integer> inMap;
    int currIdx;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = postorder.length;
        currIdx = len - 1;
        inMap = new HashMap<>();
        for (int i = 0; i < len; i++){
            inMap.put(inorder[i], i);
        }

        return buildDFS(postorder, 0, len - 1);
    }

    public TreeNode buildDFS(int[] postorder, int inLeft, int inRight) {
        if (inLeft > inRight) return null;

        int rootVal = postorder[currIdx--];
        TreeNode root = new TreeNode(rootVal);
        int mid = inMap.get(rootVal);

        root.right = buildDFS(postorder, mid + 1, inRight);
        root.left = buildDFS(postorder, inLeft, mid - 1);
        return root;
    }
}
