package Leetcode;

import java.util.Arrays;

//目标和（动态规划和回溯）
public class hot92 {
    //可以画出状态转移表进行分析
    public static int findTargetSumWays(int[] nums, int s) {  //dp第一维指当前涉及到的数字，第二维指当前的总和
        int sum = 0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i];
        // 绝对值范围超过了sum的绝对值范围则无法得到
        if (Math.abs(s) > Math.abs(sum)) return 0;
        int len = nums.length;
        int range = sum * 2 + 1;//因为要包含负数所以要两倍，又要加上0这个中间的那个情况
        int[][] dp = new int[len][range];//这个数组是从总和为-sum开始的
        //加上sum纯粹是因为下标界限问题，赋第二维的值的时候都要加上sum
        // 初始化   第一个数只能分别组成+-nums[i]的一种情况
        dp[0][sum + nums[0]] += 1;
        dp[0][sum - nums[0]] += 1;
        for (int i = 1; i < len; i++) {
            for (int j = -sum; j <= sum; j++) {
                if((j+nums[i]) > sum) {//+不成立 加上当前数大于了sum   只能减去当前的数
                    dp[i][j+sum] = dp[i-1][j-nums[i]+sum]+0;
                }else if((j-nums[i]) < -sum) {//-不成立  减去当前数小于-sum   只能加上当前的数
                    dp[i][j+sum] = dp[i-1][j+nums[i]+sum]+0;
                }else {//+-都可以
                    dp[i][j+sum] = dp[i-1][j+nums[i]+sum]+dp[i-1][j-nums[i]+sum];
                }
            }
        }
        return dp[len - 1][sum + s];
    }



    int count = 0;
    public int findTargetSumWays2(int[] nums, int target) {   //回溯（时间复杂度较高）
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }

}
