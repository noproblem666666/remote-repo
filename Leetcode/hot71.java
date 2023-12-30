package Leetcode;

import java.util.*;

//会议室||  （区间问题）
public class hot71 {
    public int currentTime(int[][] intervals){
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>(); //第一个数代表时间点，第二个数代表当前时间点是开始还是结束
        //由于在一个时间点可能有多个开始或者结束，所以需要用动态数组来存储
        for (int[] interval : intervals) {
            if(!hashMap.containsKey(interval[0])){
                hashMap.put(interval[0],new LinkedList<>());
                hashMap.get(interval[0]).add(0);
            }else{
                hashMap.get(interval[0]).add(0);
            }
            if(!hashMap.containsKey(interval[1])){
                hashMap.put(interval[1],new LinkedList<>());
                hashMap.get(interval[1]).add(1);
            }else{
                hashMap.get(interval[1]).add(1);
            }
        }
        int current = 0;
        int max = 0;
        List<Integer> list = new LinkedList<>(hashMap.keySet());     //先将set转list才可以排序
        Collections.sort(list);                 //需要将时间结点按照升序排序，从开始到结束开始计算同时使用的会议室
        for (Integer integer : list) {
            Collections.reverse(hashMap.get(integer));    //！！！注意需要排序，同一时刻的终点应该在起点前面，避免多算会议室,所以需要降序排序
            for (Integer integer1 : hashMap.get(integer)) {
                if(integer1==0){
                    current++;
                    max=Math.max(max,current);
                }else{
                    current--;
                }
            }
        }
        return max;
    }
}
