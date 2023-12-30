package Leetcode;

import java.util.*;

//最长有效括号
public class hot16 {
    public int longestValidParentheses(String s) {     //自己写的栈，难以考虑复杂情况
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]=='('){
                stack.push('(');
            }else{
                if(stack.isEmpty()||stack.peek()==')'){
                    list1.add(count);
                    count = 0;
                }else{
                    count+=2;
                    stack.pop();

                }
            }
        }
        list1.add(count);
        count=0;
        for (int i = chars.length-1; i >= 0; i--) {
            if(chars[i]==')'){
                stack.push(')');
            }else{
                if(stack.isEmpty()||stack.peek()=='('){
                    list2.add(count);
                    count = 0;
                }else{
                    count+=2;
                    stack.pop();

                }
            }
        }
        list2.add(count);

        int max1=0,max2 = 0;
        for (int i = 0; i < list1.size(); i++) {
            max1=Math.max(max1, list1.get(i));
        }
        for (int i = 0; i < list2.size(); i++) {
            max2=Math.max(max2, list2.get(i));
        }
        System.out.println(max1+"   "+max2);
        return Math.min(max1,max2);
    }

    public int longestValidParentheses2(String s) {    //动态规划
        int maxans = 0;
        int[] dp = new int[s.length()];          //表示以i下标为结尾的括号最长有效长度是多少
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {                    //状态转移方程
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    public int longestValidParentheses3(String s) {    //答案的栈，长度直接用下标差值计算
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    public int longestValidParentheses4(String s) {     //计数左右括号，不需要额外空间，但是需要正反都遍历一次
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }



}
