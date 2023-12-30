package nowcoder.erfen;
//二分查找（一）
public class BM17 {
    public int search (int[] nums, int target) {      //二分查找，时间复杂度（o（logn）），空间复杂度o（1）
        // write code here
        if(nums==null||nums.length==0){
            return -1;
        }
        int left = 0;                   //设置初始左右边界
        int right = nums.length-1;
        while(left<=right){            //这里要加上等号
            int mid = (left+right)/2;    //找中点
            if(nums[mid]==target){       //根据比较结果返货或者缩小范围
                return mid;
            }else if(nums[mid]>target){
                right=mid-1;
            }else{
                left = mid+1;
            }
        }
        return -1;                   //没有找到
    }
}
