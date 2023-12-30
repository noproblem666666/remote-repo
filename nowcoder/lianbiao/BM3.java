package nowcoder.lianbiao;

import java.util.List;
import java.util.Stack;

//链表中的节点每k个一组反转
public class BM3 {

    //空间复杂度不为o(1)
    public ListNode reverseKGroup(ListNode head, int k) {      //用栈来保存结点（注意断尾），然后分别用头插法和尾插法来保证正序或者逆序
        if (k <= 1 || head == null) {
            return head;
        }
        ListNode start = head;
        ListNode res = new ListNode(0);    //虚拟头结点
        ListNode temp = res;            //记录返回链表的插入点
        Stack<ListNode> stack = new Stack<>();
        while(start!=null){
            while (stack.size() < k && start != null) {
                stack.push(start);
                start = start.next;
            }
            if(stack.size()==k){
                while(!stack.isEmpty()){      //尾插法，逆序
                    ListNode pop = stack.pop();
                    pop.next=null;        //去掉尾巴
                    temp.next=pop;
                    temp=temp.next;
                }
            }else{
                while(!stack.isEmpty()){     //头插法，负负为正
                    ListNode pop = stack.pop();
                    pop.next = temp.next;
                    temp.next =pop;
                }
            }
        }
        return res.next;

    }

    public ListNode reverseKGroup2 (ListNode head, int k) {  //递归，空间复杂度不是o(1)，较难理解
        //找到每次翻转的尾部
        ListNode tail = head;
        //遍历k次到尾部
        for(int i = 0; i < k; i++){
            //如果不足k到了链表尾，直接返回，不翻转
            if(tail == null)
                return head;
            tail = tail.next;
        }
        //翻转时需要的前序和当前节点
        ListNode pre = null;
        ListNode cur = head;
        //在到达当前段尾节点前
        while(cur != tail){
            //翻转
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        //当前尾指向下一段要翻转的链表
        head.next = reverseKGroup2(tail, k);
        return pre;
    }

    public ListNode reverseKGroup3 (ListNode head, int k) {     //分段头插法，空间复杂度为o（1） （可以把翻转部分写成一个函数）
        if(head==null||head.next==null||k==1) return head;
        ListNode res = new ListNode(0);
        res.next = head;
        int length = 0;
        ListNode pre = res,
                cur = head,
                temp = null;
        while(head!=null){
            length++;
            head = head.next;
        }
        //分段使用头插法将链表反序
        for(int i=0; i<length/k; i++){      //这样可以在最后一段不足为k长度前停下来
            //pre作为每一小段链表的头节点，负责衔接
            for(int j=1; j<k; j++){
                temp = cur.next;
                cur.next = temp.next;
                //相当于头插法，注意：
                //temp.next = cur是错误的，temp需要连接的不是前一节点，而是子序列的头节点
                temp.next = pre.next;
                pre.next = temp;
            }
            //每个子序列反序完成后，pre，cur需要更新至下一子序列的头部
            pre = cur;
            cur = cur.next;
        }
        return res.next;
    }
}
