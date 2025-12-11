package LinkedList.Remove_Consecutive_Duplicate_From_Sorted_LL;

public class removeConsecutiveDupFromLL {
    public static void main(String[] args) {
        // Test case 1: empty list
        SinglyLinkedListNode head1 = null;
        runTest(head1, "Test 1: empty list");

        // Test case 2: single node
        SinglyLinkedListNode head2 = buildList(new int[]{5});
        runTest(head2, "Test 2: single node [5]");

        // Test case 3: duplicates in the middle
        SinglyLinkedListNode head3 = buildList(new int[]{1, 1, 2, 3, 3});
        runTest(head3, "Test 3: [1, 1, 2, 3, 3]");

        // Test case 4: all elements the same
        SinglyLinkedListNode head4 = buildList(new int[]{7, 7, 7, 7});
        runTest(head4, "Test 4: [7, 7, 7, 7]");

        // Test case 5: already unique
        SinglyLinkedListNode head5 = buildList(new int[]{1, 2, 3, 4});
        runTest(head5, "Test 5: [1, 2, 3, 4]");

        // Test case 6: duplicates only at the end
        SinglyLinkedListNode head6 = buildList(new int[]{1, 2, 3, 3, 3});
        runTest(head6, "Test 6: [1, 2, 3, 3, 3]");
    }

    private static void runTest(SinglyLinkedListNode head, String label) {
        System.out.println(label);
        System.out.print("  Original: ");
        printList(head);

        SinglyLinkedListNode cleaned = Result.deleteDuplicates(head);

        System.out.print("  Cleaned : ");
        printList(cleaned);
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

    // helper: print list
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

    public SinglyLinkedListNode(int val){ data = val; }
}

class Result {

    /*
     * Complete the 'deleteDuplicates' function below.
     *
     * The function is expected to return an INTEGER_SINGLY_LINKED_LIST.
     * The function accepts INTEGER_SINGLY_LINKED_LIST head as parameter.
     */
    public static SinglyLinkedListNode deleteDuplicates(SinglyLinkedListNode head) {
        SinglyLinkedListNode curr = head;
        if (curr == null || curr.next == null) return head; // not have/ have only one elt inside
        int prev = curr.data;
        
        while (curr.next != null) {
           if (curr.next.data == prev) {
            // delete next node
            curr.next = curr.next.next;
           } else {
            curr = curr.next;
            prev = curr.data; // swap order => result wrong, note which one should go first
           }
        }

        return head;
    }

}