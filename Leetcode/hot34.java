package Leetcode;

//单词搜索（深度优先遍历，回溯，剪枝）
public class hot34 {

    private static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};   //偏移量
    private int rows;                   //行数
    private int cols;                   //列数
    private int len;
    private boolean[][] visited;        //标记数组
    private char[] charArray;           //转化字符串
    private char[][] board;


    public boolean exist(char[][] board, String word) {
        rows = board.length;
        if (rows == 0) {
            return false;
        }
        cols = board[0].length;
        if (cols == 0) {
            return false;
        }
        visited = new boolean[rows][cols];

        len = word.length();
        charArray = word.toCharArray();
        this.board = board;       //同名需要用this区分

        for (int i = 0; i < rows; i++) {           //遍历所有起点，得到答案
            for (int j = 0; j < cols; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int x, int y, int begin) {
        if (begin == len - 1) {
            return board[x][y] == charArray[begin];
        }
        if (board[x][y] == charArray[begin]) {
            visited[x][y] = true;    //表明在这次遍历中，该位置已被遍历，防止重复使用
            for (int[] direction : DIRECTIONS) {     //遍历上下左右四个位置（顺序不重要）
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (inArea(newX, newY) && !visited[newX][newY]) {     //判断是否越界或者已被遍历
                    if (dfs(newX, newY, begin + 1)) {
                        return true;
                    }
                }
            }
            visited[x][y] = false;    //撤出时还原

        }
        return false;    //程序走到这里说明没有找到
    }

    private boolean inArea(int x, int y) {           //判断是否越界的函数
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }


}
