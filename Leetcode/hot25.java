package Leetcode;
//跳跃游戏
public class hot25 {
    public boolean canJump(int[] nums) {             //自己写的动态规划（时间复杂度较差）
        boolean[] bp = new boolean[nums.length];     //表示当前下标能否到达，默认值为false
        bp[0] = true;    //第一个出发点肯定能到达
        for (int i = 0; i < nums.length; i++) {
            if(bp[i]){
                for (int j = nums[i]; j > 0; j--) {    //双重循环
                    if(i+j<nums.length){
                        bp[i+j] = true;
                    }
                }
            }
        }
        return bp[nums.length-1];   //返回最后一位看是否能到达
    }

    public static boolean canJump2(int[] nums) {
        if (nums == null) {
            return false;
        }
        //前n-1个元素能够跳到的最远距离
        int k = 0;
        //只需记录一个最远距离即可，减少空间复杂度和时间复杂度
        for (int i = 0; i <= k; i++) {         //注意判断条件是k，当目前下标无法到达时，就说明没有更大的跳跃距离了
            //第i个元素能够跳到的最远距离
            int temp = i + nums[i];
            //更新最远距离
            k = Math.max(k, temp);
            //如果最远距离已经大于或等于最后一个元素的下标,则说明能跳过去,退出. 减少循环
            if (k >= nums.length - 1) {
                return true;
            }
        }
        //最远距离k不再改变,且没有到末尾元素
        return false;
    }
}
