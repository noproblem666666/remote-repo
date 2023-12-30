package Leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//搜索二维矩阵||
public class hot70 {
    public boolean searchMatrix(int[][] matrix, int target) {    //时间复杂度太高，超时了
        Queue<int[]> queue = new LinkedList<>();
        int[] num = new int[]{0, 0};
        int i = matrix.length;
        int j = matrix[0].length;
        queue.add(num);
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            if (matrix[temp[0]][temp[1]] == target) {
                return true;
            } else {
                if (temp[0] + 1 < i && matrix[temp[0] + 1][temp[1]] <= target) {  //不停把较小的数放入队列，较大的可以放弃，因为他的右边和下边肯定更大
                    queue.add(new int[]{temp[0] + 1, temp[1]});
                }
                if (temp[1] + 1 < j && matrix[temp[0]][temp[1] + 1] <= target) {
                    queue.add(new int[]{temp[0], temp[1] + 1});
                }
            }
        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        int i = matrix.length;
        int j = matrix[0].length;
        int stopI = 0;
        int stopJ = 0;
        while(stopI<i && matrix[stopI][0]<=target){
            stopI++;
        }
        while(stopJ<j && matrix[0][stopJ]<=target){
            stopJ++;
        }

        Queue<int[]> queue = new LinkedList<>();
        int[] num = new int[]{0, 0};

        queue.add(num);
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            if (matrix[temp[0]][temp[1]] == target) {
                return true;
            } else {
                if (temp[0] + 1 <= stopI && matrix[temp[0] + 1][temp[1]] <= target) {
                    queue.add(new int[]{temp[0] + 1, temp[1]});
                }
                if (temp[1] + 1 <= stopJ && matrix[temp[0]][temp[1] + 1] <= target) {
                    queue.add(new int[]{temp[0], temp[1] + 1});
                }
            }
        }
        return false;
    }


    public boolean searchMatrix22(int[][] matrix, int target) {     //由于每行都是递增有序，所以每行用二分查找最快
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] > target) {                 //第一个比目标大，必不可能在这一行
                break;
            }
            if(matrix[i][matrix[i].length - 1] < target){    //最后一个比目标小，必不可能在这一行
                continue;
            }
            int col = binarySearch(matrix[i], target);
            if (col != -1) {
                return true;
            }
        }
        return false;
    }

    //二分查找
    private int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public boolean searchMatrix33(int[][] matrix, int target) {    //！！！可以从右上角开始查找，比目标当前值小就往左走，比当前值大就往下走
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (target > matrix[row][col]) {
                row++;
            } else if (target < matrix[row][col]) {
                col--;
            } else {
                return true;
            }
        }
        return false;
    }


    public boolean searchMatrix44(int[][] matrix, int target) {     //变形二分法（适用于矩阵的思想）
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        return searchMatrixHelper(matrix, 0, 0, matrix[0].length - 1, matrix.length - 1, matrix[0].length - 1, matrix.length - 1, target);
    }

    private boolean searchMatrixHelper(int[][] matrix, int x1, int y1, int x2, int y2, int xMax, int yMax, int target) {
        //只需要判断左上角坐标即可
        if (x1 > xMax || y1 > yMax) {
            return false;
        }

        //x 轴代表的是列，y 轴代表的是行
        if(x1 == x2 && y1 == y2){
            return matrix[y1][x1] == target;
        }
        int m1 = (x1 + x2) >>> 1;
        int m2 = (y1 + y2) >>> 1;
        if (matrix[m2][m1] == target) {
            return true;
        }
        if (matrix[m2][m1] < target) {
            // 右上矩阵
            return searchMatrixHelper(matrix, m1 + 1, y1, x2, m2, x2, y2, target) ||
                    // 左下矩阵
                    searchMatrixHelper(matrix, x1, m2 + 1, m1, y2, x2, y2, target) ||
                    // 右下矩阵
                    searchMatrixHelper(matrix, m1 + 1, m2 + 1, x2, y2, x2, y2, target);

        } else {
            // 右上矩阵
            return searchMatrixHelper(matrix, m1 + 1, y1, x2, m2, x2, y2, target) ||
                    // 左下矩阵
                    searchMatrixHelper(matrix, x1, m2 + 1, m1, y2, x2, y2, target) ||
                    // 左上矩阵
                    searchMatrixHelper(matrix, x1, y1, m1, m2, x2, y2, target);
        }
    }




}
