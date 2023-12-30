package nowcoder.dongtaiguihua;
//不同路径的数目（一）
public class BM67 {
    //动态规划
    public int uniquePaths (int m, int n) {
        // write code here
        if(m==0||n==0){
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = 1;    //起始点应该默认位一种到达方式
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    //组合数学
    int uniquePaths2(int m, int n) {
        long ret = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            //组合公式的循环求解
            ret = ret * x / y;
        }
        return (int) ret;
    }

    //递归（从终点到起点回溯）（耗时较长）
    public int uniquePaths3 (int m, int n) {
        // 起点（1，1） 这里为什么是（1，1）呢？其实是一样的，我们上面的方法定义了（0，0）为起点，那么终点就为（m-1，n-1）
        // 这里起点为（1，1）那么终点就为 （m，n）
        if(m == 1 || n == 1)
            return 1;
        // 终点（m，n）
        return uniquePaths3(m,n-1)+uniquePaths3(m-1,n);
    }
}
