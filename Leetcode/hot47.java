package Leetcode;

import java.util.*;

//最长连续序列
public class hot47 {
    //LinkedList<Integer> addList = new LinkedList<>();     //存储每个数的前沿和后续


    Set<Integer> addList = new HashSet<>();    //必须用哈希表，这样查找更快，用list会超出时间限制


 //   LinkedList<Integer> stayList = new LinkedList<>();    //存储每个数，看是否出现
    Map<Integer, Integer> map = new HashMap<>();        //统计每个数的连续点数

    public int longestConsecutive(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        for (int num : nums) {
            if (!addList.contains(num)) {
                addList.add(num);
                map.put(num, 1);
                if (addList.contains(num - 1) && addList.contains(num + 1)) {

                    int num1 = map.get(num - 1);
                    int num2 = map.get(num + 1);
                    int temp = num1+num2;
                    map.put(num, temp + 1);
                    map.put(num-num1,temp+1);
                    map.put(num+num2,temp+1);
//                    for (int i = 0; i < num1; i++) {         //这里把前面连续的数的标记全部更新其实是多余的，只维护边界的标记即可
//                        map.put(num - 1 - i, temp + 1);
//                    }
//                    for (int i = 0; i < num2; i++) {
//                        map.put(num + 1 + i, temp + 1);
//                    }
                } else if (addList.contains(num - 1)) {
                    int temp = map.get(num - 1);
                    map.put(num, temp + 1);
                    map.put(num-temp,temp+1);
//                    for (int i = 0; i < temp; i++) {
//                        map.put(num - 1 - i, temp + 1);
//                    }
                } else if (addList.contains(num + 1)) {
                    int temp = map.get(num + 1);
                    map.put(num, temp + 1);
                    map.put(num+temp,temp+1);
//                    for (int i = 0; i < temp; i++) {
//                        map.put(num + 1 + i, temp + 1);
//                    }
                }
            }
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        int max =Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : entries) {
            int i = entry.getValue();
            if(i>max){
                max = i;
            }
        }
        return max;
    }


        public int longestConsecutive2(int[] nums) {
            Set<Integer> num_set = new HashSet<Integer>();    //hashset便于查找
            for (int num : nums) {
                num_set.add(num);
            }

            int longestStreak = 0;

            for (int num : num_set) {
                if (!num_set.contains(num - 1)) {     //如果之前有连续的，说明这个数不是连续的第一个数，不需要从这里开始统计
                    int currentNum = num;
                    int currentStreak = 1;

                    while (num_set.contains(currentNum + 1)) {
                        currentNum += 1;           //不需要重新取数，下一个数肯定是加一
                        currentStreak += 1;
                    }

                    longestStreak = Math.max(longestStreak, currentStreak);      //每次比较最长序列
                }
            }

            return longestStreak;
        }



}
