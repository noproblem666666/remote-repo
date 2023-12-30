package nowcoder.lianbiao;
//反转链表
class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}
public class BM1 {
    public ListNode ReverseList(ListNode head) {    //要求空间复杂度为o(1),原地反转
        // write code here

        if(head == null || head.next==null){
            return head;
        }

        ListNode start = head;
        ListNode next = head.next;
        start.next = null;           //先把开头结点的后续断掉

        while(next!=null) {         //原地反转
            ListNode temp = next.next;
            next.next = start;
            start = next;
            next = temp;
        }

        return start;
    }
}
