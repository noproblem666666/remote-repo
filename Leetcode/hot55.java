package Leetcode;

import java.util.Stack;

//最小栈（实现一个栈，可以用常数时间得到栈中的最小值（不能用变量存储，要考虑栈的进出））
public class hot55 {
    class MinStack {        //设计两个栈，一个栈负责正常存取，另一个栈负责存储最小值（由于栈进出的特性，所以另一个栈的最小值总是对应正确的）
        Stack<Integer> stack1;      //正常存取
        Stack<Integer> stack2;      //存取最小值
        public MinStack() {
            stack1= new Stack<>();
            stack2= new Stack<>();
        }

        public void push(int val) {
            if(stack2.isEmpty()||stack2.peek()>=val){    //存储最小值，一定要是>=因为可能会有相同的最小值，弹出后使得第二个栈少一个对象
                stack2.push(val);
            }
            stack1.push(val);

        }

        public void pop() {
            int temp = stack1.pop();
            if(temp==stack2.peek()){          //弹出最小值
                stack2.pop();
            }
        }

        public int top() {
            return stack1.peek();
        }

        public int getMin() {
            return stack2.peek();
        }
    }

    class MinStack2 {        //只用一个栈，但是要用栈额外存储下一个的最小值
        Stack<Integer> stack;      //除了正常存取还要存取下一位的最小值
        int min = Integer.MAX_VALUE; //记录当前的最小值
        public MinStack2() {
            stack= new Stack<>();

        }

        public void push(int val) {
            if(val<=min){           //别忘了加等号
                stack.push(min);    //用来记忆下一位最小值是多少
                min = val;
            }
            stack.push(val);

        }

        public void pop() {
            int temp = stack.pop();
            if(temp == min){
                min = stack.pop();   //取出存储的下一位最小值
            }

        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    //还可以有第三种方法，将方法二栈中的最小值改为最小值的差值，直接计算即可得到当前最小值，更加节省空间

    //第四种方法，设计一种链表，在链表头存取，但是除了节点中除了值，还要加入当前节点对应的最小值这一属性

}
