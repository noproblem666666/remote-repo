package nowcoder.dongtaiguihua;

//买卖股票的最好时机（二）（可以多次买卖，但是买入前必须卖出）
public class BM81 {
    //动态规划
    public int maxProfit(int[] prices) {
        // write code here
        if (prices.length < 2) {
            return 0;
        }
        //代表每一天持有股票和不持有股票的收益
        int[][] dp = new int[prices.length][2];
        //第一天买入的话，收益为负值
        dp[0][1] = -prices[0];
        //从头遍历每一天
        for (int i = 1; i < dp.length; i++) {
            //不持有说明之前也没持有或者今天卖出了
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //持有说明今天买入或者之前已经持有（因为可以多次买卖，所以和BM80中的转移方程不一样）
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        //返回最后一天不持股时的最大收益
        return dp[prices.length-1][0];
    }

    //贪心算法，因为可以多次买入卖出，那么前后比较两个数，只要是递增的就算为收益，递减或持平就不管即可
    public int maxProfit2 (int[] prices) {
        int res = 0;
        for(int i = 1; i < prices.length; i++){
            //只要某段在递增就有收益
            if(prices[i - 1] < prices[i])
                //收益累加
                res += prices[i] - prices[i - 1];
        }
        return res;
    }

    //因为计算只和前一天的情况有关，可以省去创建数组的空间复杂度
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int length = prices.length;
        //初始条件
        int hold = -prices[0];//持有股票
        int noHold = 0;//没持有股票
        for (int i = 1; i < length; i++) {
            //递推公式转化的
            noHold = Math.max(noHold, hold + prices[i]);
            hold = Math.max(hold, noHold - prices[i]);
        }
        //最后一天肯定是手里没有股票的时候利润才会最大，
        //所以这里返回的是noHold
        return noHold;
    }
}
