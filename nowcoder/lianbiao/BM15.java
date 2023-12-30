package nowcoder.lianbiao;
//删除有序链表中重复的元素（一）
public class BM15 {
    public ListNode deleteDuplicates (ListNode head) {
        // write code here
        if(head ==null||head.next==null){
            return head;
        }
        int temp = head.val;
        ListNode pre = head;
        ListNode start = head;
        while(start.next!=null){
            while(start.next!=null && start.next.val==temp ){
                start = start.next;
            }
            pre.next = start.next;
            if(pre.next == null){     //到达链表尾后就提前退出，避免越界错误
                return head;
            }
            pre = pre.next;
            start=start.next;       //这里如果start == null，那么下一次取next就会出错
            temp = start.val;
        }
        return head;
    }

    public ListNode deleteDuplicates2 (ListNode head) {     //答案遍历删除
        //空链表
        if(head == null)
            return null;
        //遍历指针
        ListNode cur = head;
        //指针当前和下一位不为空
        while(cur != null && cur.next != null){
            //如果当前与下一位相等则忽略下一位
            if(cur.val == cur.next.val)
                cur.next = cur.next.next;
                //否则指针正常遍历
            else
                cur = cur.next;
        }
        return head;
    }
}
