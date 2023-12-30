package Leetcode;

//相交链表
public class hot56 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {     //这道题也可以用哈希表来判断是否有重复的结点，但空间复杂度不为o(1)
        if(headA==null||headB==null){
            return null;
        }
        int lengthA = 0;              //统计两个链表的长度差
        int lengthB = 0;
        ListNode countA = headA;
        ListNode countB = headB;
        while (countA != null) {
            lengthA++;
            countA = countA.next;
        }
        while (countB != null) {
            lengthB++;
            countB = countB.next;
        }
        countA=headA;
        countB=headB;
        if(lengthA<lengthB){           //让长的链表先走长度差的步数
            int i =lengthB-lengthA;
            while(i>0){
                countB=countB.next;
                i--;
            }
        }else{
            int i= lengthA-lengthB;
            while(i>0){
                countA=countA.next;
                i--;
            }
        }
        while(countA!=null&&countB!=null){     //如果能相遇则必定是在相交处
            if(countA==countB){
                return countA;
            }
            countA=countA.next;
            countB=countB.next;
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {     //更加简洁的一种写法
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;         //他们相互转换后则正好长的链表多走了长度查的步数，如果没有相交则在链表尾的null处相遇
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }


}
