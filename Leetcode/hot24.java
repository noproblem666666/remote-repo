package Leetcode;

import java.util.Arrays;

//最大子数组和（动态规划）
public class hot24 {
    public int maxSubArray(int[] nums) {      //动态规划
        int[] dp = new int[nums.length];     //代表以i为结尾的子数组的最大和（这样可以简化逻辑）
        System.arraycopy(nums, 0, dp, 0, dp.length);   //初始化dp
        for (int i = 1; i < dp.length; i++) {
            if(dp[i-1]>0){                  //只有前一项的子数组和大于零，才会加上
                dp[i]+=dp[i-1];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i : dp) {                 //返回最大值
            max = Math.max(max,i);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {     //空间优化
        int pre = 0;
        int res = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            res = Math.max(res, pre);
        }
        return res;
    }

    public int maxSubArray3(int[] nums) {       //分治法
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        return maxSubArraySum(nums, 0, len - 1);
    }

    private int maxCrossingSum(int[] nums, int left, int mid, int right) {
        // 一定会包含 nums[mid] 这个元素
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        // 左半边包含 nums[mid] 元素，最多可以到什么地方
        // 走到最边界，看看最值是什么
        // 计算以 mid 结尾的最大的子数组的和
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        // 右半边不包含 nums[mid] 元素，最多可以到什么地方
        // 计算以 mid+1 开始的最大的子数组的和
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }

    private int maxSubArraySum(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = left + (right - left) / 2;
        return max3(maxSubArraySum(nums, left, mid),
                maxSubArraySum(nums, mid + 1, right),
                maxCrossingSum(nums, left, mid, right));
    }

    private int max3(int num1, int num2, int num3) {
        return Math.max(num1, Math.max(num2, num3));
    }



}
