package Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
//两数相加
public class rehot01 {
    public int[] twoSum(int[] nums, int target) {
        //先排序再双指针
        int[] temp = Arrays.copyOf(nums, nums.length);
        Arrays.sort(temp);
        int m = 0;
        int n = temp.length - 1;
        while (m < n && temp[m] + temp[n] != target) {
            if (temp[m] + temp[n] < target) {
                m++;
            } else {
                n--;
            }
        }
        int[] res = new int[2];
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == temp[m] || nums[j] == temp[n]) {
                res[i] = j;
                i++;
            }
        }
        return res;
    }

    //哈希表查找
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }


}
