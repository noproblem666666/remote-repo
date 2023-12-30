package nowcoder.dongtaiguihua;

import java.util.HashMap;
import java.util.Map;

//矩阵的最小路径和
public class BM68 {
    //动态规划（直接对原数组修改，不需要新建数组）
    public int minPathSum (int[][] matrix) {
        // write code here
        if(matrix==null||matrix.length==0||matrix[0].length==0){
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            matrix[i][0] += matrix[i-1][0];
        }
        for (int j = 1; j < n; j++) {
            matrix[0][j] += matrix[0][j-1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] += Math.min(matrix[i-1][j],matrix[i][j-1]);
            }
        }
        return matrix[m-1][n-1];
    }

    //递归（会超时）
    public int minPathSum2(int[][] grid) {
        return minPathSum(grid, grid.length - 1, grid[0].length - 1);
    }

    public int minPathSum(int[][] grid, int i, int j) {
        if (i == 0 && j == 0)
            return grid[i][j];
        //第一行只能从左边走过来
        if (i == 0)
            return grid[i][j] + minPathSum(grid, i, j - 1);
        //第一列只能从上面走下来
        if (j == 0)
            return grid[i][j] + minPathSum(grid, i - 1, j);
        //取从上面走下来和从左边走过来的最小值+当前坐标的值
        return grid[i][j] + Math.min(minPathSum(grid, i - 1, j), minPathSum(grid, i, j - 1));
    }

    //用map存储，避免重复计算
    public int minPathSum3(int[][] grid) {
        return minPathSum(grid, grid.length - 1, grid[0].length - 1, new HashMap<String, Integer>());
    }

    public int minPathSum(int[][] grid, int i, int j, Map<String, Integer> map) {
        if (i == 0 && j == 0)
            return grid[i][j];
        String key = i + "*" + j;
        if (map.containsKey(key))
            return map.get(key);
        int res = 0;
        //第一行只能从左边走过来
        if (i == 0)
            res = grid[i][j] + minPathSum(grid, i, j - 1, map);
            //第一列只能从上面走下来
        else if (j == 0)
            res = grid[i][j] + minPathSum(grid, i - 1, j, map);
            //取从上面走下来和从左边走过来的最小值+当前坐标的值
        else
            res = grid[i][j] + Math.min(minPathSum(grid, i - 1, j, map), minPathSum(grid, i, j - 1, map));
        map.put(key, res);
        return res;
    }
}
