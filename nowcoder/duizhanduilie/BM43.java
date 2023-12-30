package nowcoder.duizhanduilie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

//包含min函数的栈
public class BM43 {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> stackMin = new Stack<>();    //Todo:用来存储每个栈顶元素时对应的栈中最小值元素
//    int Min = Integer.MAX_VALUE;       //暂存最小元素
    public void push(int node) {
        stack.push(node);
//        if(stackMin.isEmpty()||Min > node){    //Todo：注意栈可能在过程中将之前的最小值排出，而此时Min记录的任然是最小值，写法不对,
//                                                      除非在出栈时继续维护Min
//            Min = node;
//        }
        if(stackMin.isEmpty() || stackMin.peek() > node)
            stackMin.push(node);
        else
            //重复加入栈顶
            stackMin.push(stackMin.peek());
    }

    public void pop() {
        stack.pop();
        stackMin.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return stackMin.peek();
    }


    Stack<int[]> stack2 = new Stack<>();       //Todo:不使用辅助栈，也可以存储数组，记录当前值和最小值
    public void push2(int node) {
        if(!stack2.isEmpty()&&stack2.peek()[1]<node){
            stack2.push(new int[]{node,stack2.peek()[1]});
        }else{
            stack2.push(new int[]{node,node});
        }
    }

    public void pop2() {
        stack2.pop();
    }

    public int top2() {
        return stack2.peek()[0];
    }

    public int min2() {
        return stack2.peek()[1];
    }

}
