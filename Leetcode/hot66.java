package Leetcode;

import java.util.*;

//回文链表
public class hot66 {
    public boolean isPalindrome(ListNode head) {    //链表不为空
        ListNode fast = head.next;
        ListNode slow = head;
        Stack<Integer> stack = new Stack<>();     //用栈存储前半段数字，正好是倒序和后半段比较
        stack.push(slow.val);
        int count = 1;
        while(fast!=null){        //用slow找到中间结点
            fast=fast.next;
            count++;
            if(fast!=null){
                fast=fast.next;
                slow= slow.next;
                stack.push(slow.val);     //存储的前半段数字
                count++;
            }
        }
        if(count%2!=0){    //如果有奇数个结点，那么中间的结点值就不影响
            stack.pop();
        }
        slow=slow.next;
        while(!stack.isEmpty()){
            if(slow.val!=stack.pop()){
                return false;
            }
            slow=slow.next;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {     //也可以自己反接前半段链表
        if(head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        ListNode pre = head, prepre = null;
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            pre.next = prepre;
            prepre = pre;
        }
        if(fast != null) {
            slow = slow.next;
        }
        while(pre != null && slow != null) {
            if(pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }


    public boolean isPalindrome3(ListNode head) {       //将链表的值复制都队列中然后前后依次比较
        List<Integer> vals = new ArrayList<Integer>();

        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    private ListNode frontPointer;                             //递归方法
    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public boolean isPalindrome4(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }






}
