package nowcoder.erfen;
//寻找峰值
public class BM19 {
    public int findPeakElement (int[] nums) {   //nums不为空  分治法思想
        // write code here                     //这种方法是为了找到所有的峰值（时间复杂度不是log（n）），这道题可以更简单些
        if(nums.length==1){
            return 0;
        }
        return findPeak(nums,0,nums.length-1);
    }

    public int findPeak(int[] nums,int left,int right){    //利用递归二分寻找峰值
        if(left==right){
            if(isPeak(nums,left)==1){
                return left;
            }else{
                return -1;
            }
        }
        if(left<right){
            int mid = (left+right)/2;       //int mid = ((right - left) >> 1) + left; //防止直接相加发生溢出
            int res1 = findPeak(nums,left,mid);
            int res2 = findPeak(nums,mid+1,right);
            return Math.max(res1,res2);     //返回最大值，可以排除-1没找到的情况
        }
        return -1;
    }
    /*
                public int isPeak(int[] nums, int index) {    //这种写法存在越界的可能
                if (index == 0 && nums[index + 1] < nums[index]) {
                    return 1;
                }
                if (index == nums.length - 1 && nums[index] < nums[index]) {
                    return 1;
                }
                if (nums[index] > nums[index - 1] && nums[index] > nums[index + 1]) {
                    return 1;
                }
                return 0;
            }
        * */
    public int isPeak(int[] nums,int index){
        if(index == 0){                    //这样写不会越界
            if(nums[index+1]<nums[index])
                return 1;
        }else if(index==nums.length-1){
            if(nums[index-1]<nums[index]){
                return 1;
            }
        }else{
            if(nums[index]>nums[index-1]&&nums[index]>nums[index+1]){
                return 1;
            }
        }
        return 0;
    }

    public int findPeakElement2 (int[] nums) {    //题目更加简便的写法
        int left = 0;
        int right = nums.length - 1;
        //二分法
        while(left < right){
            int mid = (left + right) / 2;   //int mid = ((right - left) >> 1) + left; //防止直接相加发生溢出
            //右边是往下，不一定有坡峰
            if(nums[mid] > nums[mid + 1])
                right = mid;
                //右边是往上，一定能找到波峰
            else
                left = mid + 1;
        }
        //其中一个波峰
        return right;
    }

    public static void main(String[] args) {
        BM19 s = new BM19();
        int[] nums = {0,5,1,2,6,8,2,6,15,1};
        System.out.println(s.findPeakElement(nums));
    }
}
