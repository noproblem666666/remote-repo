package nowcoder.erfen;
//二维数组中的查找
public class BM18 {
    public boolean Find (int target, int[][] array) {
        // write code here
        if(array==null||array.length==0||array[0].length==0){
            return false;
        }
        int len = array.length;
        int width = array[0].length;
        if(target<array[0][0]||target>array[len-1][width-1]){      //提前排除不可能的情况
            return false;
        }
        for (int[] ints : array) {                  //对每一行进行二分查找
            if (target < ints[0] || target > ints[width - 1]) {
                continue;
            }
            int left = 0;
            int right = width;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (ints[mid] == target) {
                    return true;
                } else if (ints[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return false;
    }

    //很巧妙的办法
    public boolean Find2(int target, int [][] array) {    //利用二维矩阵的特性，从左下角开始查询
        //优先判断特殊
        if(array.length == 0)
            return false;
        int n = array.length;
        if(array[0].length == 0)
            return false;
        int m = array[0].length;
        //从最左下角的元素开始往左或往上
        for(int i = n - 1, j = 0; i >= 0 && j < m; ){
            //元素较大，往上走
            if(array[i][j] > target)
                i--;
                //元素较小，往右走
            else if(array[i][j] < target)
                j++;
            else
                return true;
        }
        return false;
    }

    //二维数组中的二分查找较为复杂
}
