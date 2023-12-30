package Leetcode;

import java.util.Arrays;
//编辑距离（动态规划）
public class rehot30 {
    //写的还是不正确
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int i =1;
        int j =1;
        while(i<m&&j<n){
                if(chars1[i-1] ==chars2[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                    i++;
                    j++;
                }else if(chars1[i] == chars2[j-1]){
                    dp[i][j] = dp[i][j-1]+1;
                    i++;
                }else if(chars1[i-1] == chars2[j]){
                    dp[i][j] = dp[i-1][j]+1;
                    j++;
                }else{
                    dp[i][j] = dp[i-1][j-1] +1;
                    i++;
                    j++;
                }
                System.out.println(i+"  "+j);
            }
        for (int i1 = 0; j < m+1; j++) {
            for (int i2 = 0; i2 < n+1; i2++) {
                System.out.println(dp[i1][i2]);
            }
        }
        return dp[m][n];
    }

    //自底向上
    public int minDistance2(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        //初始化处理
        // 第一行
        for (int j = 1; j <= n2; j++) dp[0][j] = dp[0][j - 1] + 1;
        // 第一列
        for (int i = 1; i <= n1; i++) dp[i][0] = dp[i - 1][0] + 1;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                //重点记忆状态转移公式
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                //dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。
                else dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
            }
        }
        return dp[n1][n2];
    }


}
