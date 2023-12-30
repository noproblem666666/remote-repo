package nowcoder.diguihuisu;

import java.util.ArrayList;
import java.util.Stack;

//括号生成
public class BM60 {
    ArrayList<String> res = new ArrayList<>();       //递归回溯，用栈来匹配括号
    public ArrayList<String> generateParenthesis (int n) {
        // write code here
        if(n==0){
            return new ArrayList<>();
        }
        Stack<Character> stack = new Stack<>();
        stack.push('(');
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        generate(n,1,stack,stringBuilder);
        return res;

    }
    //每一步只有两个选择，新增左括号或者匹配右括号
    //m表示目前已经新增了多少个左括号，配合栈可以直到下一步可以进行的操作
    public void generate(int n , int m, Stack<Character> stack,StringBuilder stringBuilder){
        if(n==m && stack.isEmpty()){
            res.add(stringBuilder.toString());
            return;
        }
        if(stack.isEmpty()){
            stringBuilder.append('(');
            stack.push('(');
            generate(n,m+1,stack,stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stack.pop();
        }else{
            if(m<n){   //这时可以有两种选择
                stringBuilder.append('(');
                stack.push('(');
                generate(n,m+1,stack,stringBuilder);
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
                stack.pop();

            }
            stringBuilder.append(')');
            stack.pop();
            generate(n,m,stack,stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stack.push('(');
        }
    }


    //记录左右括号当前数量的办法来知道下一步可以进行的操作
    //直接用string记录，不用回溯之前的操作
    public void recursion(int left, int right, String temp, ArrayList<String> res, int n){
        //左右括号都用完了，就加入结果
        if(left == n && right == n){
            res.add(temp);
            return;
        }
        //使用一次左括号
        if(left < n){
            recursion(left + 1, right, temp + "(", res, n);
        }
        //使用右括号个数必须少于左括号
        if(right < n && left > right){
            recursion(left, right + 1, temp + ")", res, n);
        }
    }
    public ArrayList<String> generateParenthesis2 (int n) {
        //记录结果
        ArrayList<String> res = new ArrayList<String>();
        //递归
        recursion(0, 0, "", res, n);
        return res;
    }
}
