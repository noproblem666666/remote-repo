package nowcoder.lianbiao;
//删除链表的倒数第n个结点
public class BM9 {
    public ListNode removeNthFromEnd (ListNode head, int n) {    //双指针
        // write code here
        if(head == null || n==0){
            return null;
        }
        ListNode res = new ListNode(0);        //虚拟头结点(这样就不用特殊处理头结点，便于返回结果)
        res.next = head;
        ListNode pre = res;                       //因为要删除，所以我们需要保存的是被删除结点的前置结点
        ListNode start = head;
        while(n>1){         //题目保证n是有效的
            start=start.next;
            n--;
        }
        while(start.next!=null){
            start=start.next;
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return res.next;
    }

    public ListNode removeNthFromEnd2 (ListNode head, int n) {    //计算链表长度，遍历两次
        // write code here
        int length = 0;
        ListNode p = head;
        ListNode q = head;
        // 获取链表的长度
        while(head != null){
            length++;
            head = head.next;
        }
        if(length < 2){               //这里默认链表长度大于1，也就是删除后链表不会为空
            return null;
        }
        // 特殊情况
        if(n == length){
            return q.next;
        }
        int i = 0;
        while(p != null){
            i++;
            if(i == length - n){
                // 删除结点
                p.next = p.next.next;
            }
            p = p.next;
        }
        return q;
    }
}
