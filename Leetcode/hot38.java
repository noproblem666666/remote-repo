package Leetcode;

//不同的二叉搜索树
public class hot38 {     //使用了动态规划和卡特兰数
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;      //1*任何数都是本身
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++)
            for (int j = 1; j < i + 1; j++)
                dp[i] += dp[j - 1] * dp[i - j];

        return dp[n];
    }

}
