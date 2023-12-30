package nowcoder.lianbiao;

import java.util.HashSet;
import java.util.List;

//判断链表中是否有环
public class BM6 {
    public boolean hasCycle(ListNode head) {      //双指针法
        if(head ==null){
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null){
            fast = fast.next;
            if(fast==null){
                return false;
            }
            slow =slow.next;
            fast=fast.next;
            if(fast==slow){
                return true;
            }
        }
        return false;    //只要遍历到了链表尾，就说明没有环
    }

    //哈希表存储法，需要占用额外的空间
    public boolean hasCycle2(ListNode head) {      //双指针法
        HashSet<ListNode> temp = new HashSet<>();
        while(head!=null){
            if(temp.contains(head)){
                return true;
            }
            temp.add(head);
            head = head.next;
        }
        return false;    //只要遍历到了链表尾，就说明没有环
    }
}
