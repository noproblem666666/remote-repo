package Leetcode;
//最大正方形
public class hot64 {
    public int maximalSquare(char[][] matrix) {     //（DFS）时间复杂度太高，超出时间限制了

        int count = 1;
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j]=='1'){
                    max=Math.max(max,maxSquare(matrix,i,j,count));
                }
            }
        }
        return max;

    }
    public int maxSquare(char[][] matrix,int x,int y,int count){
        for(int i=x;i<=x+count;i++){
            for(int j=y;j<=y+count;j++){
                if(i>matrix.length-1||j>matrix[i].length-1||matrix[i][j]=='0'){
                    return count*count;   //说明这一级不是正方形，返回当前大小
                }

            }
        }
        return maxSquare(matrix,x,y,count+1);    //当前大小的正方形检查完毕，准备检查大一级体积的正方形

    }

    public int maximalSquare2(char[][] matrix) {      //动态规划（对比dfs大大降低时间复杂度）
        // base condition
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;

        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;

        // 相当于已经预处理新增第一行、第一列均为0
        int[][] dp = new int[height + 1][width + 1];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (matrix[row][col] == '1') {       //此处注意如何求该位置的正方形边长
                    dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]), dp[row][col]) + 1;
                    maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                }
            }
        }
        return maxSide * maxSide;
    }

    public int maximalSquare3(char[][] matrix) {      //另一种暴力方法，但是由于每次只多检测一行一列，所以勉强能通过，时间复杂度还是很高
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    // 遇到一个 1 作为正方形的左上角
                    maxSide = Math.max(maxSide, 1);
                    // 计算可能的最大正方形边长
                    int currentMaxSide = Math.min(rows - i, columns - j);
                    for (int k = 1; k < currentMaxSide; k++) {
                        // 判断新增的一行一列是否均为 1
                        boolean flag = true;
                        if (matrix[i + k][j + k] == '0') {
                            break;
                        }
                        for (int m = 0; m < k; m++) {
                            if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            maxSide = Math.max(maxSide, k + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }




}
