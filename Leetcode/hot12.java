package Leetcode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class hot12 {
    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {     //循环遍历链表方式，不够简洁！！！
        if (list1 == null) {         //考虑空链表的情况
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode resNode = new ListNode();    //选定起始节点
        if(list1.val>list2.val){
            resNode.val=list2.val;
            list2=list2.next;
        }else{
            resNode.val=list1.val;
            list1=list1.next;
        }

        ListNode indexNode = new ListNode();
        indexNode = resNode;
        while (list1 != null && list2 != null) {   //要生成新的升序链表，用尾插法
            if (list1.val > list2.val) {
                ListNode listNode = new ListNode(list2.val);
                indexNode.next = listNode;
                indexNode = listNode;
                list2 = list2.next;
            } else {
                ListNode listNode = new ListNode(list1.val);
                indexNode.next = listNode;
                indexNode = listNode;
                list1 = list1.next;
            }
        }
        if (list1 != null) {
            indexNode.next = list1;
        } else {
            indexNode.next = list2;
        }
        return resNode;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {      //递归方式，非常简洁！！！
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);           //递归返回较小的结点
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }

}
