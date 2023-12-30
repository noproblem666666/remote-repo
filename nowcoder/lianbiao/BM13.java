package nowcoder.lianbiao;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//判断一个链表是否为回文结构
public class BM13 {
    public boolean isPail (ListNode head) {      //使用辅助栈
        // write code here
        if(head==null||head.next==null){
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode start = head;
        while(start!=null){
            stack.push(start.val);
            start=start.next;
        }
        while(head!=null){
            if(head.val!=stack.pop()){
                return false;
            }
            head=head.next;
        }
        return true;
    }

    public boolean isPail2 (ListNode head) {    //链表转动态数组
        // write code here
        // n==1，返回true
        if (head.next == null){
            return true;
        }
        List<Integer> nums = new ArrayList<Integer>();
        // 将链表转换成list
        while(head!=null){
            nums.add(head.val);
            head = head.next;
        }
        int i = 0;
        int j = nums.size()-1;
        while(i<j){
            // 不等则返回false
            // 这边有一个坑，如果不适用equals而使用!=的话，在牛客上对于有负数的测试用例可能会无法通过。
            if (!nums.get(i).equals(nums.get(j))){
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }





    public boolean isPail3 (ListNode head) {    //双指针找中点再反转，空间复杂度为o（1）
        //空链表直接为回文
        if(head == null)
            return true;
        //准备快慢双指针
        ListNode slow = head;
        ListNode fast = head;
        //双指针找中点
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //中点处反转
        slow = reverse(slow);
        fast = head;
        while(slow != null){
            //比较判断节点值是否相等
            if(slow.val != fast.val)
                return false;
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }
    ListNode reverse(ListNode head) {
        //前序节点
        ListNode prev = null;
        while(head != null){
            //断开后序
            ListNode next = head.next;
            //指向前序
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
