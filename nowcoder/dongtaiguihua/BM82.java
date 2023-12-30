package nowcoder.dongtaiguihua;

import java.util.Arrays;

//买卖股票的最好时间（三）（限定只能买卖股票两次）
public class BM82 {
    //错误写法
    public int maxProfit(int[] prices) {
        // write code here
        if (prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        //辅助计数数组，表示当前已经进行了多少次买卖
        int[][] count = new int[prices.length + 1][2];

        dp[0][1] = -prices[0];
        //别忘了这一次计数
        count[0][1] = 1;

        //Todo：还是不对，这样只会尽快用掉两次机会，而不是选择最大利益
        for (int i = 1; i < dp.length; i++) {
            //计数看的是前一天的操作次数，而不是今天的，不然更新无意义
            if (count[i - 1][1] < 4) {
                //等于的情况不要浪费交易次数
                if (dp[i - 1][0] >= dp[i - 1][1] + prices[i]) {
                    dp[i][0] = dp[i - 1][0];
                } else {
                    dp[i][0] = dp[i - 1][1] + prices[i];
                    count[i][0] = count[i - 1][1] + 1;
                }
            }
            if (count[i - 1][0] < 3) {
                //等于的情况不要浪费交易次数
                if (dp[i - 1][0] - prices[i] > dp[i - 1][1]) {
                    dp[i][1] = dp[i - 1][0] - prices[i];
                    count[i][1] = count[i - 1][0] + 1;
                } else {
                    dp[i][1] = dp[i - 1][1];
                }
            }
        }
        return dp[prices.length - 1][0];
    }

    //动态规划，其实可以用在递归中的思想
    public int maxProfit2(int[] prices) {
        // write code here
        if (prices.length < 2) {
            return 0;
        }
        int min = prices[0];
        int res = 0;
        //记录第一次买卖的左右坐标
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 1; i < prices.length; i++) {
            //遇到更小的，更新最小值，遇到比最小值更大的，就比较卖出的利润
            if (prices[i] < min) {
                min = prices[i];
                minIndex = i;
            } else {
                //这个判断要放在前面，否则res值已经改变，这个不等式永远不会成立
                if (prices[i] - min > res) {
                    maxIndex = i;
                }
                res = Math.max(res, prices[i] - min);

            }
        }
        //第二次买入卖出肯定在第一次买入卖出区间之外
        //在被分割的数组的基础上进行下一次查找 Todo:如果题目设定的次数是参数的话，需要用递归
        int res1 = 0;
        min = prices[0];
        for (int i = 1; i < minIndex; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                res1 = Math.max(res1, prices[i] - min);
            }
        }
//        System.out.println(minIndex);
//        System.out.println(maxIndex);
        int res2 = 0;
        if (maxIndex == prices.length - 1) {
            return res + res1;
        }
        min = prices[maxIndex];
        for (int i = maxIndex + 1; i < prices.length; i++) {
            //System.out.println(prices[i]);
            if (prices[i] < min) {
                min = prices[i];
            } else {
                res2 = Math.max(res2, prices[i] - min);
            }
        }
//        System.out.println(res1);
//        System.out.println(res2);
        return Math.max(res1, res2) + res;
    }


    //答案动态规划，多设立几个状态即可
    public int maxProfit3 (int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][5];
        //初始化dp为最小
        Arrays.fill(dp[0], -10000);
        //第0天不持有状态
        dp[0][0] = 0;
        //第0天持有股票
        dp[0][1] = -prices[0];
        //状态转移
        for(int i = 1; i < n; i++){
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        //Todo：选取最大值，可以只操作一次
        //可以只买卖一次
        return Math.max(dp[n - 1][2],Math.max(0, dp[n - 1][4]));
    }
}
