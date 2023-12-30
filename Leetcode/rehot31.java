package Leetcode;
//颜色分类（双指针或三指针）
public class rehot31 {

    //分两次交换，第一次都把2交换到最后面，第二次都把0交换到最前面，没有用到i遍历数组
    public void sortColors(int[] nums) {
        if(nums.length==0||nums.length==1){
            return;
        }
        int left = 0;
        int right = nums.length-1;
        while(left<right){
            while(nums[left]!=2&&left<right){
                left++;
            }
            while(nums[right]==2&&left<right){
                right--;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
        left = 0;
        right = nums.length-1;
        while(left<right){
            while(nums[left]==0&&left<right){
                left++;
            }
            while(nums[right]!=0&&left<right){
                right--;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }


    //答案双指针，只遍历一次，一个指针负责记录0的位置，一个指针负责记录1的位置
    public void sortColors2(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                //记录1位置的指针速度较快的情况，还需要把换出来的1换回去
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                ++p0;
                ++p1;
            }
        }
    }

    //双指针法，只遍历一次，一个记录0位置，一个记录2位置
    public void sortColors3(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; ++i) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }



}
