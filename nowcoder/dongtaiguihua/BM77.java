package nowcoder.dongtaiguihua;

import java.util.Arrays;
import java.util.Stack;

//最长的括号字串(动态规划)
public class BM77 {
    //栈加动态规划
    public int longestValidParentheses(String s) {
        // write code here
        if (s == null || s.equals("")) {
            return 0;
        }
        int[] dp = new int[s.length()];
        Stack<Integer> stack = new Stack<>();//存储左括号的下标（因为存储的都是左括号）
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    dp[i] = 0;
                } else {
                    //出栈的时候就根据左括号下标计算出合法括号长度（中间肯定都是合法的，否则没法出栈）
                    dp[i] = i - stack.pop() + 1;
                }
            }
        }
        //System.out.println(Arrays.toString(dp));
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            //将中间合法的括号长度进行连接
            if (dp[i] != 0 && i - dp[i] >= 0 && dp[i - dp[i]] != 0) {
                dp[i] += dp[i - dp[i]];
            }
            max = Math.max(max, dp[i]);
        }
        //返回最大长度
        return max;
    }

    //仅使用栈
    public int longestValidParentheses2 (String s) {
        int res = 0;
        //记录上一次连续括号结束的位置
        int start = -1;
        Stack<Integer> st = new Stack<Integer>();
        for(int i = 0; i < s.length(); i++){
            //左括号入栈
            if(s.charAt(i) == '(')
                st.push(i);
                //右括号
            else{
                //如果右括号时栈为空，不合法，设置为结束位置
                if(st.isEmpty())
                    start = i;
                else{
                    //弹出左括号
                    st.pop();
                    //栈中还有左括号，说明右括号不够，减去栈顶位置就是长度
                    if(!st.empty())
                        res = Math.max(res, i - st.peek());
                        //栈中没有括号，说明左右括号行号，减去上一次结束的位置就是长度
                    else
                        res = Math.max(res, i - start);
                }
            }
        }
        return res;
    }

    //仅使用动态规划
    public int longestValidParentheses3 (String s) {
        int res = 0;
        //长度为0的串或者空串，返回0
        if(s.length() == 0 || s == null)
            return res;
        //dp[i]表示以下标为i的字符为结束点的最长合法括号长度
        int[] dp = new int[s.length()];
        //第一位不管是左括号还是右括号都是0，因此不用管
        for(int i = 1; i < s.length(); i++){
            //取到左括号记为0，有右括号才合法
            if(s.charAt(i) == ')'){
                //如果该右括号前一位就是左括号
                if(s.charAt(i - 1) == '(')
                    //计数+
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                //找到这一段连续合法括号序列前第一个左括号做匹配
                else if(i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(')
                    dp[i] = (i - dp[i - 1] > 1 ? dp[i - dp[i - 1] - 2] : 0) + dp[i - 1] + 2;
            }
            //维护最大值
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
