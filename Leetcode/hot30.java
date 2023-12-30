package Leetcode;

//变化单词的最小操作步骤    需要再做一遍
//依然是动态规划，有自顶向下和自底向上
public class hot30 {
    public int minDistance(String word1, String word2) {   //自底向上
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];       //dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数
        // 第一行
        for (int j = 1; j <= n2; j++) dp[0][j] = dp[0][j - 1] + 1;//第一行，是 word1 为空变成 word2 最少步数，就是插入操作
        // 第一列
        for (int i = 1; i <= n1; i++) dp[i][0] = dp[i - 1][0] + 1;//第一列，是 word2 为空，需要的最少步数，就是删除操作

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                //dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。
                /*//dp[i-1][j] +1删除操作
                表示0~i-1的word1通过某些操作可以得到0~j的word2，那么删除word1的i位置字符串再进行dp[i-1][j]操作即可
                //dp[i][j-1] +1插入操作
                表示0~i的word1通过某些操作可以得到0~j-1的word2，那么直接在dp[i][j-1] 操作后【在word1中直接插入word2的j位置字符】
                //dp[i-1][j-1]
                表示0~i-1的word1已经可以得到0~j-1的word2，此时分为i，j字符相等和不等两种情况，不等则需要经过一次替换
                * */
            }
        }
        return dp[n1][n2];
    }

}
