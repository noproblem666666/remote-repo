package Leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//分割等和子集(动态规划)（0-1背包问题）
public class hot87 {


    public boolean canPartition(int[] nums) {     //递归，超出时间限制
        int sum = Arrays.stream(nums).sum();    //计算数组的总和
        if(sum%2==1){    //如果总和是奇数则不可能平分
            return false;
        }
        int target = sum/2;
        return partition(target,new HashSet<Integer>(),nums);
    }
    public boolean partition(int target,HashSet<Integer> set , int[] nums){  //哈希表用来记录那些下标以及被记入
        //System.out.println(target);
        for (int i = 0; i < nums.length; i++) {
            if(set.contains(i)){
                continue;
            }else{
                if(nums[i]==target){
                    return true;
                }else if(nums[i]>target){
                    continue;
                }else{
                    set.add(i);
                    //System.out.println(set);
                    if(partition(target-nums[i],set,nums)){
                        return true;
                    }
                    set.remove(i);
                }
            }
        }
        return false;
    }

    public boolean canPartition2(int[] nums) {   //转化为0-1背包问题 核心思路：考虑一个数选不选，从选数和容量一点点扩大
        int len = nums.length;
        // 题目已经说非空数组，可以不做非空判断
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 特判：如果是奇数，就不符合要求
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        // 创建二维状态数组，行：物品索引，列：容量（包括 0）
        boolean[][] dp = new boolean[len][target + 1];

        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        // 再填表格后面几行
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i - 1][j];         //当前数大于target，不选当前数的情况（只要这一情况为true，那么这一列剩下的值都为true）

                if (nums[i] == j) {                   //如果这个数刚好等于target，那么就肯定为true
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {                //两种情况，不选当前数与选择当前数
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[len - 1][target];
    }

    public boolean canPartition3(int[] nums) {   //空间优化，只使用一维数组，从后往前填表
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        if (nums[0] <= target) {
            dp[nums[0]] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = target; nums[i] <= j; j--) {
                if (dp[target]) {
                    return true;
                }
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }




}
