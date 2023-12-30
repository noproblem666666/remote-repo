package Leetcode;
//最短无序连续子数组
public class hot96 {

    //画一个图就能更好的理解
    public int findUnsortedSubarray(int[] nums) {  //时间复杂度为o(n)，将数组看成三段
        //初始化
        int len = nums.length;
        int min = nums[len-1];
        int max = nums[0];
        int begin = 0, end = -1;
        //遍历
        for(int i = 0; i < len; i++){
            if(nums[i] < max){      //从左到右维持最大值，寻找右边界end
                end = i;            //从左往右遍历，最后一个小于max的为右边界
            }else{
                max = nums[i];
            }

            if(nums[len-i-1] > min){    //从右到左维持最小值，寻找左边界begin
                begin = len-i-1;        //从右往左遍历，最后一个大于min的为左边界
            }else{
                min = nums[len-i-1];
            }
        }
        return end-begin+1;
    }


}
