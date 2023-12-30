package Leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//最长有效括号
public class rehot16 {
    //栈不要存储括号，括号是已知信息，存储下标得到的信息更多
    public int longestValidParentheses(String s) {
        Deque<Integer> deque = new LinkedList<>();
        char[] chars = s.toCharArray();
        int[] index = new int[s.length()];
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]=='('){
                deque.push(i);
            }else{
                if(deque.isEmpty()){
                    index[i] = 0;
                }else{
                    //记得括号长度加一
                    index[i] = i-deque.pop()+1;
                }
            }
        }
        //System.out.println(Arrays.toString(index));
        //拼接能拼接的所有括号，找到最大值
        int max= 0;
        for (int i = 0; i < index.length; i++) {
            if(i-index[i]>=0 && index[i-index[i]]!=0){
                index[i]+= index[i-index[i]];
            }
            //max不要放在if里，考虑情况不全
            max = Math.max(max,index[i]);
        }
        return max;
    }

    //动态规划
    public int longestValidParentheses2(String s) {
        int maxans = 0;
        //表示以当前位置为结尾，组成的最长有效括号
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
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

}
