package Leetcode;

import java.util.HashSet;
import java.util.Set;

//环形列表
public class hot50 {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode nodeOne = head;          //两个指针，一个每次走一步，一个每次走两步
        ListNode nodeTwo = head;
        while (true) {
            nodeOne = nodeOne.next;
            nodeTwo = nodeTwo.next;
            if (nodeTwo != null) {      //只要有指针走到结尾就说明没有环
                nodeTwo = nodeTwo.next;
            } else {
                return false;
            }
            if (nodeOne == null || nodeTwo == null) {
                return false;
            }
            if (nodeOne == nodeTwo || nodeTwo.next == nodeOne) {     //如果走两步的指针追到了走一步的指针，就说明有环
                return true;
            }     //如果要计算环的长度，就要计算两个指针相遇时走的步数之差
        }
    }

    public boolean hasCycle2(ListNode head) {    //用哈希表判断结点是否被访问过
        Set<ListNode> seen = new HashSet<ListNode>();
        while (head != null) {
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }


}
