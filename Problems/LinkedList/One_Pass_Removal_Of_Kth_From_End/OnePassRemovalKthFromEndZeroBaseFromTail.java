package LinkedList.One_Pass_Removal_Of_Kth_From_End;

public class OnePassRemovalKthFromEndZeroBaseFromTail {
    public static void main(String[] args) {
        // Test case 1: example from statement
        SinglyLinkedListNode head1 = buildList(new int[]{5, 6, 7, 8});
        System.out.print("Test 1 original: ");
        printList(head1);
        head1 = Result.removeKthNodeFromEnd(head1, 3);
        System.out.print("Test 1 after k=3 : ");
        printList(head1);
        System.out.println("-----------------------------");

        // Test case 2: remove last node (k = 0)
        SinglyLinkedListNode head2 = buildList(new int[]{5, 6, 7, 8});
        System.out.print("Test 2 original: ");
        printList(head2);
        head2 = Result.removeKthNodeFromEnd(head2, 0);
        System.out.print("Test 2 after k=0 : ");
        printList(head2);
        System.out.println("-----------------------------");

        // Test case 3: single-node list, remove tail (k = 0)
        SinglyLinkedListNode head3 = buildList(new int[]{42});
        System.out.print("Test 3 original: ");
        printList(head3);
        head3 = Result.removeKthNodeFromEnd(head3, 0);
        System.out.print("Test 3 after k=0 : ");
        printList(head3);   // should print nothing (empty list)
        System.out.println("-----------------------------");

        // Test case 4: k bigger than list length → should return original list
        SinglyLinkedListNode head4 = buildList(new int[]{1, 2});
        System.out.print("Test 4 original: ");
        printList(head4);
        head4 = Result.removeKthNodeFromEnd(head4, 5);
        System.out.print("Test 4 after k=5 : ");
        printList(head4);   // unchanged
        System.out.println("-----------------------------");
    }

    // helper: build list from array
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

    // helper: print list on one line
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
     * Complete the 'removeKthNodeFromEnd' function below.
     *
     * The function is expected to return an INTEGER_SINGLY_LINKED_LIST.
     * The function accepts following parameters:
     *  1. INTEGER_SINGLY_LINKED_LIST head
     *  2. INTEGER k
     */
    public static SinglyLinkedListNode removeKthNodeFromEnd(SinglyLinkedListNode head, int k) {
        // insert a node at the head to make returning head easier
        SinglyLinkedListNode sentinel = new SinglyLinkedListNode(-1);
        sentinel.next = head;
        
        // make use of two pointer thought to do it
        SinglyLinkedListNode right = head;
        SinglyLinkedListNode left = sentinel;
        
        for (int i = 0; i < k; i++){
            // k > SinglyLinkedList.size()
            if (right.next == null) return head;
            
            right = right.next;
        }
        
        // two pointer move together to keep k length of gap inside
        while (right.next != null){
            right = right.next;
            left = left.next;
        }
        
        left.next = left.next.next;
        return sentinel.next;
    }

}
