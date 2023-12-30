package Leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;



//   合并k个链表，最优解是优先级队列和分而治之方法（归并排序）



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class hot14 {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0){        //必须先判断是否为空，再判断长度为0，否则可能会报空指针异常
            return null;
        }
        boolean flap =true;
        for (ListNode list : lists) {
            if (list != null) {
                flap = false;
                break;                              //节省循环次数
            }
        }
        if(flap){
            return null;
        }
        ListNode resNode = new ListNode();
        ListNode addNode = resNode;
        flap = true;
        while(flap){
            flap=false;
            int min=10000;
            int index = 0;
            for(int i = 0;i< lists.length;i++){
                if(lists[i]!=null){      //先判断是否为null
                    if(lists[i].val<min){
                        min = lists[i].val;
                        index=i;
                    }
                }
            }
            lists[index]=lists[index].next;    //之前已经判断过了，不会为null
            ListNode listNode = new ListNode(min);
            addNode.next=listNode;
            addNode=listNode;
            for (ListNode list : lists) {
                if (list != null) {
                    flap = true;
                    break;                              //节省循环次数
                }
            }
        }
        return resNode.next;   //起始结点没有被赋值

    }

    public ListNode mergeKLists2(ListNode[] lists) {      //使用优先级队列，重写comparator方法,速度快
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) return -1;
                else if (o1.val == o2.val) return 0;
                else return 1;
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        for (ListNode node : lists) {
            if (node != null) queue.add(node);
        }
        while (!queue.isEmpty()) {
            p.next = queue.poll();
            p = p.next;
            if (p.next != null) queue.add(p.next);
        }
        return dummy.next;      //返回时去掉头结点
    }


    public ListNode mergeKLists3(ListNode[] lists) {         //分治法（归并排序）
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {    //递归写法
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

    private ListNode mergeTwoLists2(ListNode l1, ListNode l2){   //迭代写法
        ListNode node = new ListNode(-1), h = node;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                h.next = l1;
                l1 = l1.next;
            }else{
                h.next = l2;
                l2 = l2.next;
            }
            h = h.next;
        }
        if(l1 != null){
            h.next = l1;
        }else if(l2 != null){
            h.next = l2;
        }
        return node.next;
    }

}
