package Leetcode;

//爬楼梯(动态规划，斐波那契数列)
public class rehot29 {
    //如果用递归会超时
    public int climbStairs(int n) {
        //表示到达第n个台阶的方法数
        int[] dp = new int[n + 1];
        //从第0个台阶开始爬，初始值也要为1
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
