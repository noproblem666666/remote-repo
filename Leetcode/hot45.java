package Leetcode;

import java.util.HashMap;
import java.util.Map;

//买卖股票的最佳时机
public class hot45 {
    public int maxProfit(int[] prices) {     //可以看作是一种动态规划
        if(prices.length<2){
            return 0;
        }
        HashMap<Integer,Integer> hashMap =new HashMap<>(); //用来存储可能的买入与卖出日期
        int buy = 0;
        int sell = 0;
        for (int i = 1; i < prices.length; i++) {     //只需遍历一次就够了
            if(prices[i]<prices[buy]){      //如果低于最低价，就从这起重新计算buy和sell
                hashMap.put(buy,sell);
                buy=i;
                sell=i;
            }
            if(prices[i]>prices[sell]){     //如果高于最高价，就记录为sell
                sell=i;
            }
        }
        hashMap.put(buy,sell);
        int max =0;
        for(Map.Entry<Integer,Integer> entry : hashMap.entrySet()){      //遍历hashmap计算最大利润
            max = Math.max(max,prices[entry.getValue()]-prices[entry.getKey()]);
        }
        return max;
    }

        public int maxProfit2(int prices[]) {        //答案，更加简洁，不需要hashmap，直接每次更新最大值
            int minprice = Integer.MAX_VALUE;
            int maxprofit = 0;
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < minprice) {
                    minprice = prices[i];
                } else if (prices[i] - minprice > maxprofit) {
                    maxprofit = prices[i] - minprice;
                }
            }
            return maxprofit;
        }



}
