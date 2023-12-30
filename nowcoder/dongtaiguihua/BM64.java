package nowcoder.dongtaiguihua;
//最小花费爬楼梯
public class BM64 {
    public int minCostClimbingStairs (int[] cost) {
        // write code here
        if(cost.length==1){
            return cost[0];
        }
        //dp存储的是达到该层最小的花费，往上爬还需要加上该层的花费
        int[] dp = new int[cost.length+1];   //需要比cost多一个位置，表示到达楼梯顶部
        dp[0] = 0;   //到达这两级的花费都是0
        dp[1] = 0;
        for(int i = 2;i<dp.length;i++){

            dp[i] = Math.min(dp[i-2]+cost[i-2],dp[i-1]+cost[i-1]);
        }
        return dp[dp.length-1];
    }
}
