package nowcoder.lianbiao;
//链表的奇偶重排
public class BM14 {

    //双指针指的是在遍历对象的过程中，不是普通的使用单个指针进行访问，而是使用两个指针（特殊情况甚至可以多个）
    //两个指针或是同方向访问两个链表、或是同方向访问一个链表（快慢指针）、或是相反方向扫描（对撞指针），从而达到我们需要的目的。
    public ListNode oddEvenList (ListNode head) {         //双指针法（空间复杂度为o（1））
        // write code here
        if(head==null||head.next==null||head.next.next==null){      //一定要用||
            return head;
        }
        ListNode oddLast =head ;//记录奇数链表的最后一个结点
        ListNode evenLast = head.next;   //记录偶数链表的最后一个结点
        ListNode evenFirst = head.next;   //记录偶数链表的第一个结点
        while(evenLast.next!=null){
            ListNode temp = evenLast.next;
            evenLast.next = temp.next;
            oddLast.next = temp;
            oddLast=oddLast.next;
            if(evenLast.next==null){
                oddLast.next =evenFirst;     //最后退出的时候把奇偶链表接上
                return head;
            }
            evenLast = evenLast.next;
        }
        oddLast.next =evenFirst;          //最后退出的时候把奇偶链表接上
        return head;
    }

    //也可以使用额外辅助空间，如数组，新建链表等，更加简单
}
