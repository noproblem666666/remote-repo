package Leetcode;
//移动零
public class hot73 {
    public void moveZeroes(int[] nums) {         //自己写的二次遍历
        int numZero = 0;//数出现了多少个零

        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==0){                    //遇到0计数
                numZero++;
            }else{
                nums[i-numZero]=nums[i];       //遇到其他数就向前移动目前0的个数的位置
            }
        }
        for(int i = nums.length-numZero;i<nums.length;i++){     //在数组最后的0的个数的位置补上0
            nums[i] = 0;
        }
    }

    public void moveZeroes2(int[] nums) {    //一次遍历的方法，借鉴了快速排序的思想
        if(nums==null) {
            return;
        }
        //两个指针i和j
        int j = 0;
        for(int i=0;i<nums.length;i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if(nums[i]!=0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;     //先换，j再++
            }
        }
    }


}
