package nowcoder.moni;
//顺时针旋转矩阵
public class BM99 {

    //使用辅助数组，找出坐标变换规律
    public int[][] rotateMatrix (int[][] mat, int n) {
        // write code here
        if(n==0){
            return mat;
        }
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[j][n-1-i] = mat[i][j];
            }
        }
        return res;
    }

    //不使用辅助数组，先进行对角线交换，再对每一行反转，就是顺时针旋转90度
    public int[][] rotateMatrix2(int[][] mat, int n) {
        int length = mat.length;
        //矩阵转置
        for(int i = 0; i < length; ++i){
            for(int j = 0; j < i; ++j){
                //交换上三角与下三角对应的元素
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
        //每行翻转
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length/2; j++){
                int temp = mat[i][j];
                mat[i][j] = mat[i][length - j - 1];
                mat[i][length - j - 1] = temp;
            }
        }
        return mat;
    }
}
