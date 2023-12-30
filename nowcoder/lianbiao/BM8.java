package nowcoder.lianbiao;

import java.util.Stack;

//链表中倒数最后k个结点
public class BM8 {
    public ListNode FindKthToTail (ListNode pHead, int k) {  //快慢指针
        // write code here
        if(k==0||pHead==null){
            return null;
        }
        ListNode fast =pHead;
        ListNode slow = pHead;
        while(k>0){
            if(fast==null){     //k值有可能超过链表长度
                return null;
            }
            fast=fast.next;
            k--;
        }
        while(fast!=null){
            fast=fast.next;
            slow = slow.next;
        }
        return slow;
    }

    //栈   把原链表的结点全部压栈，然后再把栈中最上面的k个节点出栈，出栈的结点重新串成一个新的链表即可
    public ListNode FindKthToTail2 (ListNode pHead, int k) {
        // write code here
        if (pHead == null || k == 0){
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        //链表节点压栈
        while (pHead != null) {
            stack.push(pHead);
            pHead = pHead.next;
        }
        // 判断栈的元素是否小于k
        if (stack.size() < k){
            return null;
        }
        //在出栈串成新的链表
        ListNode firstNode = stack.pop();
        while (--k > 0) {
            // 将出栈的元素重新连接成为链表
            ListNode temp = stack.pop();  //注意，使用的是头插法
            temp.next = firstNode;
            firstNode = temp;
        }
        return firstNode;
    }
}
