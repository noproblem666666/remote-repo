package nowcoder.dongtaiguihua;

import java.util.Arrays;

//把数字翻译成字符串
public class BM69 {
    //动态规划 Todo：比较巧妙
    public int solve (String nums) {
        //排除0
        if(nums.equals("0"))
            return 0;
        //排除只有一种可能的10 和 20
        if(nums == "10" || nums == "20")
            return 1;
        //当0的前面不是1或2时，无法译码，0种
        for(int i = 1; i < nums.length(); i++){
            if(nums.charAt(i) == '0')
                if(nums.charAt(i - 1) != '1' && nums.charAt(i - 1) != '2')
                    return 0;
        }
        int[] dp = new int[nums.length() + 1];
        //辅助数组初始化为1
        Arrays.fill(dp, 1);
        for(int i = 2; i <= nums.length(); i++){
            //在11-19，21-26之间的情况
            if((nums.charAt(i - 2) == '1' && nums.charAt(i - 1) != '0') || (nums.charAt(i - 2) == '2' && nums.charAt(i - 1) > '0' && nums.charAt(i - 1) < '7'))
                dp[i] = dp[i - 1] + dp[i - 2];
            else
                dp[i] = dp[i - 1];
        }
        return dp[nums.length()];
    }

    //递归
    public int solve2 (String nums) {
        return back(nums.toCharArray(), 0);
    }
    // 递归函数
    public int back(char[] nums, int start){
        //当start走到终点时，证明已经解码完毕，直接返回1
        if(start == nums.length){
            return 1;
        }
        //当字符为0的时候，0没对应的解码，所以直接返回0 (此路解码废掉)
        if(nums[start] == '0')
            return 0;
        //每次解码一个字符
        int res1 = back(nums,start+1);
        int res2 = 0;

        //如果当前字符等于1 或者 当前字符加上下一个字符合起来小于等于26 则可以一次解码两个字符
        if((start < nums.length-1) && (nums[start] == '1' || (nums[start] == '2' &&nums[start+1] <= '6'))){
            res2 = back(nums,start+2);
        }
        //返回结果
        return res1 + res2;
    }
}
