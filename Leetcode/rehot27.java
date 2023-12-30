package Leetcode;
//不同路径（动态规划）
public class rehot27 {
    //递归，超出时间限制
    int sum = 0;
    public int uniquePaths(int m, int n) {
        unique(m,n);
        return sum;
    }
    public void unique(int m,int n){
        if(m==1&&n==1){
            sum++;
            return;
        }
        if(m>1){
            unique(m-1,n);
        }
        if(n>1){
            unique(m,n-1);
        }
    }

    //动态规划，每个节点只有从上到达和从左到达两种路径
    public int uniquePaths2(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }

    //组合数学公式计算
    public int uniquePaths3(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }



}
