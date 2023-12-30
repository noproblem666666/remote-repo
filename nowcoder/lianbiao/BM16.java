package nowcoder.lianbiao;

import java.util.HashMap;
import java.util.Map;

//删除有序链表中重复的元素（二）
public class BM16 {


    public ListNode deleteDuplicates (ListNode head) {    //需要考虑的细节较为复杂
        // write code here
        if(head == null||head.next==null){
            return head;
        }
        ListNode res = new ListNode(0);  //虚拟头结点，便于处理头结点和返回结果
        res.next = head;
        ListNode pre = res;
        ListNode start = head;
        while(start!=null && start.next != null){
            int index = 0;
            while(start.next!=null && start.next.val!= start.val){
                start=start.next;
                pre = pre.next;
            }
            while(start.next!=null && start.next.val==start.val){
                start = start.next;
                index = 1;

            }
            if(index == 1){
                pre.next = start.next;      //因为是要把重复的结点完全删去
                start = start.next;
            }

        }
        return res.next;      //注意不是返回head
    }

    public ListNode deleteDuplicates2 (ListNode head) {      //答案写法
        //空链表
        if(head == null)
            return null;
        ListNode res = new ListNode(0);
        //在链表前加一个表头
        res.next = head;
        ListNode cur = res;
        while(cur.next != null && cur.next.next != null){
            //遇到相邻两个节点值相同
            if(cur.next.val == cur.next.next.val){
                int temp = cur.next.val;
                //将所有相同的都跳过
                while (cur.next != null && cur.next.val == temp)
                    cur.next = cur.next.next;
            }
            else
                cur = cur.next;
        }
        //返回时去掉表头
        return res.next;
    }

    public ListNode deleteDuplicates3 (ListNode head) {    //哈希表统计次数，有序无序都可以，需要额外空间
        //空链表
        if(head == null)
            return null;
        Map<Integer,Integer> mp = new HashMap<>();
        ListNode cur = head;
        //遍历链表统计每个节点值出现的次数
        while(cur != null){
            if(mp.containsKey(cur.val))
                mp.put(cur.val, (int)mp.get(cur.val) + 1);
            else
                mp.put(cur.val,1);
            cur = cur.next;
        }
        ListNode res = new ListNode(0);
        //在链表前加一个表头
        res.next = head;
        cur = res;
        //再次遍历链表
        while(cur.next != null){
            //如果节点值计数不为1
            if(mp.get(cur.next.val) != 1)
                //删去该节点
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
        //去掉表头
        return res.next;
    }

    public ListNode deleteDuplicates4(ListNode head) {   //递归
        if(head == null){
            return null;
        }
        if(head.next != null && head.val == head.next.val){//发现有重复值
            while(head.next != null && head.val == head.next.val){
                head = head.next;//删除
            }
            return deleteDuplicates(head.next);//把与删除的那个结点相同的结点也进行删除
        }
        head.next = deleteDuplicates(head.next);//当没有发现重复值的情况，就一直进行递归操作
        return head;
    }
}
