package nowcoder.haxi;

import java.util.Arrays;
import java.util.HashSet;

//缺失的第一个正整数 Todo：最佳方法：原地哈希
public class BM53 {

    //最基础的办法，使用辅助数组
    public int minNumberDisappeared(int[] nums) {
        // write code here
        int[] temp = new int[nums.length + 1]; //默认从下标1开始算整数1
        for (int num : nums) {
            if (num > nums.length || num <= 0) {
                continue;
            }
            temp[num] = 1;
        }
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] == 0) {
                return i;
            }
        }
        return temp.length;   //即nums.length+1 , 如果前面的正数都出现了
    }

    //排序法
    public int minNumberDisappeared2(int[] nums) {
        // write code here
        Arrays.sort(nums);
        int temp = 1;
        for (int num : nums) {
            if (num <= 0) {
                continue;
            }
            if (num != temp) {
                return temp;
            } else {
                temp++;
            }
        }
        return temp;   //之前的正数都没有错过
    }

    //哈希表,存储所有存在的数字
    public int minNumberDisappeared3(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        int temp = 1;   //从1开始找
        while (hashSet.contains(temp)) {
            temp++;
        }
        return temp;
    }

    //原地哈希 ,空间复杂度为1，时间复杂度为o(n)
    public int minNumberDisappeared4(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (Math.abs(nums[i]) <= n && Math.abs(nums[i]) > 0) {
                //需要减一，用下标0代表数字1，不然数组空间不够用
                //如果有重复元素的话，就需要取绝对值的负数，防止两次取反不变
                nums[Math.abs(nums[i]) - 1] = -nums[Math.abs(nums[i]) - 1];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;   //记得加一
            }
        }
        return nums.length + 1;
    }
}
