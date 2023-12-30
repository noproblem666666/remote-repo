package Leetcode;

import java.util.Arrays;

//下一个排列（其实就是寻找下一个能使数变大的排列，但变大幅度最小）
public class hot15 {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        for (int i = len - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {          //从后向前寻找第一个升序对
                Arrays.sort(nums, i, len);        //将后面的降序对全部逆序排列
                for (int j = i; j <len; j++) {    //在后面的数中找到第一个比i下标大的数并交换
                    if (nums[j] > nums[i - 1]) {
                        int temp = nums[j];
                        nums[j] = nums[i - 1];
                        nums[i - 1] = temp;
                        return;
                    }
                }
            }
        }
        Arrays.sort(nums);     //如果之前都没找到，就说明是降序，直接排列成升序
        return;
    }
}
