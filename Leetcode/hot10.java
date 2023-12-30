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
//class ListNode1 {
//    int val;
//    ListNode next;
//
//    ListNode1() {
//    }
//
//    ListNode1(int val) {
//        this.val = val;
//    }
//
//    ListNode1(int val, ListNode next) {
//        this.val = val;
//        this.next = next;
//    }
//}

public class hot10 {
    public static void main(String[] args) {

    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = head;
        ListNode del =start;
        ListNode pre = start;
        int i=0;
        while(i<n-1){

            del=del.next;
            i++;
        }
        while(del.next!=null){
            pre = head;
            head=head.next;
            del=del.next;
        }

        if(pre==head){
            return pre.next;
        }
        pre.next=head.next;
        return start;
    }
}
