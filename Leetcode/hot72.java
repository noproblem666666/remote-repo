package Leetcode;

import java.util.Arrays;

//完全平方数
public class hot72 {
    public int numSquares(int n) {     //解答错误
        int num = 1;
        while(n>=num*num){
            num++;
        }
        int[] nums = new int[num-1];
        for(int i=0;i<num-1;i++){
            nums[i]=(i+1)*(i+1);
        }
        int min = Integer.MAX_VALUE;
        if(num-2==0){
            return n;
        }
        while(num-2>0){
            min = Math.min(squares(n,nums,0,num-2,0),min);
            num--;
        }
        return min;
    }
    public int squares(int n,int[] nums,int sum,int index,int num){
        while(n>sum){                    //因为数组中最小一位是1，所以肯定能凑齐，不用考虑失败的可能
            sum=sum+nums[index];
            num++;
        }
        if(sum==n){
            return num;
        }
        sum=sum-nums[index];
        num--;
        return squares(n,nums,sum,index-1,num);    //每次都搭配最大数的贪心算法并不一定正确

    }

    public int numSquares2(int n) {      //完全背包问题（动态规划）
        int num=(int)Math.sqrt(n);     //num是可能用到的最大平方数
        int[] dp=new int[n+1];
        Arrays.fill(dp,n);     //最坏的情况就是全用1来搭配
        dp[0]=0;
        for(int i=1;i<=num;i++){                //注意循环嵌套的顺序
            for(int j=1;j<=n;j++){
                if(j-i*i>=0) dp[j]=Math.min(dp[j],dp[j-i*i]+1);     //状态转移方程，如果这个数能用一个平方数来搭配，那他就用减去这个数的状态的数加一，比较最小值
            }
        }
        return dp[n];
    }


}
