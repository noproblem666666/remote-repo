package nowcoder.dongtaiguihua;
//连续子数组的最大和
public class BM72 {
    //动态规划
    public int FindGreatestSumOfSubArray(int[] array) {
        //dp[i] 代表以元素 array[i] 为结尾的连续子数组最大和。
        int[] dp = new int[array.length];
        int max = array[0];
        dp[0] = array[0];
        for(int i=1;i<array.length;i++){
            // 动态规划，状态转移方程，确定dp[i]的最大值
            //要么加上之前的和，要么以自己为开始
            dp[i] = Math.max(dp[i-1] + array[i], array[i]);
            // 每次比较，保存出现的最大值
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    //暴力法（会超时）
    public int FindGreatestSumOfSubArray2(int[] array) {
        int max = array[0];
        int sum = 0;
        for(int i=0;i<array.length;i++){
            // 每开启新的循环，需要把sum归零
            sum = 0;
            for(int j=i;j<array.length;j++){
                // 这里是求从i到j的数值和
                sum += array[j];
                // 每次比较，保存出现的最大值
                max = Math.max(max,sum);
            }
        }
        return max;
    }

    //优化空间后的动态规划
    public int FindGreatestSumOfSubArray3(int[] array) {
        int sum = 0;
        int max = array[0];
        for(int i=0;i<array.length;i++){
            // 优化动态规划，确定sum的最大值
            sum = Math.max(sum + array[i], array[i]);
            // 每次比较，保存出现的最大值
            max = Math.max(max,sum);
        }
        return max;
    }
}
