package nowcoder.lianbiao;

import java.util.*;

//单链表的排序
public class BM12 {
    public ListNode sortInList (ListNode head) {     //使用数组存储，使用java默认的排序方法
        // write code here
        if(head == null){
            return null;
        }
        List<Integer> list = new LinkedList<>();
        while(head!=null){
            list.add(head.val);
            head = head.next;
        }
        Collections.sort(list);   //默认的排序方法，时间复杂度为(nlog(n))
        ListNode res = new ListNode(0);
        ListNode start = res;
        for (Integer integer : list) {      //尾插法，保持原有顺序
            ListNode addNode = new ListNode(integer);
            start.next = addNode;
            start=addNode;
        }
        return res.next;
    }

    public ListNode sortInList2 (ListNode head) {    //归并排序（递归）分治法思想
        // write code here
        if (head == null || head.next == null)
            return head;
        // 使用快慢指针寻找链表的中点
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        // 递归左右两边进行排序
        ListNode left = sortInList2(head);
        ListNode right = sortInList2(tmp);
        // 创建新的链表
        ListNode h = new ListNode(0);
        ListNode res = h;
        // 合并 left right两个链表
        while (left != null && right != null) {
            // left  right链表循环对比
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        // 最后添加未对比的链表部分判断左链表是否为空
        h.next = left != null ? left : right;
        return res.next;
    }
}
