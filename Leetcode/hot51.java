package Leetcode;

import java.util.HashSet;
import java.util.Set;

public class hot51 {
    public ListNode detectCycle(ListNode head) {   //用哈希表存储，遇到相同的就直接返回
        Set<ListNode> set = new HashSet<>();
        if(head==null){
            return null;
        }
        while(head!=null){
            if(set.contains(head)){
                return head;
            }
            set.add(head);
            head = head.next;

        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {   //快慢指针
        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        int temp = 1;
        while(fast!=null){
            fast=fast.next;
            if(fast==slow){
                break;
            }
            if(fast==null){
                return null;
            }
            fast=fast.next;
            slow = slow.next;
            temp++;                //temp求出环的长度
        }
        if(fast==null){
            return null;
        }
        fast =head;
        slow = head;
        while(temp>0){
            fast = fast.next;       //fast先走环的长度的步数
            temp--;
        }
        while(fast!=slow){          //他们下一次相遇便是环的入口（条件不能是null，因为这里以及确实存在环）
            fast=fast.next;
            slow = slow.next;
        }
        return slow;
    }

}
