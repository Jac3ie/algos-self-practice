package Binary_Tree_General.Invert_Binary_Tree;

import java.util.*;

public class invertTree {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ===================== Test Case 1 =====================
        // Tree:
        //      4
        //     / \
        //    2   7
        //   / \ / \
        //  1  3 6  9
        // Inverted should be:
        //      4
        //     / \
        //    7   2
        //   / \ / \
        //  9  6 3  1
        TreeNode t1 = new TreeNode(
                4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9))
        );

        System.out.println("Test 1 (BFS invert):");
        System.out.print("  Original (level-order): ");
        printLevelOrder(t1);
        sol.invertTreeBFS(t1);
        System.out.print("  Inverted (level-order): ");
        printLevelOrder(t1);
        System.out.println("  Expect: [4, 7, 2, 9, 6, 3, 1]");
        System.out.println("-----------------------------------");

        // ===================== Test Case 2 =====================
        // Single node
        TreeNode t2 = new TreeNode(1);

        System.out.println("Test 2 (DFS invert):");
        System.out.print("  Original (level-order): ");
        printLevelOrder(t2);
        sol.invertTreeDFS(t2);
        System.out.print("  Inverted (level-order): ");
        printLevelOrder(t2);
        System.out.println("  Expect: [1]");
        System.out.println("-----------------------------------");

        // ===================== Test Case 3 =====================
        // Tree:
        //   2
        //  / \
        // 1   3
        // Inverted:
        //   2
        //  / \
        // 3   1
        TreeNode t3 = new TreeNode(2, new TreeNode(1), new TreeNode(3));

        System.out.println("Test 3 (BFS invert):");
        System.out.print("  Original (level-order): ");
        printLevelOrder(t3);
        sol.invertTreeBFS(t3);
        System.out.print("  Inverted (level-order): ");
        printLevelOrder(t3);
        System.out.println("  Expect: [2, 3, 1]");
        System.out.println("-----------------------------------");

        // ===================== Test Case 4 =====================
        // Null tree
        TreeNode t4 = null;

        System.out.println("Test 4 (null tree):");
        TreeNode ans4 = sol.invertTreeDFS(t4);
        System.out.print("  Result: ");
        printLevelOrder(ans4);
        System.out.println("  Expect: []");
        System.out.println("-----------------------------------");
    }

    // Print tree in level-order (BFS), ignoring null placeholders for simplicity
    private static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }

        List<Integer> out = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            out.add(node.val);

            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }

        System.out.println(out);
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
    public TreeNode invertTreeBFS(TreeNode root) {
        if (root == null) return root;

        // BFS way to invert using a queue
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offer(root);

        while (!dq.isEmpty()) {
            TreeNode curr = dq.poll();

            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;

            if (curr.left != null) dq.offer(curr.left);
            if (curr.right != null) dq.offer(curr.right);
        }
        return root;
    }

    public TreeNode invertTreeDFS(TreeNode root) {
        if (root == null) return root;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTreeDFS(root.left);
        invertTreeDFS(root.right);
        return root;
    }
}
