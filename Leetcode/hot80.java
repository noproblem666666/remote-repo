package Leetcode;

import java.util.Arrays;

//零钱兑换（动态规划）
public class hot80 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];     //dp[i]表示组成总额i所需的最少硬币数量
        Arrays.fill(dp,amount+1);     //先将数组全部初始化为最大值再一轮轮减少
        dp[0] = 0;   //组成总金额为0的话，最少硬币数量肯定为0
        for(int i = 1;i < dp.length;i++){
            for(int j = 0;j<coins.length;j++){
                if(i-coins[j]<0){
                    continue;
                }else{
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);   //状态转移方程，更大的金额肯定是由小金额多加一枚硬币组成的
                }
            }
        }
        if(dp[amount]==amount+1){     //如果最后一个数没有被更新，说明无法凑出需要的金额，返回-1
            return -1;
        }
        return dp[amount];     //最后计算出的数组最后一位就是答案
    }


    int[] memo;
    public int coinChange2(int[] coins, int amount) {    //使用了记忆化搜索的递归（直接使用递归会超时）
        if(coins.length == 0){
            return -1;
        }
        memo = new int[amount];

        return findWay(coins,amount);
    }
    // memo[n] 表示钱币n可以被换取的最少的硬币数，不能换取就为-1
    // findWay函数的目的是为了找到 amount数量的零钱可以兑换的最少硬币数量，返回其值int
    public int findWay(int[] coins,int amount){
        if(amount < 0){
            return -1;
        }
        if(amount == 0){
            return 0;
        }
        // 记忆化的处理，memo[n]用赋予了值，就不用继续下面的循环
        // 直接的返回memo[n] 的最优值
        if(memo[amount-1] != 0){
            return memo[amount-1];
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0;i < coins.length;i++){
            int res = findWay(coins,amount-coins[i]);
            if(res >= 0 && res < min){
                min = res + 1; // 加1，是为了加上得到res结果的那个步骤中，兑换的一个硬币
            }
        }
        memo[amount-1] = (min == Integer.MAX_VALUE ? -1 : min);
        return memo[amount-1];
    }


}
