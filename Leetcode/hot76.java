package Leetcode;

import java.util.Arrays;

//最长递增子序列（动态规划）
//「无后效性」的设计思想：让不确定的因素确定下来，以保证求解的过程形成一个逻辑上的有向无环图。
// 这题不确定的因素是某个元素是否被选中，而我们设计状态的时候，让 nums[i] 必需被选中，
// 这一点是「让不确定的因素确定下来」，也是我们这样设计状态的原因。
public class hot76 {
    public int lengthOfLIS(int[] nums) { //时间复杂度为：o(n*n)     空间复杂度为：o(n)
        if (nums == null) {
            return 0;
        }
        //第一步，定义状态，确定dp[i]表示以nums[i]为结尾的最长递增子序列长度（必须选中这一位）
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);   //初始化数组的值为1，因为以这一位为结尾子序列至少为1
        //第二步，确定状态转移方程，只要当前这一位大于前面的某位数字，那么就可以在那位数字的最长子序列上加一组成自己的最长子序列，然后这一位取最长的一个
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        //注意最后返回的结果应该是所有位中最大的一个，而不是最后一个
        int max = 0;
        for (int i : dp) {
            max = Math.max(max,i);
        }
        return max;
        //因为这道题每次刷新一个状态时，必须保存前面所有的状态，所以不能进行空间优化
    }



    //考虑一个简单的贪心，如果我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢，因此我们希望每次在上升子序列最后加上的那个数尽可能的小。
    public int lengthOfLIS2(int[] nums) {      //更加巧妙的方法（使用了贪心算法和二分查找） 时间复杂度降低为o(n*log(n))
        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        // tail 数组的定义：长度为 i + 1 的上升子序列的末尾最小是几
        int[] tail = new int[len];
        // 遍历第 1 个数，直接放在有序数组 tail 的开头
        tail[0] = nums[0];
        // end 表示有序数组 tail 的最后一个已经赋值元素的索引
        int end = 0;

        for (int i = 1; i < len; i++) {
            // 【逻辑 1】比 tail 数组实际有效的末尾的那个元素还大
            if (nums[i] > tail[end]) {
                // 直接添加在那个元素的后面，所以 end 先加 1
                end++;
                tail[end] = nums[i];
            } else {
                // 使用二分查找法，在有序数组 tail 中
                // 找到第 1 个大于等于 nums[i] 的元素，尝试让那个元素更小
                int left = 0;
                int right = end;
                while (left < right) {
                    // 选左中位数不是偶然，而是有原因的，原因请见 LeetCode 第 35 题题解
                    // int mid = left + (right - left) / 2;
                    int mid = left + ((right - left) >>> 1);
                    if (tail[mid] < nums[i]) {
                        // 中位数肯定不是要找的数，把它写在分支的前面
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                // 走到这里是因为 【逻辑 1】 的反面，因此一定能找到第 1 个大于等于 nums[i] 的元素
                // 因此，无需再单独判断
                tail[left] = nums[i];
            }
            // 调试方法
            // printArray(nums[i], tail);
        }
        // 此时 end 是有序数组 tail 最后一个元素的索引
        // 题目要求返回的是长度，因此 +1 后返回
        end++;
        return end;
    }

    // 调试方法，以观察是否运行正确
    private void printArray(int num, int[] tail) {
        System.out.print("当前数字：" + num);
        System.out.print("\t当前 tail 数组：");
        int len = tail.length;
        for (int i = 0; i < len; i++) {
            if (tail[i] == 0) {
                break;
            }
            System.out.print(tail[i] + ", ");
        }
        System.out.println();
    }




}
