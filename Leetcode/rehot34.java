package Leetcode;

//单词搜索(递归回溯加剪枝)
public class rehot34 {
    public boolean exist(char[][] board, String word) {
        //记录那些结点在此次递归中被访问到
        int[][] used = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(isExist(board, i, j, word, 0, used)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isExist(char[][] board, int m, int n, String word, int k, int[][] used) {
        if (m < 0 || m > board.length-1 || n < 0 || n > board[0].length-1||used[m][n]==1) {
            return false;
        }
        if (board[m][n] == word.charAt(k)) {
            if (k == word.length() - 1) {
                return true;
            }
            //used这里设为1，递归出来后马上就要还原，以防忘了
            used[m][n] = 1;
            //查找相邻结点
            boolean res = isExist(board, m + 1, n, word, k + 1, used) || isExist(board, m - 1, n, word, k + 1, used)
                    || isExist(board, m, n + 1, word, k + 1, used) || isExist(board, m, n - 1, word, k + 1, used);
            if(!res){
                //如果匹配失败，别忘了把used还原
                used[m][n]=0;
                return false;
            }else{
                return true;
            }
        }
        //匹配不对就直接返回false，减少时间复杂度
        return false;
    }
}
