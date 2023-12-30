package nowcoder.moni;

import java.util.ArrayList;
import java.util.List;

//螺旋矩阵 Todo：记一下怎么写
public class BM98 {
    //按照始终指针转动规则
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        if(matrix.length == 0)
            return res;
        // 定义四个指针，并且充当边界限制的作用
        int top = 0, bottom = matrix.length-1;
        int left = 0, right = matrix[0].length-1;

        while( top < (matrix.length+1)/2 && left < (matrix[0].length+1)/2 ){
            //上面  左到右
            for(int i = left; i <= right; i++){
                res.add(matrix[top][i]);
            }

            //右边 上到下
            for(int i = top+1; i <= bottom; i++){
                res.add(matrix[i][right]);
            }

            //下面  右到左
            for(int i = right-1; top!=bottom && i>=left; i--){
                res.add(matrix[bottom][i]);
            }

            //左边 下到上
            for(int i = bottom-1; left!=right && i>=top+1; i--){
                res.add(matrix[i][left]);
            }
            // 遍历完一圈之后，所有往里面靠
            ++top;
            --bottom;
            ++left;
            --right;
        }
        return res;
    }

    //按照圈进行模拟
    public ArrayList<Integer> spiralOrder2(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        // 进行数组的判空
        if(matrix.length == 0)
            return res;
        // 找到整个数组的 右边界 和 下边界
        int m = matrix.length, n = matrix[0].length;
        // 两个点（0，m-1）  （0，n-1）
        circle(matrix, 0, 0, m - 1, n - 1, res);
        return res;

    }
    // 遍历 以 (x1, y1) 作为左上角，(x2, y2) 作为右下角形成的「圈」
    void circle(int[][] matrix, int x1, int y1, int x2, int y2, List<Integer> ans) {
        // 此时则已经交叉所以退出
        if (x2 < x1 || y2 < y1) return;

        // 只有一行时，按「行」遍历
        if (x1 == x2) {
            for (int i = y1; i <= y2; i++) ans.add(matrix[x1][i]);
            return;
        }
        // 只有一列时，按「列」遍历
        if (y1 == y2) {
            for (int i = x1; i <= x2; i++) ans.add(matrix[i][y2]);
            return;
        }

        // 遍历当前「圈」
        // 从左往右
        for (int i = y1; i < y2; i++) ans.add(matrix[x1][i]);
        // 从上往下
        for (int i = x1; i < x2; i++) ans.add(matrix[i][y2]);
        // 从右往左
        for (int i = y2; i > y1; i--) ans.add(matrix[x2][i]);
        // 从下往上
        for (int i = x2; i > x1; i--) ans.add(matrix[i][y1]);

        // 往里收一圈，继续递归遍历
        circle(matrix, x1 + 1, y1 + 1, x2 - 1, y2 - 1, ans);
    }
}
