package nowcoder.dongtaiguihua;

//编辑距离（一）
public class BM75 {
    public int editDistance(String str1, String str2) {
        // write code here
        int n1 = str1.length();
        int n2 = str2.length();
        //表示进行到两个字符串的第几个位置时所花费的操作步数
        int[][] dp = new int[n1 + 1][n2 + 1];
        //初始化边界
        for (int i = 1; i < n1 + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i < n2 + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < n1 + 1; i++) {
            for (int j = 1; j < n2 + 1; j++) {
                //字符相等时，说明不需要进行改变
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {  //此时肯定需要加一步操作，不管是删除增加还是修改
                    //选取最小的距离加上此处的距离1
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[n1][n2];
    }
}
