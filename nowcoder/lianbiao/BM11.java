package nowcoder.lianbiao;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//链表相加（二）
public class BM11 {
    public ListNode addInList (ListNode head1, ListNode head2) {     //直接计算，遇到太长的数，存储不了就会出错
        // write code here
        int sum1 = 0 ,sum2 = 0;
        ListNode start1 = head1 ,start2 =head2;
        while(start1!=null){
            sum1*=10;
            sum1+=start1.val;
            start1 = start1.next;
        }
        while(start2!=null){
            sum2*=10;
            sum2+=start2.val;
            start2 =start2.next;
        }
        int sum = sum1 +sum2;
        ListNode res = new ListNode(0); //虚拟头结点
        while(sum!=0){   //使用头插法，顺序一致
            ListNode node = new ListNode(sum%10);
            sum/=10;
            node.next = res.next;
            res.next = node;
        }
        return res.next;
    }

    public ListNode addInList2 (ListNode head1, ListNode head2) {    //使用动态数组进行存储，在从后往前相加
        // write code here
        int len1 = 0,len2=0;
        List<Integer> list1 = new ArrayList<>(),list2 = new ArrayList<>();
        while(head1!=null){
            list1.add(head1.val);
            len1++;
            head1=head1.next;
        }
        while(head2!=null){
            list2.add(head2.val);
            len2++;
            head2=head2.next;
        }
        int temp = 0;   //记录进位
        List<Integer> res = new ArrayList<>();
        int count = Math.min(len1,len2);
        int i =1;
        while(count>0){                       //每一位相加，考虑进位
            int sum = list1.get(list1.size()-i)+list2.get(list2.size()-i)+temp;
            if(sum>=10){
                res.add(sum-10);
                temp =1;
            }else{
                res.add(sum);
                temp =0;
            }
            i++;
            count--;
        }
        if(list1.size()-i>=0){                //处理多出来的位数，记得处理最后可能的多一位
            addLeft(list1, temp, res, i);
        }else{
            addLeft(list2, temp, res, i);
        }
        ListNode resList = new ListNode(0);  //虚拟头结点 ，头插法
        for (Integer re : res) {
            ListNode addNode = new ListNode(re);
            addNode.next = resList.next;
            resList.next = addNode;
        }
        return resList.next;
    }

    public void addLeft(List<Integer> list, int temp, List<Integer> res, int i) {
        while(list.size()-i>=0){
            int sum = list.get(list.size()-i) + temp;
            if(sum>=10){
                res.add(sum-10);
                temp = 1;
            }else{
                res.add(sum);
                temp = 0;
            }
            i++;
        }
        if(temp==1){                    //两数相加可能会导致最后增多一位
            res.add(1);
        }
    }

    public ListNode addInList3 (ListNode head1, ListNode head2) {     //反转链表，直接从后往前处理，便于对齐
        // 进行判空处理
        if(head1 == null)
            return head2;
        if(head2 == null){
            return head1;
        }
        // 反转h1链表
        head1 = reverse(head1);
        // 反转h2链表
        head2 = reverse(head2);
        // 创建新的链表头节点
        ListNode head = new ListNode(-1);
        ListNode nHead = head;
        // 记录进位的数值
        int tmp = 0;
        while(head1 != null || head2 != null){
            // val用来累加此时的数值（加数+加数+上一位的进位=当前总的数值）
            int val = tmp;
            // 当节点不为空的时候，则需要加上当前节点的值
            if (head1 != null) {
                val += head1.val;
                head1 = head1.next;
            }
            // 当节点不为空的时候，则需要加上当前节点的值
            if (head2 != null) {
                val += head2.val;
                head2 = head2.next;
            }
            // 求出进位
            tmp = val/10;
            // 进位后剩下的数值即为当前节点的数值
            nHead.next = new ListNode(val%10);
            // 下一个节点
            nHead = nHead.next;

        }
        // 最后当两条链表都加完的时候，进位不为0的时候，则需要再加上这个进位
        if(tmp > 0){
            nHead.next = new ListNode(tmp);
        }
        // 重新反转回来返回
        return reverse(head.next);
    }

    // 反转链表
    ListNode reverse(ListNode head){
        if(head == null)
            return head;
        ListNode cur = head;
        ListNode node = null;
        while(cur != null){
            ListNode tail = cur.next;
            cur.next = node;
            node = cur;
            cur = tail;
        }
        return node;
    }

    public ListNode addInList4 (ListNode head1, ListNode head2) {     //使用辅助栈的先进后出特性
        // write code here
        if(head1 == null)
            return head2;
        if(head2 == null){
            return head1;
        }
        // 使用两个辅助栈，利用栈先进后出，相当于反转了链表
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode p1=head1;
        ListNode p2=head2;
        // 将两个链表的结点入栈
        while(p1!=null){
            stack1.push(p1);
            p1=p1.next;
        }
        while(p2!=null){
            stack2.push(p2);
            p2=p2.next;
        }
        // 进位
        int tmp = 0;
        // 创建新的链表头节点
        ListNode head = new ListNode(-1);
        ListNode nHead = head.next;
        while(!stack1.isEmpty()||!stack2.isEmpty()){
            // val用来累加此时的数值（加数+加数+上一位的进位=当前总的数值）
            int val = tmp;
            // 栈1不为空的时候，弹出结点并累加值
            if (!stack1.isEmpty()) {
                val += stack1.pop().val;
            }
            // 栈2不为空的时候，弹出结点并累加值
            if (!stack2.isEmpty()) {
                val += stack2.pop().val;
            }
            // 求出进位
            tmp = val/10;
            // 进位后剩下的数值即为当前节点的数值
            ListNode node = new ListNode(val%10);
            // 将结点插在头部
            node.next = nHead;
            nHead = node;
        }
        if(tmp > 0){
            // 头插
            ListNode node = new ListNode(tmp);
            node.next = nHead;
            nHead = node;
        }
        return nHead;
    }
}
