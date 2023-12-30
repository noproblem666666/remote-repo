package Leetcode;
//两数相加
public class rehot02 {
    //对每个结点计算对应值并插入
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp = 0;
        ListNode head = new ListNode();
        ListNode tail = head;
        while(l1!=null&&l2!=null){
            int sum = l1.val+l2.val+temp;
            if(sum>=10){
                temp = 1;
                sum-=10;
            }else{
                temp = 0;
            }
            //使用尾插法，正好对上顺序
            ListNode node = new ListNode(sum);
            tail.next=node;
            tail=node;
            l1=l1.next;
            l2=l2.next;
        }
        while(l1!=null){
            int sum = l1.val+temp;
            if(sum>=10){
                temp = 1;
                sum-=10;
            }else{
                temp=0;
            }
            ListNode node = new ListNode(sum);
            tail.next=node;
            tail=node;
            l1=l1.next;
        }
        while(l2!=null){
            int sum = l2.val+temp;
            if(sum>=10){
                temp = 1;
                sum-=10;
            }else{
                temp=0;
            }
            ListNode node = new ListNode(sum);
            tail.next=node;
            tail=node;
            l2=l2.next;
        }
        //最后一位仍然有可能进位
        if(temp == 1){
            ListNode node = new ListNode(1);
            tail.next=node;
            tail=node;
        }
        //返回的是head.next，而不是直接返回head
        return head.next;
    }

    //答案写法，更加简洁
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }
        //最后可能的进位
        if(carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }


}
