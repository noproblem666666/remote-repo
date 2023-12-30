package nowcoder.duizhanduilie;

import java.util.*;

//表达式求值
//Todo:这道题用单栈很难解决，应该还要考虑乘号的优先运算，括号内的运算情况也很复杂，应该用双栈，或者栈加递归
public class BM49 {
    public int solve(String s) {   //解法不对
        // write code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '(') {

            } else if (aChar == ')') {
                int b = stack.pop();
                char c = stack.pop();
                int a = stack.pop();
                if (c == '+') {
                    stack.push((char) (a + b));
                } else if (c == '-') {
                    stack.push((char) (a - b));
                } else {
                    stack.push((char) (a * b));
                }
            } else {
                stack.push(aChar);
            }
        }
        return stack.pop();
    }


    //双栈解法
    Map<Character, Integer> map = new HashMap<Character, Integer>(){{
        put('-', 1);
        put('+', 1);
        put('*', 2);
    }};

    public int solve2(String s) {
        // 将所有的空格去掉
        s = s.replaceAll(" ", "");

        char[] cs = s.toCharArray();
        int n = s.length();

        // 存放所有的数字
        Deque<Integer> nums = new ArrayDeque<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.addLast(0);
        // 存放所有「非数字以外」的操作
        Deque<Character> ops = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    if (ops.peekLast() != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNumber(c)) {
                    int u = 0;
                    int j = i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j < n && isNumber(cs[j])) u = u * 10 + (cs[j++] - '0');
                    nums.addLast(u);
                    i = j - 1;   //最后需要回退一位
                } else {
                    //防止是负数
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        nums.addLast(0);
                    }
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        char prev = ops.peekLast();
                        if (map.get(prev) >= map.get(c)) {
                            calc(nums, ops);
                        } else {
                            break;
                        }
                    }
                    ops.addLast(c);
                }
            }
        }
        // 将剩余的计算完
        while (!ops.isEmpty() && ops.peekLast() != '(') calc(nums, ops);
        return nums.peekLast();
    }
    // 计算逻辑：从 nums 中取出两个操作数，从 ops 中取出运算符，然后根据运算符进行计算即可
    void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        int ans = 0;
        if (op == '+') ans = a + b;
        else if (op == '-') ans = a - b;
        else if (op == '*') ans = a * b;
        nums.addLast(ans);
    }
    boolean isNumber(char c) {
        return Character.isDigit(c);
    }


    //栈加递归，用递归处理括号
    public ArrayList<Integer> function(String s, int index){
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char op = '+';
        int i;
        for(i = index; i < s.length(); i++){
            //数字转换成int数字
            //判断是否为数字
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                num = num * 10 + s.charAt(i) - '0';
                if(i != s.length() - 1)
                    continue;
            }
            //碰到'('时，把整个括号内的当成一个数字处理
            if(s.charAt(i) == '('){
                //递归处理括号
                ArrayList<Integer> res = function(s, i + 1);
                num = res.get(0);
                i = res.get(1);
                if(i != s.length() - 1)
                    continue;
            }
            switch(op){
                //加减号先入栈
                case '+':
                    stack.push(num);
                    break;
                case '-':
                    //相反数
                    stack.push(-num);
                    break;
                //优先计算乘号
                case '*':
                    int temp = stack.pop();
                    stack.push(temp * num);
                    break;
            }
            num = 0;
            //右括号结束递归
            if(s.charAt(i) == ')')
                break;
            else
                op = s.charAt(i);
        }
        int sum = 0;
        //栈中元素相加
        while(!stack.isEmpty())
            sum += stack.pop();
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(sum);
        temp.add(i);
        return temp;
    }
    public int solve3 (String s) {
        ArrayList<Integer> res = function(s, 0);
        return res.get(0);
    }
}
