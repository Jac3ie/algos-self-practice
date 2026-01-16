package StackAndQueue.Min_Stack_List_Implement;

import java.util.*;

public class minStackListImplement {
    public static void main(String[] args) {
        MinStack st = new MinStack();

        // -----------------------------
        // Test 1: sample-like sequence
        // push 2, push 0, push 3, push 0, getMin, pop, getMin, pop, top, getMin
        // -----------------------------
        System.out.println("Test 1: sample-like sequence");
        st.push(2);
        System.out.println("  push(2)   -> top=" + st.top() + ", min=" + st.getMin());

        st.push(0);
        System.out.println("  push(0)   -> top=" + st.top() + ", min=" + st.getMin());

        st.push(3);
        System.out.println("  push(3)   -> top=" + st.top() + ", min=" + st.getMin());

        st.push(0);
        System.out.println("  push(0)   -> top=" + st.top() + ", min=" + st.getMin());

        System.out.println("  getMin()  -> " + st.getMin()); // expect 0
        st.pop();
        System.out.println("  pop()     -> top=" + st.top() + ", min=" + st.getMin()); // expect top=3, min=0

        System.out.println("  getMin()  -> " + st.getMin()); // expect 0
        st.pop();
        System.out.println("  pop()     -> top=" + st.top() + ", min=" + st.getMin()); // expect top=0, min=0

        System.out.println("  top()     -> " + st.top());    // expect 0
        System.out.println("  getMin()  -> " + st.getMin()); // expect 0
        System.out.println("-----------------------------");

        // -----------------------------
        // Test 2: strictly decreasing values
        // -----------------------------
        System.out.println("Test 2: decreasing values");
        MinStack st2 = new MinStack();
        st2.push(5);
        System.out.println("  push(5)   -> top=" + st2.top() + ", min=" + st2.getMin()); // min=5
        st2.push(4);
        System.out.println("  push(4)   -> top=" + st2.top() + ", min=" + st2.getMin()); // min=4
        st2.push(3);
        System.out.println("  push(3)   -> top=" + st2.top() + ", min=" + st2.getMin()); // min=3
        st2.pop();
        System.out.println("  pop()     -> top=" + st2.top() + ", min=" + st2.getMin()); // min=4
        st2.pop();
        System.out.println("  pop()     -> top=" + st2.top() + ", min=" + st2.getMin()); // min=5
        System.out.println("-----------------------------");

        // -----------------------------
        // Test 3: duplicates of the minimum
        // -----------------------------
        System.out.println("Test 3: duplicate minimum values");
        MinStack st3 = new MinStack();
        st3.push(1);
        System.out.println("  push(1)   -> top=" + st3.top() + ", min=" + st3.getMin()); // min=1
        st3.push(2);
        System.out.println("  push(2)   -> top=" + st3.top() + ", min=" + st3.getMin()); // min=1
        st3.push(1);
        System.out.println("  push(1)   -> top=" + st3.top() + ", min=" + st3.getMin()); // min=1
        st3.pop();
        System.out.println("  pop()     -> top=" + st3.top() + ", min=" + st3.getMin()); // still min=1
        st3.pop();
        System.out.println("  pop()     -> top=" + st3.top() + ", min=" + st3.getMin()); // min=1
        System.out.println("-----------------------------");

        // -----------------------------
        // Test 4: push then pop back to empty (no calls when empty)
        // -----------------------------
        System.out.println("Test 4: back to empty (safe usage)");
        MinStack st4 = new MinStack();
        st4.push(10);
        System.out.println("  push(10)  -> top=" + st4.top() + ", min=" + st4.getMin()); // min=10
        st4.pop();
        System.out.println("  pop()     -> stack is now empty (do NOT call top/getMin now)");
        System.out.println("-----------------------------");
    }
}

class MinStack {

    List<Integer> stack;
    Deque<Integer> minSoFarStack;
    int minSoFar;
    int size;

    public MinStack() {
        stack = new ArrayList<>();
        minSoFarStack = new ArrayDeque<>();
        minSoFar = Integer.MAX_VALUE;
        size = 0;
    }
    
    public void push(int val) {
        stack.add(val);
        size++;
        minSoFar = Math.min(minSoFar, val);
        minSoFarStack.push(minSoFar);
    }
    
    public void pop() {
        stack.remove(size - 1);
        size--;
        minSoFarStack.pop();
        minSoFar = minSoFarStack.peek() == null ? Integer.MAX_VALUE : minSoFarStack.peek();
    }
    
    public int top() {
        return stack.get(size - 1);
    }
    
    public int getMin() {
        return minSoFarStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
