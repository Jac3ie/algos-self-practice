package LinkedList.Reverse_Sub_LinkedList;

public class reverseSubLinkedList {
    public static void main(String[] args) {

        Solution sol = new Solution();

        // ---------------------- Test Case 1 ----------------------
        // head = [1,2,3,4,5], left = 2, right = 4
        // expected = [1,4,3,2,5]
        ListNode head1 = buildList(new int[]{1,2,3,4,5});
        ListNode ans1 = sol.reverseBetween(head1, 2, 4);

        System.out.println("Test 1:");
        System.out.print("  head    = "); printList(buildList(new int[]{1,2,3,4,5}));
        System.out.println("  left    = 2, right = 4");
        System.out.print("  result  = "); printList(ans1);
        System.out.println("  expect  = [1 -> 4 -> 3 -> 2 -> 5]");
        System.out.println("-----------------------------------");


        // ---------------------- Test Case 2 ----------------------
        // Reverse from head
        // head = [1,2,3,4,5], left = 1, right = 3
        // expected = [3,2,1,4,5]
        ListNode head2 = buildList(new int[]{1,2,3,4,5});
        ListNode ans2 = sol.reverseBetween(head2, 1, 3);

        System.out.println("Test 2:");
        System.out.print("  head    = "); printList(buildList(new int[]{1,2,3,4,5}));
        System.out.println("  left    = 1, right = 3");
        System.out.print("  result  = "); printList(ans2);
        System.out.println("  expect  = [3 -> 2 -> 1 -> 4 -> 5]");
        System.out.println("-----------------------------------");


        // ---------------------- Test Case 3 ----------------------
        // Reverse tail segment
        // head = [1,2,3,4,5], left = 4, right = 5
        // expected = [1,2,3,5,4]
        ListNode head3 = buildList(new int[]{1,2,3,4,5});
        ListNode ans3 = sol.reverseBetween(head3, 4, 5);

        System.out.println("Test 3:");
        System.out.print("  head    = "); printList(buildList(new int[]{1,2,3,4,5}));
        System.out.println("  left    = 4, right = 5");
        System.out.print("  result  = "); printList(ans3);
        System.out.println("  expect  = [1 -> 2 -> 3 -> 5 -> 4]");
        System.out.println("-----------------------------------");


        // ---------------------- Test Case 4 ----------------------
        // Single node, no change
        // head = [5], left = 1, right = 1
        // expected = [5]
        ListNode head4 = buildList(new int[]{5});
        ListNode ans4 = sol.reverseBetween(head4, 1, 1);

        System.out.println("Test 4:");
        System.out.print("  head    = "); printList(buildList(new int[]{5}));
        System.out.println("  left    = 1, right = 1");
        System.out.print("  result  = "); printList(ans4);
        System.out.println("  expect  = [5]");
        System.out.println("-----------------------------------");


        // ---------------------- Test Case 5 ----------------------
        // Reverse entire list
        // head = [1,2,3,4], left = 1, right = 4
        // expected = [4,3,2,1]
        ListNode head5 = buildList(new int[]{1,2,3,4});
        ListNode ans5 = sol.reverseBetween(head5, 1, 4);

        System.out.println("Test 5:");
        System.out.print("  head    = "); printList(buildList(new int[]{1,2,3,4}));
        System.out.println("  left    = 1, right = 4");
        System.out.print("  result  = "); printList(ans5);
        System.out.println("  expect  = [4 -> 3 -> 2 -> 1]");
        System.out.println("-----------------------------------");
    }

    // Build list from array where arr[0] is head
    private static ListNode buildList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        return head;
    }

    // Print list like: [1 -> 2 -> 3]
    private static void printList(ListNode node) {
        if (node == null) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        while (node != null) {
            System.out.print(node.val);
            node = node.next;
            if (node != null) System.out.print(" -> ");
        }
        System.out.println("]");
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(){}

    public ListNode(int val){
        this.val = val;
    }

    public ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        ListNode sentinel = new ListNode(0, head);
        // start from sentinel incase the left = 1, then leftPrev can be sentinel
        ListNode leftPrev = sentinel;
        
        // move the pointer to the leftPrev
        for (int i = 1; i < left; i++) {
            leftPrev = leftPrev.next;
        }

        // make a ref to curr where we gonna move it one pos to right every iteration
        ListNode curr = leftPrev.next;
        for (int i = 0; i < right - left; i++) {
            /*
            strategy is: 1. 架空move 2. move.next to leftPrev.next 3. leftPrev.next = move
            */
            ListNode move = curr.next; // what we gonna move to prev.next eventually
            curr.next = move.next; // isolate the move position elt => 2
            move.next = leftPrev.next; // link the move pos elt to leftPrev.next => 3
            leftPrev.next = move; // => 3
        }

        return sentinel.next;
    }
}
