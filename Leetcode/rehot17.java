package Leetcode;

//搜索旋转排序数组(二分查找)
public class rehot17 {
    //二分查找时间复杂度（o（logn））
    public int search(int[] nums, int target) {
        if(nums.length==0){
            return -1;
        }
        if(nums.length==1){
            return target==nums[0]?0:-1;
        }
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = (left+right)/2;
            System.out.println(mid);
            if(nums[mid]==target){
                return mid;
            }
            if(nums[mid]<nums[right]){    //说明右边有序
                if(target>nums[mid]&&target<=nums[right]){  //因为是翻转过的数组，所以只有同时满足这两者才能说明是在这一边，不然就有可能在另一边
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }else{   //左边有序
                if(target<nums[mid]&&target>=nums[left]){//因为是翻转过的数组，所以只有同时满足这两者才能说明是在这一边，不然就有可能在另一边
                    right = mid -1;
                }else{
                    left = mid+1;
                }
            }
        }
        return -1;
    }

}
