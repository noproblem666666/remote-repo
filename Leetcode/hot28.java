package Leetcode;

import java.util.Arrays;

//动态规划的思路和hot27差不多   ,回溯法dfs也能解，时间复杂度太高
public class hot28 {
    public static void main(String[] args) {
        int[][] grid={{1,3,1},{1,5,1},{4,2,1}};
        hot28 s =new hot28();
        System.out.println(s.minPathSum(grid));
    }
    public int minPathSum(int[][] grid) {
        if(grid.length==1&&grid[0].length==1){
            return grid[0][0];
        }
        if(grid.length==1||grid[0].length==1){
            int sum = 0;                                    //定义变量,记录每次相加的结果
            for (int i = 0;i < grid.length ;i++ ) {            //获取每一个一维数组
                for (int j = 0;j < grid[i].length ;j++ ) {    //获取每一个一维数组中的元素
                    sum = sum + grid[i][j];                    //累加
                }
            }
            return sum;
        }
        int[][] path=new int[grid.length][grid[0].length];   //题目给定的grid长宽至少为1    注意两个二维数组之间的对应关系!!!
        for (int i = 1; i < grid.length; i++) {
            path[i][0]=path[i-1][0]+grid[i-1][0];    //path[0][0]初始化默认值为0
        }
        for (int i = 1; i < grid[0].length; i++) {
            path[0][i]=path[0][i-1]+grid[0][i-1];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                path[i][j]=Math.min(path[i-1][j]+grid[i-1][j],path[i][j-1]+grid[i][j-1]);   //由于数组中没有负数，最短路径只能是从左或从上到达的
            }
        }
//        for (int i = 0;i < path.length ;i++ ) {            //获取每一个一维数组
//            for (int j = 0;j < path[i].length ;j++ ) {    //获取每一个一维数组中的元素
//                System.out.println(path[i][j]);
//            }
//        }
        return path[grid.length-1][grid[0].length-1]+grid[grid.length-1][grid[0].length-1]; //别忘了加上最后一步的路径长度
    }

    public int minPathSum2(int[][] grid) {     //直接在给定数组里更新值，节省空间
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(i == 0 && j == 0) continue;
                else if(i == 0)  grid[i][j] = grid[i][j - 1] + grid[i][j];
                else if(j == 0)  grid[i][j] = grid[i - 1][j] + grid[i][j];
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }


}
