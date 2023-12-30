package nowcoder.duizhanduilie;

import java.util.Stack;

//用两个栈实现队列
public class BM42 {
    Stack<Integer> stack1 = new Stack<Integer>();     //暂存元素
    Stack<Integer> stack2 = new Stack<Integer>();     //需要出队时，如果这个栈为空，先把元素倒到这个栈再弹出

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
