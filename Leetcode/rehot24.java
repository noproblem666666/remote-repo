package Leetcode;
//最大子数组和（动态规划）
public class rehot24 {
    public int maxSubArray(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }
        //以i为结尾位置的最大子数组和
        int[] dp = new int[nums.length];
        dp[0] =nums[0];
        //有可能有小于0的数字,先等于nums[0],防止最大值就是第一个数字
        int max = nums[0];
        for (int i = 1; i < dp.length; i++) {
            //每次状态转移，可选加或者不加前面的子数组和
            dp[i] = Math.max(nums[i],nums[i]+dp[i-1]);
            max=Math.max(max,dp[i]);
            //System.out.println(dp[i]);
        }
        return max;
    }

    //由于我们的状态转移数组每次只用到前一位，因此可以使用一个常量来优化空间
    public int maxSubArray2(int[] nums) {
        int pre = 0;
        int res = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            res = Math.max(res, pre);
        }
        return res;
    }



}
