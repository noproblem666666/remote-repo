package Leetcode;

import java.util.Stack;

public class hot11 {
    Stack<Character> stack = new Stack<>();

    public static void main(String[] args) {

    }

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int index = 0;
        while (index < s.length()) {
            if (s.charAt(index) == '(' || s.charAt(index) == '{' || s.charAt(index) == '[') {
                stack.push(s.charAt(index));
            }
            if (s.charAt(index) == ')') {
                if(stack.isEmpty()){               //每次弹出前要检查是否为空，避免报错
                    return false;
                }
                char m = stack.pop();
                if (m != '(') {
                    return false;
                }
            }
            if (s.charAt(index) == '}') {
                if(stack.isEmpty()){
                    return false;
                }
                char m = stack.pop();
                if (m != '{') {
                    return false;
                }
            }
            if (s.charAt(index) == ']') {
                if(stack.isEmpty()){
                    return false;
                }
                char m = stack.pop();
                if (m != '[') {
                    return false;
                }
            }
            index++;
        }
        return stack.isEmpty();          //如果还有残余括号则匹配失败


    }
}
