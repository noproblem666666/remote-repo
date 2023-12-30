package Leetcode;
//买卖股票的最佳时机含冷冻期（动态规划）
public class hot78 {
    public int maxProfit(int[] prices) {
        if(prices.length<2){
            return 0;
        }
        int profit = 0;
        int pre = 0;
        int buy = 0;
        int sell = 0;
        int max = 0;
        while (buy<prices.length) {
            while(buy<prices.length-1&&prices[buy]>=prices[buy+1]){   //不一定第一步就要买的
                buy++;
            }
            if(buy==prices.length-1){
                if(pre!=0){
                    max = sell;
                    for(int i =max+1;i<prices.length;i++){
                        if(prices[i]>prices[max]){
                            max = i;
                        }
                    }
                    System.out.println("max:"+ max);
                    return profit-prices[sell]+prices[max];
                }
                return profit;

            }
            sell=buy+1;
            while(sell<prices.length-2 && prices[sell+1]-prices[sell]>prices[sell+1]-prices[sell+2]){
                //这里没有考虑之后可能没有买入，导致没有算到最大的利润！！！
                sell++;
            }
            if(sell==prices.length-2&&prices[sell]<prices[sell+1]){
                sell++;
            }
            pre = prices[sell]- prices[buy];
            profit+=pre;
            //profit += prices[sell]-prices[buy];
            System.out.println("buy:"+buy);
            System.out.println("sell:"+sell);
            System.out.println("profit:"+profit);
            buy = sell+2;
        }
        return profit;
    }
    //不要关注冷冻期！不要关注冷冻期！不要关注冷冻期！ 只关注卖出的那一天！只关注卖出的那一天！只关注卖出的那一天！
    //题目中定义的“冷冻期”=卖出的那一天的后一天，题目设置冷冻期的意思是，如果昨天卖出了，今天不可买入，
    // 那么关键在于哪一天卖出，只要在今天想买入的时候判断一下前一天是不是刚卖出，即可，所以关键的一天其实是卖出的那一天，而不是卖出的后一天

    public int maxProfit2(int[] prices) {   //动态规划（四种状态）
        int days = prices.length;
        // 定义动态规划状态变量，第一维记录天数，第二维记录股票的状态
        int[][] dp= new int[days][4];

        // 初始化状态变量
        // 状态0，表示不持有股票，且没卖出，意思就是本来就不持有，没做任何买卖
        dp[0][0] = 0;
        // 状态1，表示不持有股票，原因是今天卖出了（这里因为是第0天，所以没买入，所以收益也是0）
        dp[0][1] = 0;
        // 状态2，表示持有股票，今天买入的，因为是买入，所以收入是负的，
        dp[0][2] = -1 * prices[0];
        // 状态3，表示持有股票，非今天买入，从前一天继承过来的（这里因为是第0天，所以相当于是今天买入，今天卖出）
        dp[0][3] = -1 * prices[0];

        for (int i = 1; i < days; i++) {
            // 第i天不持有且本来也不持有，那说明前一天也不持有，即使持有也卖出了，那就是0和1两种情况
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            // 第i天不持有，因为今天卖出了，那收益就是今天的股票价格加上 之前累加的
            dp[i][1] = Math.max(dp[i-1][2], dp[i-1][3]) + prices[i];
            // 第i天持有，而且是今天买入（排除状态2、3），那么根据冷冻期 昨天一定没有卖出今天才能买入
            dp[i][2] = dp[i-1][0] - prices[i];
            // 第i天持有，且非今天买入, 那就是之前买入，计算之前累加的
            dp[i][3] = Math.max(dp[i-1][2], dp[i-1][3]);
        }

        //因为买卖到最后，一定是不持有的（即使亏了，卖也比不卖强），所以应该是0和1两种状态，取较大值
        return Math.max(dp[days-1][0], dp[days-1][1]);
    }
    //由于每次的状态转移只和前一天的状态有关，可以优化空间复杂度到o(1)
}
