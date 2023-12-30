package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//找到所有数组中消失的数字（原地修改数组）
public class hot90 {

    //也可以用哈希表
    public List<Integer> findDisappearedNumbers(int[] nums) {  //使用了额外的数组，时间复杂度为o(n),空间复杂度为o(n)
        List<Integer> res = new LinkedList<>();
        int[] index = new int[nums.length+1];    //记录0-n的一个数组
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        for (int i = 0; i < nums.length; i++) {
            index[nums[i]]=0;
        }
        for (int i = 0; i < index.length; i++) {
            if(index[i]!=0){
                res.add(index[i]);
            }
        }
        return res;
    }

    public List<Integer> findDisappearedNumbers2(int[] nums) {     //时间复杂度o(n),空间复杂度o(1)(默认返回的数组不算在额外空间中)
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while(nums[i]!=nums[nums[i]-1]){        //不停的用当前数字与它应该在的位置进行交换，直到双方相同
                //System.out.println(nums[i]);
                int temp = nums[i];
                nums[i] = nums[nums[i]-1];
                nums[temp-1]= temp;    //运行到这里的时候nums[i]已经改变了,所以需要用temp代替，否则会无限循环
                //System.out.println(Arrays.toString(nums));
            }
        }
        for (int i = 0; i < nums.length; i++) {    //交换后，在其位置上不是最终数字的就一定不存在
            if(nums[i]!=i+1){
                res.add(i+1);
            }
        }

        return res;
    }


    public List<Integer> findDisappearedNumbers3(int[] nums) {    //将遍历到的最终位置上的数加n（也可以乘以-1之类的操作，如果已经为负就不需要再乘）
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;    //注意，当我们遍历到某个位置时，其中的数可能已经被增加过，因此需要对n取模来还原出它本来的值
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {       //如果这个数依然小于等于n，就说明它之前没有被加过
                ret.add(i + 1);
            }
        }
        return ret;
    }



    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        hot90 s =new hot90();
        List<Integer> list = s.findDisappearedNumbers2(nums);
    }
}
