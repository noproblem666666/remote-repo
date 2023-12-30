package nowcoder.erfen;
//旋转数组的最小数字
public class BM21 {
    //二分查找
    public int minNumberInRotateArray (int[] nums) {//（二分查找，注意子数组未经翻转的情况）（比较中间值与左边界情况会复杂些）
        // write code here
        if(nums.length==1){
            return nums[0];
        }

        int left = 0;
        int right = nums.length-1;
        if(nums[left]<nums[right]){   //说明根本没有翻转
            return nums[left];
        }
        return findMin(nums,left,right);

    }
    public int findMin (int[] nums,int left,int right){
        if(nums.length==1){
            return nums[0];
        }

        while(left<right){
            if(nums[left]<nums[right]){   //说明根本没有翻转(每次循环都需要检查，因为可能此时的子数组已经是未经翻转的数组)
                return nums[left];
            }
            int mid = left + (right-left)/2;
            if(nums[mid]<nums[left]){  //说明中点在数组的前段，最小值在左面
                right = mid;
            }else if(nums[mid]>nums[left]){    //说明中点在数组的后段，最小值在右面
                left = mid+1;
            }else{    //相等时无法判断在前面还是后面，使用递归

                return Math.min(findMin(nums,left,mid),findMin(nums,mid+1,right));
            }
        }
        return nums[left];
    }
    //左右不对称的原因是： 这是循环前升序排列的数，左边的数小，右边的数大，而且我们要找的是最小值，肯定是偏向左找，所以左右不对称了。
    //为什么比较mid与right而不比较mid与left？ 具体原因前面已经分析过了，简单讲就是因为我们找最小值，
    // 要偏向左找，目标值右边的情况会比较简单，容易区分，所以比较mid与right而不比较mid与left。
    //那么能不能通过比较mid与left来解决问题？ 能，转换思路，不直接找最小值，而是先找最大值，最大值偏右，
    // 可以通过比较mid与left来找到最大值，最大值向右移动一位就是最小值了（需要考虑最大值在最右边的情况，右移一位后对数组长度取余）。

    public int minNumberInRotateArray2(int [] array) {     //答案
        // 特殊情况判断
        if (array.length== 0) {
            return 0;
        }
        // 左右指针i j
        int i = 0, j = array.length - 1;
        // 循环
        while (i < j) {       //注意答案比较的是左边界，这样就不用考虑未经翻转的情况
            // 找到数组的中点 m
            int m = (i + j) / 2;
            // m在左排序数组中，旋转点在 [m+1, j] 中
            if (array[m] > array[j]) i = m + 1;
                // m 在右排序数组中，旋转点在 [i, m]中
            else if (array[m] < array[j]) j = m;
                // 缩小范围继续判断
            else j--;
        }
        // 返回旋转点
        return array[i];
    }
}
