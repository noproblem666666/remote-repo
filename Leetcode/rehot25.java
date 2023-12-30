package Leetcode;

//跳跃游戏（动态规划）
public class rehot25 {
    //时间复杂度较差
    public static boolean canJump(int[] nums) {
        //记录能否到达i下标的点
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i]) {
                int temp = nums[i];
                while (temp > 0) {
                    //防止越界
                    if (i + temp < dp.length) {
                        dp[i + temp] = true;
                    }
                    temp--;
                }
            }
        }
        return dp[dp.length - 1];
    }

    //贪心算法，遍历数组，每次都计算可以到达的最远处，只要能够到达最远端就算成功
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            //如果i>rightmost,说明后面的都已经无法到达，没有继续计算的意义
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                //说明已经到达，直接停止计算
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
