package nowcoder.lianbiao;

import java.util.HashSet;

//两个链表的第一个公共结点
public class BM10 {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {   //计算长度差
        if(pHead1==null||pHead2==null){
            return null;
        }
        int len1 = 0,len2=0;
        ListNode start1 =pHead1,start2 = pHead2;
        while(start1!=null){
            len1++;
            start1 = start1.next;
        }
        while(start2!=null){
            len2++;
            start2=start2.next;
        }
        if(len1>len2){
            return findNode(len1-len2,pHead1,pHead2);
        }else{
            return findNode(len2-len1,pHead2,pHead1);
        }
    }
    public ListNode findNode(int count,ListNode head1 ,ListNode head2){    //找出公共结点的函数
        while(count>0){
            head1=head1.next;
            count--;
        }
        while(head1!=null){
            if(head1==head2){
                return head2;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return null;
    }

    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {    //双指针
        ListNode l1 = pHead1, l2 = pHead2;
        while(l1 != l2){
            l1 = (l1==null)?pHead2:l1.next;     //如果自己走完，就从另一条链表开始走起，这样相遇时走过的步数相同，有公共结点就必定会在第一个公共结点处相交
            l2 = (l2==null)?pHead1:l2.next;
        }
        return l1;
    }

    public ListNode FindFirstCommonNode3(ListNode pHead1, ListNode pHead2) {   //用哈希表统计
        //创建集合set
        HashSet<ListNode> set = new HashSet<>();
        //先把链表1的结点全部存放到集合set中
        while (pHead1 != null) {
            set.add(pHead1);
            pHead1 = pHead1.next;
        }

        //然后访问链表2的结点，判断集合中是否包含链表2的结点，如果包含就直接返回
        while (pHead2 != null) {
            if (set.contains(pHead2))
                return pHead2;
            pHead2 = pHead2.next;
        }
        //如果集合set不包含链表2的任何一个结点，说明没有交点，直接返回null
        return null;
    }
}
