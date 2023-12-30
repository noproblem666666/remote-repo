package Leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//删除无效的括号（需要多看看！！！）
public class hot77 {                       //搜索加剪枝   时间复杂度o(n*(2^n))    空间复杂度o(n)
    Set<String> set = new HashSet<>();
    // n：字符串长度；max：最大括号数（单括号）；maxPathLen：记录「爆搜」过程中的最大路径子串的长度
    int n, max, maxPathLen;
    String s;

    public List<String> removeInvalidParentheses(String _s) {
        s = _s;
        n = s.length();
        int left = 0, right = 0;

        // 统计多余的括号数量
        for (char c : s.toCharArray()) {
            if (c == '(') left++;
            else if (c == ')') {
                if (left != 0) left--;
                else right++;
            }
        }
        maxPathLen = n - left - right;      // 提前更新 maxPathLen

        // 统计左右括号数量
        int left2 = 0, right2 = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') left2++;
            else if (c == ')') right2++;
        }

        max = Math.min(left2, right2);
        dfs(0, "", left, right, 0);
        return new ArrayList<>(set);    // 将Set集合转为List返回
    }

    /**
     * 遍历 _s 字符串，记录有效路径
     * @param curCharIndex 当前遍历的字符下标
     * @param path 遍历时的路径（括号组合字符串）
     * @param left 多余的左括号数量
     * @param right 多余的右括号数量
     * @param score 分数，用于标记左右括号的得分
     */
    private void dfs(int curCharIndex, String path, int left, int right, int score) {
        // 剪枝：合法路径的得分范围为 0 <= score <= max；多余的括号数量为负数，说明删多了，不符合
        if (left < 0 || right < 0 || score < 0 || score > max) return;

        if (left == 0 && right == 0) {
            // 多余的括号为0，且当前路径长度等于最大路径子串的长度，则符合
            if (path.length() == maxPathLen) {
                set.add(path);
            }
        }

        if (curCharIndex == n) return;      // 搜索完毕，退出（放在此处是为了记录完最后一个字符）

        char c = s.charAt(curCharIndex);     // 获取当前字符

        // 每一种选择都对应 添加/不添加
        if (c == '(') {         // 添加左括号，score + 1；不添加score不变，多余的左括号数量-1
            dfs(curCharIndex + 1, path + c, left, right, score+ 1);
            dfs(curCharIndex + 1, path, left - 1, right, score);
        } else if (c == ')') {      // 添加右括号，score - 1；不添加score不变，多余的右括号数量-1
            dfs(curCharIndex + 1, path + c, left, right, score - 1);
            dfs(curCharIndex + 1, path, left, right - 1, score);
        } else {        // 普通字符，score不变
            dfs(curCharIndex + 1, path + c, left, right, score);
        }
    }




}
