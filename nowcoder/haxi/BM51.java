package nowcoder.haxi;

import java.util.Arrays;
import java.util.HashMap;

//数组中出现次数超过一半的数字
public class BM51 {

    //哈希表存储出现次数
    public int MoreThanHalfNum_Solution (int[] numbers) {
        // write code here
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int number : numbers) {
            hashMap.put(number,hashMap.getOrDefault(number,0)+1);
            if (hashMap.get(number)>numbers.length/2){  //每次放入检查，避免再遍历一次
                return number;
            }
        }
        return 0;    //数据保证有解
    }

    //排序法，众数的出现次数超过一半，那么排序后的中间数一定是它
    public int MoreThanHalfNum_Solution2 (int[] numbers){
        Arrays.sort(numbers);
        return numbers[numbers.length/2];   //数据保证有解
    }

    //候选法，一边遍历一边计数，为0时替换
    public int MoreThanHalfNum_Solution3 (int[] numbers){
        int count = 1;
        int temp = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if(numbers[i]==temp){
                count++;
            }else{
                count--;
            }
            if(count==0){
                temp=numbers[i];
                count=1;    //重置计数为1
            }
        }
        return temp; //数据保证有解
    }
}
