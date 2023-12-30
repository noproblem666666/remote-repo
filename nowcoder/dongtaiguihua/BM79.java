package nowcoder.dongtaiguihua;

import java.util.Arrays;

//打家劫舍（二）(考虑环形问题，收尾不能都要)
public class BM79 {
    //最简单的办法，把数组去掉头和去掉尾的情况都算一次，取最大值
    public int rob (int[] nums) {
        // write code here
        if(nums.length<=3){
            Arrays.sort(nums);
            return nums[nums.length-1];
        }

        int[] numsTemp = new int[nums.length-1];
        //快速复制数组
        System.arraycopy(nums,1,numsTemp,0,nums.length-1);

        int max1 = 0;
        nums[2] +=nums[0];
        //别遗漏了这次比较
        max1 = Math.max(max1,nums[2]);
        for (int i = 3; i < nums.length-1; i++) {
            //每一次决定从第前两家开始还是第前三家开始
            nums[i] +=Math.max(nums[i-2],nums[i-3]);
            max1 = Math.max(max1,nums[i]);
        }

        int max2 = 0;
        numsTemp[2] +=numsTemp[0];
        //别遗漏了这次比较
        max2 = Math.max(max2,numsTemp[2]);
        for (int i = 3; i < numsTemp.length; i++) {
            //每一次决定从第前两家开始还是第前三家开始
            numsTemp[i] +=Math.max(numsTemp[i-2],numsTemp[i-3]);
            max2 = Math.max(max2,numsTemp[i]);
        }
        return Math.max(max1,max2);
    }

    //答案写法。更加简洁
    public int rob2 (int[] nums) {
        //dp[i]表示长度为i的数组，最多能偷取多少钱
        int[] dp = new int[nums.length + 1];
        //选择偷了第一家
        dp[1] = nums[0];
        //最后一家不能偷
        for(int i = 2; i < nums.length; i++)
            //对于每家可以选择偷或者不偷
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        int res = dp[nums.length - 1];
        //清除dp数组，第二次循环
        Arrays.fill(dp, 0);
        //不偷第一家
        dp[1] = 0;
        //可以偷最后一家
        for(int i = 2; i <= nums.length; i++)
            //对于每家可以选择偷或者不偷
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        //选择最大值
        return Math.max(res, dp[nums.length]);
    }
}
