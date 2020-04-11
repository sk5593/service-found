package com.service.found.test;

import sun.misc.HexDumpEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class LinkedExample {
    /**
     * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
     * 使用一个工具栈
     *
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack();
        ArrayList<Integer> list = new ArrayList();
        while (listNode != null) {
            stack.add(listNode.val);
            listNode = listNode.next;
        }
        while (stack.size() > 0) {
            list.add(stack.pop());
        }
        return list;
    }

    /**
     * 输入一个链表，输出该链表中倒数第k个结点。
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode first = head;
        ListNode last = head;

        int n = 0;
        for (; first != null; n++) {
            //first先走
            first = first.next;
            if (n >= k) {
                //last在循环了k次之后在走，当first走完的时候，last所在的地方就是k的节点值
                last = last.next;
            }
        }
        return n < k ? null : last;
    }

    /**
     * 输入一个链表，反转链表后，输出新链表的表头。
     */
    public ListNode ReverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        if (head == null) return null;
        while (head != null) {
            //用next保存head.next，不然把pre赋值给head.next之后，链表就断了
            next = head.next;
            //将head.next指向pre
            head.next = pre;
            //链表后移
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        //如果list1遍历完了，将剩下的list2返回
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val < list2.val) {
            ListNode merge = Merge(list1.next, list2);
            return merge;
        } else {
            ListNode merge = Merge(list1, list2.next);
            return merge;
        }
    }

    public ListNode Merge2(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        //头结点
        ListNode head = null;
        //用于遍历的节点
        ListNode cur = null;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                //第一次循环
                if (head == null) {
                    //赋值头结点
                    head = cur = list1;
                } else {
                    //指针向后移动
                    cur.next = list1;
                    cur = cur.next;
                }
                //list1后移
                list1 = list1.next;
            } else {
                if (head == null) {
                    head = cur = list2;
                } else {
                    cur.next = list2;
                    cur = cur.next;
                }
                list2 = list2.next;
            }
        }
        //剩余的节点
        if (list1 == null) {
            cur.next = list2;
        }
        if (list2 == null) {
            cur.next = list1;
        }
        return head;
    }

    /**
     * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
     * 返回结果为复制后复杂链表的head。
     * (注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空)
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;
        RandomListNode curr = pHead;
        //1.复制链表
        while (curr != null) {
            RandomListNode cloneList = new RandomListNode(curr.label);
            RandomListNode pHeadNext = curr.next;
            curr.next = cloneList;
            cloneList.next = pHeadNext;
            curr = pHeadNext;
        }
        //2.重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
        curr = pHead;
        while (curr != null) {
            curr.next.random = curr.random==null?null:curr.random.next;
            curr = curr.next.next;
        }
        //3.将复制的链表拆分,就是改变一下指针指向
        curr = pHead;
        RandomListNode pCloneHead = pHead.next;
        RandomListNode pCloneCurr = pCloneHead;
        while (curr != null) {
            RandomListNode currNext = curr.next;
            pCloneCurr.next = currNext.next==null?null:currNext.next.next;
            pCloneCurr = pCloneCurr.next;
            curr = currNext.next;
        }
        return pCloneHead;
    }
    /**
     * 输入两个链表，找出它们的第一个公共结点。
     * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        //可以使用hashMap
        //也可以让长链表先跑它们的长度差，然后再一起跑。
        return null;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

