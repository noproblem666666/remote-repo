package Leetcode;

//合并两个有序链表
public class rehot12 {
    //迭代解法，注意不要在合并过程中断尾，导致操作链表找不到后续结点
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode pre = new ListNode();
        ListNode tail = pre;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                tail = tail.next;
                //注意不要断尾，不然会把链表弄断，因为tail和list1操作的是同一个结点
                //tail.next = null;
                list1 = list1.next;
            } else {
                tail.next = list2;
                tail = tail.next;
                //tail.next = null;
                list2 = list2.next;
            }
        }
        if (list1 == null) {
            tail.next = list2;
        } else {
            tail.next = list1;
        }
        return pre.next;
    }

    //递归解法
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }

    }


}
