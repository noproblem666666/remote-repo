package Leetcode;

import java.math.BigInteger;
 //用到了BigInteger来存储超长计算数字
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class hot02 {
    public static void main(String[] args) {
        ListNode listNode =new ListNode(9);
        ListNode listNode1 =new ListNode(9, listNode);
        ListNode listNode2 =new ListNode(9,listNode1);
        ListNode listNode3 =new ListNode(9,listNode2);
        ListNode listNode4 =new ListNode(9,listNode3);
        ListNode listNode5 =new ListNode(9,listNode4);
        ListNode listNode6 =new ListNode(9,listNode5);
        ListNode listNode7 =new ListNode(9,listNode6);
        ListNode listNode8 =new ListNode(9,listNode7);
        ListNode listNode9 =new ListNode(1,listNode8);
        ListNode listNode10 =new ListNode(9);
//        long calculator = calculator(listNode2);
//        System.out.println(calculator);
//        ListNode listNode3 = toListNode(1234);
        ListNode listNode11 = addTwoNumbers(listNode10, listNode9);
        while(listNode11!=null){
            System.out.println(listNode11.val);
            listNode11=listNode11.next;
        }


    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger bigInteger =new BigInteger("0");
            long sum = 0;

            bigInteger = calculator(l1).add(calculator(l2));
            return toListNode(bigInteger);
    }

    public static BigInteger calculator(ListNode l) {
        long sum = 0;
        StringBuilder str = new StringBuilder();
        while (l != null) {
            str.append(l.val);
            l=l.next;
        }
        str.reverse();
        BigInteger bigInteger =new BigInteger(str.toString());
        //sum=Long.parseLong(str.toString());
        return bigInteger;
    }
    public static ListNode toListNode(BigInteger sum){
        if(sum.equals(BigInteger.valueOf(0))){
            return new ListNode(0);
        }
        BigInteger i = sum.remainder(BigInteger.valueOf(10));
        ListNode listNode = new ListNode(i.intValue());
        ListNode index =listNode;
        sum=sum.divide(BigInteger.valueOf(10));
        while(!sum.equals(BigInteger.valueOf(0))){
            i=sum.remainder(BigInteger.valueOf(10));
            ListNode listNode1 =new ListNode(i.intValue());
            index.next =listNode1;
            index =listNode1;
            sum=sum.divide(BigInteger.valueOf(10));
        }
        return listNode;
    }
}