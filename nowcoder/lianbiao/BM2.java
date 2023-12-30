package nowcoder.lianbiao;
//链表内指定区间反转
public class BM2 {
    public ListNode reverseBetween (ListNode head, int m, int n) {     //可以设置虚拟头结点，这样就不用讨论头结点情况了
        // write code here
        if(head==null||head.next==null){
            return head;
        }

        ListNode start = head;
        ListNode pre = null;
        int len = n-m;   //需要反转的区间长度
        if(len==0){          //等于0则直接返回
            return start;
        }
        while(m>1){     //找到起点
            pre = start;
            start = start.next;
            m--;
        }

        ListNode next = start.next;      //把开头断掉，会为后面的返回埋坑，要小心
        start.next=null;

        while(len>0){                  //反转区间
            ListNode temp = next.next;
            next.next = start;
            start=next;
            next=temp;
            len--;
        }
        if(pre !=null){               //如果起点不是开头，就要把前半段接上
            pre.next = start;
            start = pre;
        }
        ListNode end = start;          //找到区间最后
        while(end.next!=null){
            end=end.next;
        }
        end.next = next;              //接上后半段

        if(pre ==null){               //起点是开头的情况下，返回的应该就是反转过后的区间
            return start;             //如果这时候返回开头，开头已经被断掉了
        }
        return head;
    }

    public ListNode reverseBetween2 (ListNode head, int m, int n) {   //答案二次遍历法，更好理解
        //设置虚拟头节点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        //1.走left-1步到left的前一个节点
        for(int i=0;i<m-1;i++){
            pre = pre.next;
        }

        //2.走roght-left+1步到right节点
        ListNode rigthNode = pre;
        for(int i=0;i<n-m+1;i++){
            rigthNode = rigthNode.next;
        }

        //3.截取出一个子链表
        ListNode leftNode = pre.next;
        ListNode cur = rigthNode.next;

        //4.切断链接
        pre.next=null;
        rigthNode.next=null;

        //5.反转局部链表
        reverseLinkedList(leftNode);

        //6.接回原来的链表
        pre.next = rigthNode;
        leftNode.next = cur;
        return dummyNode.next;
    }
    //反转局部链表
    private void reverseLinkedList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null){
            //Cur_next 指向cur节点的下一个节点
            ListNode Cur_next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = Cur_next ;
        }
    }


    public ListNode reverseBetween3 (ListNode head, int m, int n) {    //答案一次遍历法，更加简洁
        //设置虚拟头节点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next =head;
        ListNode pre = dummyNode;
        for(int i=0;i<m-1;i++){
            pre = pre.next;
        }

        ListNode cur = pre.next;
        ListNode Cur_next ;
        for(int i=0;i<n-m;i++){
            Cur_next = cur.next;
            cur.next = Cur_next.next;
            Cur_next .next = pre.next;
            pre.next = Cur_next ;
        }
        return dummyNode.next;
    }
}
