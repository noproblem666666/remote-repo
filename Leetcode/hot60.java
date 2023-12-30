package Leetcode;

public class hot60 {
    public ListNode reverseList(ListNode head) {     //迭代方法
        if(head==null||head.next==null){
            return head;
        }
        ListNode oneNode=head;      //记录当前结点
        ListNode twoNode=head.next;      //记录第二个结点
        ListNode threeNode;     //记录第三个结点
        while(twoNode!=null){
            threeNode = twoNode.next;
            twoNode.next=oneNode;
            if(oneNode==head){
                oneNode.next=null;
            }
            oneNode=twoNode;
            twoNode=threeNode;

        }
        return oneNode;
    }

    public ListNode reverseList2(ListNode head) {      //答案的迭代，更加简洁
        //申请节点，pre和 cur，pre指向null
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while(cur!=null) {
            //记录当前节点的下一个节点
            tmp = cur.next;
            //然后将当前节点指向pre
            cur.next = pre;
            //pre和cur节点都前进一位
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public ListNode reverseList3(ListNode head) {     //递归，较难理解
        //递归终止条件是当前为空，或者下一个节点为空
        if(head==null || head.next==null) {
            return head;
        }
        //这里的cur就是最后一个节点
        ListNode cur = reverseList(head.next);
        //这里请配合动画演示理解
        //如果链表是 1->2->3->4->5，那么此时的cur就是5
        //而head是4，head的下一个是5，下下一个是空
        //所以head.next.next 就是5->4
        head.next.next = head;
        //防止链表循环，需要将head.next设置为空
        head.next = null;
        //每层递归函数都返回cur，也就是最后一个节点
        return cur;
    }





}
