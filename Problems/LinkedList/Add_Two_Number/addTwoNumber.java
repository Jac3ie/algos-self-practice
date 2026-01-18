package LinkedList.Add_Two_Number;

public class addTwoNumber {
    public static void main(String[] args) {
        // ---------------------- Test Case 1 ----------------------
        // l1 = [2,4,3] (342), l2 = [5,6,4] (465)
        // expected = [7,0,8] (807)
        ListNode l1_1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2_1 = new ListNode(5, new ListNode(6, new ListNode(4)));

        Solution sol = new Solution();
        ListNode ans1 = sol.addTwoNumbers(l1_1, l2_1);

        System.out.println("Test 1:");
        System.out.print("  l1      = "); printList(l1_1);
        System.out.print("  l2      = "); printList(l2_1);
        System.out.print("  result  = "); printList(ans1);
        System.out.println("  expect  = [7 -> 0 -> 8]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 2 ----------------------
        // l1 = [0] (0), l2 = [0] (0)
        // expected = [0] (0)
        ListNode l1_2 = new ListNode(0);
        ListNode l2_2 = new ListNode(0);

        ListNode ans2 = sol.addTwoNumbers(l1_2, l2_2);

        System.out.println("Test 2:");
        System.out.print("  l1      = "); printList(l1_2);
        System.out.print("  l2      = "); printList(l2_2);
        System.out.print("  result  = "); printList(ans2);
        System.out.println("  expect  = [0]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 3 ----------------------
        // l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
        // expected = [8,9,9,9,0,0,0,1]
        ListNode l1_3 = buildList(new int[]{9,9,9,9,9,9,9});
        ListNode l2_3 = buildList(new int[]{9,9,9,9});

        ListNode ans3 = sol.addTwoNumbers(l1_3, l2_3);

        System.out.println("Test 3:");
        System.out.print("  l1      = "); printList(l1_3);
        System.out.print("  l2      = "); printList(l2_3);
        System.out.print("  result  = "); printList(ans3);
        System.out.println("  expect  = [8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1]");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 4 ----------------------
        // Different lengths + carry at the end:
        // l1 = [9,9] (99), l2 = [1] (1)
        // expected = [0,0,1] (100)
        ListNode l1_4 = buildList(new int[]{9,9});
        ListNode l2_4 = buildList(new int[]{1});

        ListNode ans4 = sol.addTwoNumbers(l1_4, l2_4);

        System.out.println("Test 4:");
        System.out.print("  l1      = "); printList(l1_4);
        System.out.print("  l2      = "); printList(l2_4);
        System.out.print("  result  = "); printList(ans4);
        System.out.println("  expect  = [0 -> 0 -> 1]");
        System.out.println("-----------------------------------");
    }

    // Build list from array where arr[0] is head (same order as the problem input)
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

    // Print list like: [2 -> 4 -> 3]
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode(0);
        int carry = 0;
        //keep a ref to tail so can insert to tail
        ListNode tail = sentinel;
        
        while (l1 != null || l2 != null || carry != 0) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int sum = num1 + num2 + carry;
            int digit = sum % 10;
            carry = sum / 10; // if sum less than 10, this will result in 0

            // insert to tail, not bet sentinel and sentinel.next
            tail.next = new ListNode(digit);
            tail = tail.next;

            // move forward
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return sentinel.next;
    }
}
