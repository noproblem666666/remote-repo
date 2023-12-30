package Leetcode;

import java.util.ArrayList;
import java.util.List;
           //最多的不同路径
public class hot27 {
    StringBuilder stringBuilder = new StringBuilder();
    List<String> list = new ArrayList<>();
    int res;

    public int uniquePaths(int m, int n) {
        findPaths(m, n);
        return res;
    }

    public void findPaths(int m, int n) {       //回溯递归，时间复杂度太高
        if (m == 1 & n == 1) {
            if (!list.contains(String.valueOf(stringBuilder))) {
                list.add(String.valueOf(stringBuilder));
                res++;
            }
        } else if (m == 1) {
            n--;
            stringBuilder.append("n");    //不能拼接n，因为数字是变化的
            findPaths(m, n);
            //n++;这个操作没必要，因为回到上一层时数字没变
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        } else if (n == 1) {
            m--;
            stringBuilder.append("m");
            findPaths(m, n);
            //m++;
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        } else {
            m--;
            stringBuilder.append("m");
            findPaths(m, n);
            m++;
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            n--;
            stringBuilder.append("n");
            findPaths(m, n);
            //n++;
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }


    public int uniquePaths2(int m, int n) {           //排列组合  因为机器到底右下角，向下几步，向右几步都是固定的，我们只要向下n-1步，向右m-1步就一定能到达终点。
        //只跟第几行第几列有关，从m+n-2步中抽出m-1步
        long ans = 1;
        for (int i = 0; i < Math.min(m - 1, n - 1); i++) {    //C 和 A 的公式
            ans *= m + n - 2 - i;
            ans /= i + 1;
        }
        return (int) ans;
    }
    public int uniquePaths3(int m, int n) {   //动态规划，dp[i][j]是到达i，j的最多路径
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];   //起点是0，0，终点就是m-1，n-1
    }



}
