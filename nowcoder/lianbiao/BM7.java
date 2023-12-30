package nowcoder.lianbiao;

import java.util.HashSet;

//链表中环的入口节点
public class BM7 {
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if(pHead ==null){
            return null;
        }
        ListNode fast = pHead;
        ListNode slow = pHead;
        int count = 0;
        while(fast!=null){
            fast = fast.next;
            if(fast==null){
                return null;
            }
            slow =slow.next;
            fast=fast.next;
            count++;             //count，（如果有环）快慢指针的步数差代表环的长度
            if(fast==slow){
                break;
            }
        }
        if(fast == null){
            return null;
        }
        fast = pHead;
        slow = pHead;
        while(count>0){     //fast先走环的长度的步数，然后一起走，相遇便是环的入口
            fast=fast.next;
            count--;
        }
        while(fast!=slow){
            fast=fast.next;
            slow=slow.next;
        }
        return fast;
    }

    //哈希表存储法，需要占用额外的空间(第一次遇到的重复结点便是入口)
    public ListNode EntryNodeOfLoop2(ListNode pHead) {      //双指针法
        HashSet<ListNode> temp = new HashSet<>();
        while(pHead!=null){
            if(temp.contains(pHead)){
                return pHead;
            }
            temp.add(pHead);
            pHead = pHead.next;
        }
        return null;    //只要遍历到了链表尾，就说明没有环
    }


    //根据分析计算，可知从相遇处到入口结点的距离与头结点与入口结点的距离相同。
    public ListNode EntryNodeOfLoop3(ListNode pHead) {
        if(pHead == null) return null;
        // 定义快慢指针
        ListNode slow = pHead;
        ListNode fast = pHead;
        while(fast != null && fast.next != null){
            // 快指针是满指针的两倍速度
            fast = fast.next.next;
            slow = slow.next;
            // 记录快慢指针第一次相遇的结点
            if(slow == fast) break;
        }
        // 若是快指针指向null，则不存在环
        if(fast == null || fast.next == null) return null;
        // 重新指向链表头部
        fast = pHead;
        // 与第一次相遇的结点相同速度出发，相遇结点为入口结点
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
