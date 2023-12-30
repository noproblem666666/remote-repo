package Leetcode;

import java.util.Stack;

//每日温度（栈）
public class hot100 {
    public int[] dailyTemperatures(int[] T) {     //暴力解法（超出时间限制）
        int length = T.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            int current = T[i];
            if (current < 100) {
                for (int j = i + 1; j < length; j++) {
                    if (T[j] > current) {
                        result[i] = j - i;
                        break;
                    }
                }
            }
        }
        return result;
    }


    public int[] dailyTemperatures2(int[] T) {     //递减栈
        Stack<int[]> stack = new Stack<>();  //

        int[] res = new int[T.length];          //初始化数组默认值为0
        int[] Ts = new int[]{T[0], 0};
        stack.push(Ts);
        for (int i = 1; i < T.length; i++) {
            while (!stack.isEmpty() && stack.peek()[0] < T[i]) {       //只要遇到值小于自身的就不停填值，直到栈空或者值大于自身就入栈
                int[] temp = stack.pop();
                res[temp[1]] = i - temp[1];
            }

            stack.push(new int[]{T[i], i});
        }
        return res;
    }
}
