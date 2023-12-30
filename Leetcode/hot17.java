package Leetcode;

//搜索旋转排序数组
public class hot17 {
    public int search(int[] nums, int target) {  //借鉴二分查找方法
        int len = nums.length;
        if (len == 0) return -1;
        int left = 0, right = len - 1;
        // 1. 首先明白，旋转数组后，从中间划分，一定有一边是有序的。
        // 2. 由于一定有一边是有序的，所以根据有序的两个边界值来判断目标值在有序一边还是无序一边
        // 3. 这题找目标值，遇到目标值即返回
        // 4. 注意：由于有序的一边的边界值可能等于目标值，所以判断目标值是否在有序的那边时应该加个等号
        // (在二分查找某个具体值得时候如果把握不好边界值，可以再每次查找前判断下边界值，也就是while循环里面的两个if注释)
        while (left <= right) {

            // if(nums[left] == target) return left;
            // if(nums[right] == target) return right;

            int mid = left + (right - left) / 2;      //计算中间结点的下标
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < nums[right]) {   //右边有序
                if (target > nums[mid] && target <= nums[right]) {     //目标值在右边
                    left = mid + 1;
                } else {           //目标值在左边
                    right = mid - 1;
                }
            } else {     //左边有序
                if (target < nums[mid] && target >= nums[left]) {     //目标值在左边
                    right = mid - 1;
                } else {           //目标值在左边
                    left = mid + 1;
                }
            }
        }
        return -1;     //说明没有找到
    }
}
