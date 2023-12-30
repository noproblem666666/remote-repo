package nowcoder.haxi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//数组中只出现一次的两个数字
public class BM52 {
    //哈希表法
    public int[] FindNumsAppearOnce (int[] nums) {
        // write code here
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int num : nums) {
            hashMap.put(num,hashMap.getOrDefault(num,0)+1);
        }
        for (Integer integer : hashMap.keySet()) {
            if(hashMap.get(integer)==1){
                res.add(integer);
            }
        }
        return new int[]{res.get(0),res.get(1)};
    }

    //排序法。相同的两个数必定在一起
    public int[] FindNumsAppearOnce2 (int[] nums){
        ArrayList<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        int i = 0;
        for (; i < nums.length-1; i++) {
            if(nums[i]==nums[i+1]){
                i++;   //配合循环体+2
            }else{
                res.add(nums[i]);
            }
        }
        if(i==nums.length-1){  //说明最后一位也是出现一位的数
            res.add(nums[i]);
        }
        return new int[]{res.get(0),res.get(1)};
    }




    //位运算，非常巧妙
    public int[] FindNumsAppearOnce3 (int[] array) {

        // 先将全部数进行异或运算，得出最终结果
        int tmp = 0;
        for(int num: array){
            tmp ^= num;
        }

        // 找到那个可以充当分组去进行与运算的数
        // 从最低位开始找起
        int mask = 1;
        while((tmp&mask) == 0){
            mask <<= 1;
        }

        // 进行分组，分成两组，转换为两组 求出现一次的数字 去求
        int a = 0;
        int b = 0;
        for(int num:array){
            if((num&mask) == 0){
                a ^= num;
            }else{
                b ^= num;
            }
        }
        // 因为题目要求小的数放前面，所以这一做个判断
        if(a > b){
            int c = a;
            a = b;
            b = c;
        }
        return new int[]{a,b};
    }
}
