package nowcoder.haxi;

import java.util.ArrayList;
import java.util.HashMap;

//两数之和
public class BM50 {
    public int[] twoSum (int[] numbers, int target) {
        // write code here
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();    //方便找到每一个数对应的下标（防止有相同的数）
        for (int i = 0; i < numbers.length; i++) {
            if(!hashMap.containsKey(numbers[i])){
                hashMap.put(numbers[i],new ArrayList<>());
            }
            hashMap.get(numbers[i]).add(i+1);
        }
        for (int number : numbers) {

            //如果target = target-number 并且此时该数只有一个坐标，那么就不存在两个坐标使得和相同
            if(hashMap.containsKey(target-number)&&(target != 2*number || hashMap.get(number).size() != 1)){
                return new int[]{hashMap.get(number).get(0),hashMap.get(target-number).get(hashMap.get(target-number).size()-1)};    //按下标升序排列
            }
        }
        return new int[0]; //没找到
    }

    //很巧妙的方法，在放进哈希表的时候就检查是否有合适的坐标对，防止有相同的数覆盖哈希值
    public int[] twoSum2 (int[] numbers, int target) {
        // write code here
        HashMap<Integer, Integer> map = new HashMap<>();
        //遍历数组
        for (int i = 0; i < numbers.length; i++) {
            //将不包含target - numbers[i]，装入map中，包含的话直接返回下标
            if(map.containsKey(target - numbers[i]))
                return new int[]{map.get(target - numbers[i])+1, i+1};
            else
                map.put(numbers[i], i);
        }
        throw new IllegalArgumentException("No solution");
    }
}
