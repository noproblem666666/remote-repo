package Leetcode;
//在排序数组中查找元素的第一个和最后一个位置
public class rehot18 {
    public int[] searchRange(int[] nums, int target) {
        //执行两次二分查找，分别找到左边界和右边界
        if(nums.length==0){
            return new int[]{-1,-1};
        }
        if(nums.length==1){
            return target==nums[0]?new int[]{0,0}:new int[]{-1,-1};
        }
        int left = binary(nums,target,true);
        int right = binary(nums,target,false);
        return new int[]{left,right};


    }
    public int binary(int[] nums,int target,boolean low){
        int left = 0;
        int right = nums.length-1;
        if(low){
            while(left<=right){
                int mid = (left+right)/2;
                if(nums[mid]==target){
                    //边界情况或者指针相遇情况返回
                    if(mid ==0 ||left==right){
                        return mid;
                    }
                    right=mid-1;
                    //这里已经对指针操作了，不能再对数组操作了
                    continue;
                }
                //防止数组越界
                if(mid<nums.length-1&&nums[mid]!=target&&nums[mid+1]==target){
                    return mid+1;
                }
                if(nums[mid]<target){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }else{
            while(left<=right){
                int mid = (left+right)/2;
                System.out.println(mid);
                if(nums[mid]==target){
                    //边界情况或者指针相遇情况返回
                    if(mid == nums.length-1 || left==right){
                        return mid;
                    }
                    left=mid+1;
                    //这里已经对指针操作了，不能再对数组操作了
                    continue;
                }
                //防止数组越界
                if(mid>0&&nums[mid]!=target&&nums[mid-1]==target){
                    return mid-1;
                }
                if(nums[mid]<target){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return -1;
    }


    //官方答案，写法更简洁
    public int[] searchRange2(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
