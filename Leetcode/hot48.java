package Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//只出现一次的数字
public class hot48 {
    public int singleNumber(int[] nums) {
        //nums的长度肯定为奇数
        if(nums.length==1){
            return nums[0];
        }
        Arrays.sort(nums);        //先排序，（默认的是归并排序,时间复杂度为o(n*log(n))）
        int m = 0;
        int n = 1;
        int num = (nums.length-1)/2;
        while(num>0){
            if(nums[m]==nums[n]){
                m+=2;
                n+=2;
            }else{
                return nums[m];   //单个的数只会出现在奇数个位置
            }
            num--;
        }
        return nums[m];    //如果之前都没找到，那么就在最后一个
    }


        public int singleNumber2(int[] nums) {        //利用哈希表，空间复杂都为o(n)
            Map<Integer, Integer> map = new HashMap<>();
            for (Integer i : nums) {
                Integer count = map.get(i);
                count = count == null ? 1 : ++count;
                map.put(i, count);
            }
            for (Integer i : map.keySet()) {
                Integer count = map.get(i);
                if (count == 1) {
                    return i;
                }
            }
            return -1; // can't find it.
        }

    public int singleNumber3(int[] nums) {        //异或，最优解，因为答案只出现了一次啊
        int ans = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                ans = ans ^ nums[i];
            }
        }
        return ans;
    }





}
