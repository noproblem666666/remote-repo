package nowcoder.duizhanduilie;

import java.util.Stack;

//有效括号序列
public class BM44 {
    public boolean isValid(String s) {
        // write code here
        if (s == null || s.equals("")) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '{' || aChar == '(' || aChar == '[') {
                stack.push(aChar);
            } else {
                //查询ascii码表
                if (stack.isEmpty() || (stack.peek() + 1 != aChar && stack.peek() + 2 != aChar)) {  //不符合规则时的条件（后面是并不是或）
                    return false;
                }
                stack.pop();   //匹配完成后将括号输出
            }
        }
        return stack.isEmpty();  //只剩左半边括号的情况
    }

    public boolean isValid2 (String s) {     //比较巧妙的办法，遇到左括号放对应的右括号，这样右括号就可以直接比较是否相等
        //辅助栈
        Stack<Character> st = new Stack<Character>();
        //遍历字符串
        for(int i = 0; i < s.length(); i++){
            //遇到左小括号
            if(s.charAt(i) == '(')
                //期待遇到右小括号
                st.push(')');
                //遇到左中括号
            else if(s.charAt(i) == '[')
                //期待遇到右中括号
                st.push(']');
                //遇到左打括号
            else if(s.charAt(i) == '{')
                //期待遇到右打括号
                st.push('}');
                //必须有左括号的情况下才能遇到右括号
            else if(st.isEmpty() || st.pop() != s.charAt(i))
                return false;
        }
        //栈中是否还有元素
        return st.isEmpty();
    }
}
