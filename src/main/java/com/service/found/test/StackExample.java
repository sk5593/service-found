package com.service.found.test;

import java.util.Iterator;
import java.util.Stack;

public class StackExample {
    /**
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
     * 注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
     * 第一次添加：将元素加入栈和辅助栈
     * 后面的添加：将元素加入栈，并将元素和辅助栈中栈顶元素比较，如果小，就入辅助栈，如果大，就不用入辅助栈
     * 出栈的时候，比较出栈元素和辅助栈的栈顶元素，如果相等，两个栈都要出栈，辅助栈下面的是次小值。
     */
    Stack<Integer> stack = new Stack<Integer>();
    //辅助栈
    Stack<Integer> minStack = new Stack<>();
    int first = Integer.MAX_VALUE;

    public void push(int node) {
        if (minStack.size() == 0) {
            //第一次入栈
            minStack.push(node);
            first = node;
        } else {
            Integer peek = minStack.peek();
            if (node < peek) {
                minStack.push(node);
                first = node;
            }
        }
        stack.push(node);
    }

    public void pop() {
        Integer pop = stack.pop();
        if (pop == minStack.peek()) {
            minStack.pop();
            first = minStack.peek();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return first;
    }

    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
     * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
     * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     * 使用辅助栈
     * 比如添加1!=4，继续添加，
     * 添加到4，4==4，出栈，popA右移，
     * 3==3，出栈，popA右移，
     * 2！=5，继续压栈，
     * 5==5,出栈，右移，
     * 1！=2，结束循环
     * 此时stack为空的话，说明是弹出序列
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        boolean result = false;
        Stack<Integer> stack = new Stack<>();
        int popIndex = 0;
        for (int x = 0; x < pushA.length; x++) {
            stack.push(pushA[x]);
            while (!stack.isEmpty()&&stack.peek() == popA[popIndex]){
                popIndex++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
