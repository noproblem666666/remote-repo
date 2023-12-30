package nowcoder.dongtaiguihua;

import java.util.Arrays;

//最长上升子序列（一）
public class BM71 {
    //动态规划
    public int LIS(int[] arr) {
        // write code here
        if (arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        //初始子序列长度都为1
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        //System.out.println(Arrays.toString(dp));
        Arrays.sort(dp);
        //返回dp数组中的最大值即为答案
        return dp[arr.length - 1];
    }
}
