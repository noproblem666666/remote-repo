package nowcoder.dongtaiguihua;

import java.util.Arrays;
import java.util.Collections;

//兑换零钱（一）（推荐动态规划）
public class BM70 {
    public int minMoney (int[] arr, int aim) {
        // write code here
        if(aim==0){
            return 0;
        }
        if(arr==null||arr.length==0){
            return -1;
        }
        Integer[] integers = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        //必须先转换成包装类，才能用逆序方法
        Arrays.sort(integers, Collections.reverseOrder());
        //两个数组，一个表示从0到aim的钱数是否可达，另一个如果可达就更新需要的钱数
        int[] res = new int[aim+1];
        int[] temp = new int[aim+1];
        temp[0] = 1;
        for (int i = 0; i < res.length; i++) {
            for (int j : arr) {
                if(temp[i]==1 && i+j<aim+1){
                    if(temp[i+j]==0){
                        temp[i+j] = 1;
                        //更新初始的钱数，直接取最小值会都是0
                        res[i+j] = res[i]+1;
                    }
                    //不停的取最小值
                    res[i+j] = Math.min(res[i+j],res[i]+1);
                }
            }
        }
        //说明不可达
        if(res[aim] == 0){
            return -1;
        }
        return res[aim];
    }

    //答案写法，更加简便，只申请一个数组，初始化为aim+1的值，这样就可以直接取最小值
    public int minMoney2(int[] arr, int aim) {
        //小于1的都返回0
        if(aim < 1)
            return 0;
        int[] dp = new int[aim + 1];
        //dp[i]表示凑齐i元最少需要多少货币数
        Arrays.fill(dp, aim + 1);
        dp[0] = 0;
        //遍历1-aim元
        for(int i = 1; i <= aim; i++){
            //每种面值的货币都要枚举
            for(int j = 0; j < arr.length; j++){
                //如果面值不超过要凑的钱才能用
                if(arr[j] <= i)
                    //维护最小值
                    dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);
            }
        }
        //如果最终答案大于aim代表无解
        return dp[aim] > aim ? -1 : dp[aim];
    }

    //递归，记录计算结果以减小时间复杂度
    public int recursion(int[] arr, int aim, int[] dp){
        //组合超过了，返回-1
        if(aim < 0)
            return -1;
        //组合刚好等于需要的零钱
        if(aim == 0)
            return 0;
        //剩余零钱是否已经被运算过了
        if(dp[aim - 1] != 0)
            return dp[aim - 1];
        int Min = Integer.MAX_VALUE;
        //遍历所有面值
        for(int i = 0; i < arr.length; i++){
            //递归运算选择当前的面值
            int res = recursion(arr, aim - arr[i], dp);
            //获取最小值
            if(res >= 0 && res < Min)
                Min = res + 1;
        }
        //更新最小值
        dp[aim - 1] = Min == Integer.MAX_VALUE ? -1 : Min;
        return dp[aim - 1];
    }

    public int minMoney3 (int[] arr, int aim) {
        //小于1的都返回0
        if(aim < 1)
            return 0;
        //dp[i]表示凑齐i元最少需要多少货币数
        int[] dp = new int[aim + 1];
        return recursion(arr, aim, dp);
    }
}
