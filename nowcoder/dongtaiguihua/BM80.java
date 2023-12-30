package nowcoder.dongtaiguihua;

//买卖股票的最好时机（一）（只能买入和卖出一次）
public class BM80 {
    //一次遍历，比暴力法更好
    public int maxProfit(int[] prices) {
        // write code here
        if (prices.length < 2) {
            return 0;
        }
        int min = prices[0];
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            //遇到更小的，更新最小值，遇到比最小值更大的，就比较卖出的利润
            if (prices[i] < min) {
                min = prices[i];
            } else {
                res = Math.max(res, prices[i] - min);
            }
        }
        return res;
    }

    //动态规划，为之后的复杂情况做准备（每天都要考虑持股和不持股两种情况）
    public int maxProfit2 (int[] prices) {
        int n = prices.length;
        //dp[i][0]表示某一天不持股到该天为止的最大收益，dp[i][1]表示某天持股，到该天为止的最大收益
        int[][] dp = new int[n][2];
        //第一天不持股，总收益为0
        dp[0][0] = 0;
        //第一天持股，总收益为减去该天的股价
        dp[0][1] = -prices[0];
        //遍历后续每天，状态转移
        for(int i = 1; i < n; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //只能买卖一次，所以持有的话收益就是-prices[i]
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        //最后一天不持股，到该天为止的最大收益
        return dp[n - 1][0];
    }
}
