package Graph.Deep_Clone_Graph;

import java.util.*;

public class cloneGraph {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------------------- Test Case 1 ----------------------
        // Graph (undirected) from example:
        // 1 -- 2
        // |    |
        // 4 -- 3
        //
        // adjList = [[2,4],[1,3],[2,4],[1,3]]
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        // connect neighbors (undirected)
        n1.neighbors.add(n2); n1.neighbors.add(n4);
        n2.neighbors.add(n1); n2.neighbors.add(n3);
        n3.neighbors.add(n2); n3.neighbors.add(n4);
        n4.neighbors.add(n1); n4.neighbors.add(n3);

        Node clone1 = sol.cloneGraph(n1);

        System.out.println("Test 1:");
        System.out.println("  Original graph (BFS adjacency):");
        printAdjBFS(n1);
        System.out.println("  Cloned graph (BFS adjacency):");
        printAdjBFS(clone1);

        System.out.println("  Quick checks:");
        System.out.println("    same reference root? " + (n1 == clone1)); // should be false
        System.out.println("    root value same?     " + (n1.val == clone1.val)); // should be true
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 2 ----------------------
        // Single node, no neighbors:
        // adjList = [[]]
        Node single = new Node(1);
        Node clone2 = sol.cloneGraph(single);

        System.out.println("Test 2:");
        System.out.println("  Original graph (BFS adjacency):");
        printAdjBFS(single);
        System.out.println("  Cloned graph (BFS adjacency):");
        printAdjBFS(clone2);

        System.out.println("  Quick checks:");
        System.out.println("    same reference root? " + (single == clone2)); // should be false
        System.out.println("    neighbors size same? " + (single.neighbors.size() == clone2.neighbors.size())); // true (0)
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 3 ----------------------
        // A triangle: 1-2-3-1
        Node a1 = new Node(1);
        Node a2 = new Node(2);
        Node a3 = new Node(3);

        a1.neighbors.add(a2); a1.neighbors.add(a3);
        a2.neighbors.add(a1); a2.neighbors.add(a3);
        a3.neighbors.add(a1); a3.neighbors.add(a2);

        Node clone3 = sol.cloneGraph(a1);

        System.out.println("Test 3:");
        System.out.println("  Original graph (BFS adjacency):");
        printAdjBFS(a1);
        System.out.println("  Cloned graph (BFS adjacency):");
        printAdjBFS(clone3);

        System.out.println("  Quick checks:");
        System.out.println("    same reference root? " + (a1 == clone3)); // should be false
        System.out.println("    neighbors size same? " + (a1.neighbors.size() == clone3.neighbors.size())); // true (0)
        System.out.println("-----------------------------------");
    }

    /**
     * Print graph as BFS adjacency list style:
     * Node 1 -> [2,4]
     * Node 2 -> [1,3]
     * ...
     *
     * Avoid infinite loops by using a visited set.
     */
    private static void printAdjBFS(Node start) {
        if (start == null) {
            System.out.println("  (null graph)");
            return;
        }

        Deque<Node> q = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();

        q.offer(start);
        visited.add(start);

        while (!q.isEmpty()) {
            Node curr = q.poll();

            System.out.print("  Node " + curr.val + " -> [");
            for (int i = 0; i < curr.neighbors.size(); i++) {
                Node nei = curr.neighbors.get(i);
                System.out.print(nei.val);
                if (i != curr.neighbors.size() - 1) System.out.print(", ");

                if (!visited.contains(nei)) {
                    visited.add(nei);
                    q.offer(nei);
                }
            }
            System.out.println("]");
        }
    }
}


// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // use a map that original node => cloned node
        Map<Node, Node> oriMapClone = new HashMap<>();
        Deque<Node> dq = new ArrayDeque<>();
        dq.offer(node);
        oriMapClone.put(node, new Node(node.val));

        while (!dq.isEmpty()) {
            Node curr = dq.poll();
            Node currCopy = oriMapClone.get(curr);

            // loop on the nbrs of curr node
            for (Node nbr : curr.neighbors) {
                // for each nbr, make sure exist in the map
                if (!oriMapClone.containsKey(nbr)) {
                    oriMapClone.put(nbr, new Node(nbr.val));
                    dq.offer(nbr); // enqueue to dq, since new encountered node is the direct of BFS
                }

                // made sure when we add the adjcent nodes, the adj nodes are all exists,
                // this is through HashMap oriMapClone
                currCopy.neighbors.add(oriMapClone.get(nbr));
            }
        }
        return oriMapClone.get(node); // up here, the copyNode will be constructed fully
    }
}
