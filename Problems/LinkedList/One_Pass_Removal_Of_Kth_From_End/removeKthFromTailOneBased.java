package LinkedList.One_Pass_Removal_Of_Kth_From_End;

public class removeKthFromTailOneBased {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------- Test 1: example-style case ----------
        ListNode head1 = buildList(new int[]{5, 6, 7, 8});
        System.out.print("Test 1 original: ");
        printList(head1);
        head1 = sol.removeNthFromEnd(head1, 3);  // remove 3rd from end
        System.out.print("Test 1 after n=3 : ");
        printList(head1); // expected: [5, 7, 8] if n is 3rd from end, or [6,7,8] if problem defines differently
        System.out.println("-----------------------------");

        // ---------- Test 2: remove last node (n = 1) ----------
        ListNode head2 = buildList(new int[]{1, 2, 3});
        System.out.print("Test 2 original: ");
        printList(head2);
        head2 = sol.removeNthFromEnd(head2, 1);
        System.out.print("Test 2 after n=1 : ");
        printList(head2);  // expected: [1, 2]
        System.out.println("-----------------------------");

        // ---------- Test 3: remove head (n = length) ----------
        ListNode head3 = buildList(new int[]{10, 20, 30});
        System.out.print("Test 3 original: ");
        printList(head3);
        head3 = sol.removeNthFromEnd(head3, 3);  // length = 3
        System.out.print("Test 3 after n=3 : ");
        printList(head3);  // expected: [20, 30]
        System.out.println("-----------------------------");

        // ---------- Test 4: single-node list ----------
        ListNode head4 = buildList(new int[]{42});
        System.out.print("Test 4 original: ");
        printList(head4);
        head4 = sol.removeNthFromEnd(head4, 1);
        System.out.print("Test 4 after n=1 : ");
        printList(head4);  // expected: []
        System.out.println("-----------------------------");

        // ---------- Test 5: n larger than length (your guard path) ----------
        ListNode head5 = buildList(new int[]{9, 8});
        System.out.print("Test 5 original: ");
        printList(head5);
        head5 = sol.removeNthFromEnd(head5, 5);  // n > length -> returns original head
        System.out.print("Test 5 after n=5 : ");
        printList(head5);  // expected unchanged: [9, 8]
        System.out.println("-----------------------------");
    }

    // helper: build list from int[]
    private static ListNode buildList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        return head;
    }

    // helper: print list
    private static void printList(ListNode node) {
        if (node == null) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        while (node != null) {
            System.out.print(node.val);
            node = node.next;
            if (node != null) System.out.print(", ");
        }
        System.out.println("]");
    }
}




// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // set a sentinel node for quick reference to head
        ListNode sentinel = new ListNode();
        sentinel.next = head;

        ListNode left = sentinel;
        ListNode right = sentinel;
        for (int i = 0; i < n; i++) {
            if (right.next == null) return head; // for 1-based version, n <= length, at most lenth

            right = right.next;
        }

        while (right.next != null) {
            right = right.next;
            left = left.next;
        }

        left.next = left.next.next;
        return sentinel.next;
    }
}