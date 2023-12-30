package Leetcode;

import java.util.ArrayList;
import java.util.List;

//括号生成
public class rehot13 {
    //递归回溯
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuffer str = new StringBuffer();
        generate(n, 0, 0, res, str);
        return res;
    }

    //每一层递归记录左括号和右括号数量
    public void generate(int n, int left, int right, List<String> res, StringBuffer str) {
        //左括号数量不能大于n
        if (left < n) {
            str.append("(");
            generate(n, left + 1, right, res, str);
            str.deleteCharAt(str.length() - 1);
        }
        //右括号数量不能大于左括号数量
        if (left > right) {
            str.append(")");
            generate(n, left, right + 1, res, str);
            str.deleteCharAt(str.length() - 1);
        }
        //数量够了就加入，因为是toString，不用考虑对str的操作影响集合里的数
        if (str.length() == 2 * n) {
            res.add(str.toString());
        }
    }
}
