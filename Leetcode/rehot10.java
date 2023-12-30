package Leetcode;

import java.util.Deque;
import java.util.LinkedList;

//删除链表的倒数第N个结点
public class rehot10 {
    //快慢指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null||n<1){
            return head;
        }
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode slow = pre;
        ListNode fast = head;
        while(n>1){
            fast = fast.next;
            n--;
        }
        //找到要删除结点的前一结点
        while(fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
        //删除操作
        slow.next = slow.next.next;
        return pre.next;
    }


    //计算链表长度
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    //栈
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }




}
