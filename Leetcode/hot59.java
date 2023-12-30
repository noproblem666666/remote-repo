package Leetcode;

//岛屿数量（深度优先遍历）
public class hot59 {
    public int numIslands(char[][] grid) {      //已知grid不为空     未能解决，可能多数
        int height = grid.length;
        int length = grid[0].length;
        int n = 0;
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                if (grid[0][i] == '1') {       //数组是字符类型的
                    n++;
                }
            } else {
                if (grid[0][i] == '1' && grid[0][i - 1] == '0') {
                    n++;
                }
            }
        }
        if (height == 1) {
            return n;
        } else {
            for (int i = 1; i < height; i++) {
                for (int i1 = 0; i1 < length; i1++) {
                    if (i1 == 0) {
                        if (grid[i - 1][i1] == '0' && grid[i][i1] == '1') {     //这样会有多数的，因为有岛屿和后面一位可能有连接成一个岛屿
                            n++;
                        }
                    } else {
                        if (grid[i - 1][i1] == '0' && grid[i][i1 - 1] == '0' && grid[i][i1] == '1') {
                            n++;
                        }
                    }
                }
            }
        }
        return n;
    }

    public int numIslands2(char[][] grid) {      //仍然不能解决，可能在第一行就多数了岛屿
        int height = grid.length;
        int length = grid[0].length;
        int n = 0;
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                if (grid[0][i] == '1') {       //数组是字符类型的
                    n++;
                }
            } else {
                if (grid[0][i] == '1' && grid[0][i - 1] == '0') {
                    n++;
                }
            }
        }
        //System.out.println(n);
        if (height == 1) {
            return n;
        } else {
            for (int i = 1; i < height; i++) {
                int index = 0;
                for (int i1 = 0; i1 < length; i1++) {
                    if (i1 == 0) {
                        if (grid[i][i1] == '1') {       //数组是字符类型的
                            index++;
                            n++;
                        }
                    } else {
                        if (grid[i][i1] == '1' && grid[i][i1 - 1] == '0') {
                            index++;
                            n++;
                        }
                    }
                }
                //System.out.println("第二层筛选前的n");
                //System.out.println(n);

                int num = 0;
                while (index > 0) {            //检查这几个岛屿，与上面有连接的就减去
                    while (num < length && grid[i][num] == '0') {     //是字符类型的
                        num++;
                    }
                    while (num < length && grid[i][num] != '0') {
                        //System.out.println(grid[i - 1][num]);
                        if (grid[i - 1][num] == '1') {
                            n--;      //确定连接为一个岛屿，去掉这个岛屿
                            break;
                        }
                        num++;
                    }
                    while (num < length && grid[i][num] != '0') {
                        num++;
                    }
                    index--;
                }
                //System.out.println("第二层筛选后的n");
                //System.out.println(n);
            }
        }
        return n;
    }

    public int numIslands3(char[][] grid) {     //深度优先遍历
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1'){
                    dfs(grid, i, j);        //与其相连的所有1都为一个岛屿
                    count++;
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid, int i, int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';                  //遍历过后需要标记，以免重复遍历
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }

}
