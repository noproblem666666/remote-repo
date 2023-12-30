package Leetcode;

import java.util.HashMap;
import java.util.Map;

//和为k的子数组（前缀和）（注意这道题有负数，不能用滑动窗口）
public class hot95 {
    public int subarraySum(int[] nums, int k) {     //暴力枚举，超时
        int len = nums.length;
        int count = 0;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {

                int sum = 0;
                for (int i = left; i <= right; i++) {
                    sum += nums[i];
                }
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum2(int[] nums, int k) {   //优化后的暴力解法，先固定一维（时间复杂度任然很差）
        int count = 0;
        int len = nums.length;
        for (int left = 0; left < len; left++) {
            int sum = 0;
            // 区间里可能会有一些互相抵销的元素
            for (int right = left; right < len; right++) {
                sum += nums[right];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum3(int[] nums, int k) {    //前缀和（时间复杂度依然很差）
        int len = nums.length;
        // 计算前缀和数组
        int[] preSum = new int[len + 1];
        preSum[0] = 0;        //默认第一个什么也不加的前缀和为0
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int count = 0;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                // 区间和 [left..right]，注意下标偏移
                if (preSum[right + 1] - preSum[left] == k) {   //如果两个数之间的前缀和差值为k，那么就符合题意
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum4(int[] nums, int k) {      //前缀和加哈希表优化，这样只需要遍历一次
        // key：前缀和，value：key 对应的前缀和的个数  （要记录个数因为这道题中有负数和0）
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0, 1);

        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;

            // 先获得前缀和为 preSum - k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }

            // 然后维护 preSumFreq 的定义
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }




}
