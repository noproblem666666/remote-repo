package nowcoder.diguihuisu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//矩阵最长递增路径
public class BM61 {
    //DFS深度优先遍历
    int max = 0;
    public int solve (int[][] matrix) {   //从每个坐标入口执行一次递归
        // write code here
        if(matrix==null||matrix.length==0||matrix[0].length==0){
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] temp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0;  j< n; j++) {
                findPath(matrix,temp,i,j,1);
            }
        }
        return max;
    }

    //递归查找当前最长路径
    public void findPath(int[][] matrix,int[][] temp,int x,int y,int count){
        if(count>max){
            max = count;
        }
        temp[x][y] = 1;
        ArrayList<Integer> arrayList = findBigger(matrix,temp,x,y);
        for (Integer integer : arrayList) {
            if(integer==1){
                findPath(matrix,temp,x-1,y,count+1);
            }else if(integer==2){
                findPath(matrix,temp,x+1,y,count+1);
            }else if(integer==3){
                findPath(matrix,temp,x,y-1,count+1);
            }else{
                findPath(matrix,temp,x,y+1,count+1);
            }
            temp[x][y] = 0;
        }

    }
    //找到下一个更大的且未曾被当前递归经过的结点
    public ArrayList<Integer> findBigger(int[][] matrix, int[][] temp, int x, int y){
        ArrayList<Integer> res = new ArrayList<>();
        if(isOut(matrix,x-1,y)&&matrix[x-1][y]>matrix[x][y]){
            res.add(1);
        }
        if(isOut(matrix,x+1,y)&&matrix[x+1][y]>matrix[x][y]){
            res.add(2);
        }
        if(isOut(matrix,x,y-1)&&matrix[x][y-1]>matrix[x][y]){
            res.add(3);
        }
        if(isOut(matrix,x,y+1)&&matrix[x][y+1]>matrix[x][y]){
            res.add(4);
        }
        return res;
    }
    //判断坐标是否合法
    public boolean isOut(int[][] matrix,int x,int y){
        return x>=0&&x<matrix.length&&y>=0&&y<matrix[0].length;
    }


    //答案写法，速度更快
    //记录四个方向
    private int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int n, m;
    //深度优先搜索，返回最大单元格数
    public int dfs(int[][] matrix, int[][] dp, int i, int j) {
        if(dp[i][j] != 0)
            return dp[i][j];
        dp[i][j]++;
        for (int k = 0; k < 4; k++) {
            int nexti = i + dirs[k][0];
            int nextj = j + dirs[k][1];
            //判断条件
            if(nexti >= 0 && nexti < n && nextj >= 0 && nextj < m && matrix[nexti][nextj] > matrix[i][j])
                dp[i][j] = Math.max(dp[i][j], dfs(matrix, dp, nexti, nextj) + 1);
        }
        return dp[i][j];
    }
    public int solve2 (int[][] matrix) {
        //矩阵不为空
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int res = 0;
        n = matrix.length;
        m = matrix[0].length;
        //i，j处的单元格拥有的最长递增路径
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                //更新最大值
                res = Math.max(res, dfs(matrix, dp, i, j));
        return res;
    }

    //BFS广度优先遍历，利用拓扑不停计算刷新出度（不停的找出度为0的坐标，再找周围比他小的坐标，逆向搜索）
    //记录四个方向
    private int[][] dirs1 = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int n1, m1;
    public int solve3 (int[][] matrix) {
        //矩阵不为空
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int res = 0;
        n = matrix.length;
        m = matrix[0].length;
        //i，j处的单元格拥有的最长递增路径
        int[][] outdegrees = new int[m + 1][n + 1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                for(int k = 0; k < 4; k++){
                    int nexti = i + dirs[k][0];
                    int nextj = j + dirs[k][1];
                    if(nexti >= 0 && nexti < n && nextj >= 0 && nextj < m && matrix[nexti][nextj] > matrix[i][j]){
                        //符合条件，记录出度
                        outdegrees[i][j]++;
                    }
                }
            }
        }
        //辅助队列
        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(outdegrees[i][j] == 0){
                    //找到出度为0的入队列
                    q1.offer(i);
                    q2.offer(j);
                }
            }
        }
        while(!q1.isEmpty()){
            res++;
            int size = q1.size();//因为是广度优先遍历，对于每个长度，都需要遍历到所有的出度为0的结点
            for(int x = 0; x < size; x++){
                int i = q1.poll();
                int j = q2.poll();
                //四个方向
                for(int k = 0; k < 4; k++){
                    int nexti = i + dirs[k][0];
                    int nextj = j + dirs[k][1];
                    //逆向搜索，所以下一步是小于
                    if (nexti >= 0 && nexti < n && nextj >= 0 && nextj < m && matrix[nexti][nextj] < matrix[i][j]) {
                        //符合条件，出度递减
                        outdegrees[nexti][nextj]--;
                        if (outdegrees[nexti][nextj] == 0) {
                            q1.offer(nexti);
                            q2.offer(nextj);
                        }
                    }
                }
            }
        }
        return res;
    }
}
