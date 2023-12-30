package nowcoder.lianbiao;
//合并两个排序的链表
public class BM4 {
    public ListNode Merge (ListNode pHead1, ListNode pHead2) {     //把其中一个插入到另一个
        // write code here
        if(pHead1==null){
            return pHead2;
        }
        if(pHead2==null){
            return pHead1;
        }
        if(pHead1.val> pHead2.val){
            ListNode start1=pHead1 , start2 = pHead2;   //作为起始节点
            ListNode pre1=start1 , pre2 = null;
            while(start1!=null){
                while(start2!=null&&start2.val<=start1.val){    //第一个肯定小于
                    pre2 = start2;
                    start2=start2.next;
                }
                if(start2==null){
                    pre2.next = start1;
                    return pHead2;
                }
                pre1 = start1.next;
                pre2.next = start1;
                start1.next = start2;
                start1 = pre1;
                pre2 =pre2.next;         //最后记得pre2归位，防止下一次循环没有经过pre2 = start2
            }
            return pHead2;
        }else{
            ListNode start1=pHead1 , start2 = pHead2;   //作为起始节点
            ListNode pre1=null , pre2 = start2;
            while(start2!=null){
                while(start1!=null&&start1.val<=start2.val){    //第一个肯定小于等于
                    pre1 = start1;
                    start1=start1.next;
                }
                if(start1==null){
                    pre1.next = start2;
                    return pHead1;
                }
                pre2 = start2.next;
                pre1.next = start2;
                start2.next = start1;
                start2 = pre2;
                pre1=pre1.next;       //最后记得pre1归位，防止下一次循环没有经过pre1 = start1
            }
            return pHead1;
        }

    }


    public ListNode Merge2(ListNode list1,ListNode list2) {   //递归方法
        if(list1==null){
            return list2;
        }
        else if(list2==null){
            return list1;
        }
        if(list2.val>list1.val){
            list1.next = Merge(list1.next,list2);
            return list1;
        }
        else{
            list2.next = Merge(list1,list2.next);
            return list2;
        }
    }

    public ListNode Merge3(ListNode list1,ListNode list2) {    //迭代，新建链表，更加方便
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        // 必须保证两个list都不为空
        while(list1 != null & list2 != null) {
            if(list1.val > list2.val) {
                dummy.next = list2;
                list2 = list2.next;
                dummy = dummy.next;
            } else if(list1.val <= list2.val) {
                dummy.next = list1;
                list1 = list1.next;
                dummy = dummy.next;
            }
        }
        // list1后面还有，就把剩下的全部拿走
        if(list1 != null) {
            dummy.next = list1;
        }
        if(list2 != null) {
            dummy.next = list2;
        }
        return res.next;
    }
    //也可以使用额外数组
}
