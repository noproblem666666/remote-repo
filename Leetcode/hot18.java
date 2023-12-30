package Leetcode;
//在排序数组中查找元素的第一个和最后一个位置
public class hot18 {
    public int[] searchRange(int[] nums, int target) {     //二分查找改版(也可以做两次二分查找找边界（只需要修改下相等时的逻辑）)
        int left = 0,right = nums.length-1;
        while(left<=right){         //先用二分查找找到答案区间
            int mid = (left+right)/2;
            if(nums[mid]>target){
                right = mid-1;
            }else if(nums[mid]<target){
                left = mid+1;
            }else{
                return search(nums,left,right,mid);     //将此时的答案区间传过去
            }
        }
        return new int[]{-1,-1};    //说明没找到
    }

    public int[] search(int[] nums,int left,int right,int now){  //根据传送过来的区间找到答案
        int indexLeft;
        int indexRight;

        int tempL = left;
        int tempR = now;
        while(tempL<=tempR){     //用二分查找左边界，注意边界条件
            int midL = (tempL+tempR)/2;

            if(nums[midL]>=nums[now]){
                tempR = midL-1;
            }else{
                tempL = midL+1;
            }
        }
        indexLeft = tempL;    //最后的答案

        tempL=now;
        tempR=right;
        while(tempL<=tempR){    //用二分查找右边界，注意边界条件
            int midL = (tempL+tempR)/2;

            if(nums[midL]<=nums[now]){
                tempL = midL+1;
            }else{
                tempR = midL-1;
            }
        }
        indexRight = tempR;

        return new int[]{indexLeft,indexRight};
    }
}
