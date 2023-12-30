package Leetcode;

import java.util.Arrays;

//寻找重复数（不能更改原始数组数据，只能用o(1)的额外空间）  不能用异或，因为重复的数不是奇数个，其他数也不是偶数个
public class hot74 {
    //不能解题，因为一个数出现的次数是不确定的
    public int findDuplicate(int[] nums) {    //已知1到n中只有一个重复的，1到n的总和是可以计算的，用数组的总和减去1到n的总和就求出了重复的那个数
        if (nums[0] == nums[1]) {    //如果只有一个重复且所有数组都是这一个数就算例外情况，直接返回数组头
            return nums[0];
        }
        int sum = Arrays.stream(nums).sum();    //计算数组的总和（使用流来计算）
        int sumN = ((nums.length - 1) + 1) * (nums.length - 1) / 2;   //1到n的总和(注意nums.length是n+1)
        return sum - sumN;
    }

    public int findDuplicate2(int[] nums) {    //时间复杂度太高
        for (int i = 0; i < nums.length; i++) {
            for (int i1 = 0; i1 < nums.length; i1++) {
                if (i != i1 && nums[i] == nums[i1]) {
                    return nums[i];
                }
            }
        }
        return nums[0];
    }

    public int findDuplicate3(int[] nums) {     //借鉴了二分查找的思想  时间复杂度为o(nlog(n))
        int len = nums.length; // n + 1 = len, n = len - 1
        //每次都能根据count的结果缩小一半查找的范围
        // 在 [1..n] 查找 nums 中重复的元素
        int left = 1;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;

            // nums 中小于等于 mid 的元素的个数
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            if (count > mid) {          //说明重复的数应该在较小的范围内
                // 下一轮搜索的区间 [left..mid]
                right = mid;     //注意不能遗漏了mid，最后才返回
            } else {                  //说明重复的数应该在较大的范围内
                // 下一轮搜索的区间 [mid + 1..right]
                left = mid + 1;
            }
        }
        return left;
    }

    public int findDuplicate4(int[] nums) {      //快慢指针，非常巧妙
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while(slow != fast){          //因为有重复数，所以必定会遇到
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int pre1 = 0;
        int pre2 = slow;
        while(pre1 != pre2){
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return pre1;
    }




}
