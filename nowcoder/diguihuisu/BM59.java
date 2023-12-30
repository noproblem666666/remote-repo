package nowcoder.diguihuisu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//N皇后问题（较难）
public class BM59 {
    int count;
    public int Nqueen(int n) {    //Todo:方法有问题，因为在回溯的过程中，我们可能把前几层的限制也一并解除了
        // write code here
        if (n == 0) {
            return 0;
        }
        int[][] temp = new int[n][n];   //记录那些空格不可用
        find(n, temp, 0);
        return count;
    }

    public boolean isOut(int n, int i) {
        return i < 0 || i > n - 1;
    }

    public void find(int n, int[][] temp, int m) {
        if (m == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (temp[m][i] == 0) {
                temp[m][i] = 1;
                for (int j = 1; j + m < n; j++) {
                    temp[m + j][i] = 1;
                    if (!isOut(n, i + j)&&temp[m + j][i + j] == 0) {
                        temp[m + j][i + j] = 1;
                    }
                    if (!isOut(n, i - j)&&temp[m + j][i - j] == 0) {
                        temp[m + j][i - j] = 1;
                    }
                }
                find(n,temp,m+1);
                temp[m][i] = 0;
                for (int j = 1; j + m < n; j++) {
                    temp[m + j][i] = 0;
                    if (!isOut(n, i + j)) {
                        temp[m + j][i + j] = 0;
                    }
                    if (!isOut(n, i - j)) {
                        temp[m + j][i - j] = 0;
                    }
                }

            }
        }

    }


    //使用集合标记斜线 Todo：标记方式非常巧妙
    Set<Integer> column = new HashSet<Integer>(); //标记列不可用
    Set<Integer> posSlant = new HashSet<Integer>();//标记正斜线不可用
    Set<Integer> conSlant = new HashSet<Integer>();//标记反斜线不可用
    int result = 0;

    public int Nqueen2 (int n) {
        // write code here
        compute(0, n);
        return result;
    }
    private void compute(int i, int n){
        if(i == n){
            result++;
            return;
        }
        for(int j = 0; j < n; j++){   //注意此时j是变的 1-1 2-2 3-3的方式使得斜线都会被判定不通过
            if(column.contains(j) || posSlant.contains(i - j) || conSlant.contains(i + j)){
                continue;
            }
            column.add(j);//列号j
            posSlant.add(i - j);//行号i - 列号j 正斜线
            conSlant.add(i + j);//行号i + 列号j 反斜线
            compute(i + 1, n); //计算下一行
            column.remove(j); //完成上一步递归计算后，清除
            posSlant.remove(i - j);
            conSlant.remove(i + j);
        }
    }


    int res;
    public int Nqueen3 (int n) {    //位运算
        // write code here
        backtrack(0, 0, 0, 0, n);
        return res;
    }
    public void backtrack(int i, int col, int pos, int neg, int n){
        if(i == n){
            res++;
            return;
        }
        //标记放皇后的位置
        int pre = ~(col | pos | neg) & ((1 << n) - 1);
        //遍历pre
        while(pre > 0){
            int cur = pre & (-pre);
            //当前行放置了一个皇后之后进入下一行
            backtrack(i + 1, col | cur, (pos | cur) >> 1, (neg | cur) << 1, n);
            pre &= pre - 1;
        }
    }


    private int res2;
    //判断皇后是否符合条件
    public boolean isValid(int[] pos, int row, int col){
        //遍历所有已经记录的行
        for(int i = 0; i < row; i++){
            //不能同行同列同一斜线（注意判断条件）
            if(row == i || col == pos[i] || Math.abs(row - i) == Math.abs(col - pos[i]))
                return false;
        }
        return true;
    }

    //递归查找皇后种类
    public void recursion(int n, int row, int[] pos){
        //完成全部行都选择了位置
        if(row == n){
            res++;
            return;
        }
        //遍历所有列
        for(int i = 0; i < n; i++){
            //检查该位置是否符合条件
            if(isValid(pos, row, i)){
                //加入位置
                pos[row] = i;
                //递归继续查找
                recursion(n, row + 1, pos);
            }
        }
    }
    public int Nqueen4 (int n) {
        res = 0;
        //下标为行号，元素为列号，记录皇后位置
        int[] pos = new int[n];
        Arrays.fill(pos, 0);
        //递归
        recursion(n, 0, pos);
        return res;
    }

}
