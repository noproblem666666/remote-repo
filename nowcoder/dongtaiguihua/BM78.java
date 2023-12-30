package nowcoder.dongtaiguihua;

import java.util.Arrays;

//打家劫舍（一）(动态规划，和爬楼梯原理一样)
public class BM78 {
    //动态规划，直接在原数组上操作
    public int rob (int[] nums) {
        // write code here
        if(nums.length<=2){
            Arrays.sort(nums);
            return nums[nums.length-1];
        }
        if(nums.length==3){
            return Math.max(nums[0]+nums[2],nums[1]);
        }
        int max = 0;
        nums[2] +=nums[0];
        //别遗漏了这次比较
        max = Math.max(max,nums[2]);
        for (int i = 3; i < nums.length; i++) {
            //每一次决定从第前两家开始还是第前三家开始
            nums[i] +=Math.max(nums[i-2],nums[i-3]);
            max = Math.max(max,nums[i]);
        }
        return max;
    }

    //答案写法，更加简洁
    public int rob2 (int[] nums) {
        //dp[i]表示长度为i的数组，最多能偷取多少钱
        int[] dp = new int[nums.length + 1];
        //长度为1只能偷第一家
        dp[1] = nums[0];
        for(int i = 2; i <= nums.length; i++)
            //对于每家可以选择偷或者不偷
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        return dp[nums.length];
    }
}
