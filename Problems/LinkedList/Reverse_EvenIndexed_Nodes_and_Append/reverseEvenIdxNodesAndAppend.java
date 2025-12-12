package LinkedList.Reverse_EvenIndexed_Nodes_and_Append;

import java.util.*;

public class reverseEvenIdxNodesAndAppend {
    public static void main(String[] args) {
        // 多组测试用例
        int[][] testCases = {
                {1, 2, 3, 4, 5},      // 奇偶都有
                {10, 20, 30, 40},     // 偶数个节点
                {7, 8, 9},            // 3 个节点
                {42},                 // 单节点
                {1, 2}                // 两个节点
        };

        int caseNum = 1;
        for (int[] arr : testCases) {
            System.out.println("===== Test Case " + caseNum++ + " =====");
            SinglyLinkedListNode head = buildList(arr);

            System.out.print("Original list : ");
            printList(head);

            SinglyLinkedListNode modified =
                    Result.extractAndAppendSponsoredNodes(head);

            System.out.print("Modified list : ");
            printList(modified);
            System.out.println();
        }
    }

    // helper：从数组构建链表
    private static SinglyLinkedListNode buildList(int[] arr) {
        if (arr.length == 0) return null;
        SinglyLinkedListNode head = new SinglyLinkedListNode(arr[0]);
        SinglyLinkedListNode curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new SinglyLinkedListNode(arr[i]);
            curr = curr.next;
        }
        return head;
    }

    // helper：打印链表
    private static void printList(SinglyLinkedListNode node) {
        if (node == null) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        while (node != null) {
            System.out.print(node.data);
            node = node.next;
            if (node != null) System.out.print(", ");
        }
        System.out.println("]");
    }
}

    
class SinglyLinkedListNode {
    int data;
    SinglyLinkedListNode next;

    public SinglyLinkedListNode(int val){
        data = val;
        next = null;
    }
}

class Result {

    /*
     * Complete the 'extractAndAppendSponsoredNodes' function below.
     *
     * The function is expected to return an INTEGER_SINGLY_LINKED_LIST.
     * The function accepts INTEGER_SINGLY_LINKED_LIST head as parameter.
     */

    public static SinglyLinkedListNode extractAndAppendSponsoredNodes(SinglyLinkedListNode head) {
        if (head == null || head.next == null) return head;
        
        // since in one traversal, and need to make it reversed, so I think of Stack
        Deque<SinglyLinkedListNode> stack = new ArrayDeque<>();
        
        int counter = 0;
        // need to delete head since its 0 always
        SinglyLinkedListNode sentinel = new SinglyLinkedListNode(-1);
        sentinel.next = head;
        // need to initiate new node, since old node contains its order in .next still, 
        // if blindly append will create a potential cycle!
        stack.push(new SinglyLinkedListNode(sentinel.next.data));
        sentinel.next = sentinel.next.next;
        SinglyLinkedListNode curr = sentinel;
        
        while (curr.next != null) {
            // check next node idx by checking counter + 1
            if ((counter + 1) % 2 == 0) {
                stack.push(new SinglyLinkedListNode(curr.next.data));
                curr.next = curr.next.next;
                counter++;
                continue;
            }
            counter++;
            curr = curr.next;
        }
        
        while (!stack.isEmpty()){
            curr.next = stack.pop();
            curr = curr.next;
        }
        
        return sentinel.next;
    }

}