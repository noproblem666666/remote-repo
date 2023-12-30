package nowcoder.shuangzhizhen;

import java.util.*;

//最长无重复子数组
public class BM92 {
    //滑动窗口
    public int maxLength (int[] arr) {
        // write code here
        //用来判断有没有重复的数字
        HashSet<Integer> hashSet = new HashSet<>();
        int fast=0,slow=0;
        int max = 0;
        while(fast<arr.length){
            //没有重复就放入，有重复就缩小窗口，从set中删除元素
            if(!hashSet.contains(arr[fast])){
                hashSet.add(arr[fast]);
            }else{
                max = Math.max(max,fast-slow);
                while(arr[slow]!=arr[fast]){
                    hashSet.remove(arr[slow]);
                    slow++;
                }
                slow++;
            }
            fast++;
        }
        //防止出现从头到尾没有重复的情况
        if(max == 0){
            max = hashSet.size();
        }
        return max;
    }

    //用hashmap存储元素的下标，这样出现重复时可以更快的找到左边界应该移动的位置
    public int maxLength2(int[] arr) {
        if (arr.length == 0)
            return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; i < arr.length; ++i) {
            if (map.containsKey(arr[i])) {
                j = Math.max(j, map.get(arr[i]) + 1);
            }
            map.put(arr[i], i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    //用队列模拟数组左右边界
    public int maxLength3(int[] arr) {
        //用链表实现队列，队列是先进先出的
        Queue<Integer> queue = new LinkedList<>();
        int res = 0;
        for (int c : arr) {
            while (queue.contains(c)) {
                //如果有重复的，队头出队
                queue.poll();
            }
            //添加到队尾
            queue.add(c);
            res = Math.max(res, queue.size());
        }
        return res;
    }

    //答案的hashset解法
    public int maxLength4(int[] arr) {
        int left = 0, right = 0, max = 0;
        Set<Integer> set = new HashSet<>();
        while (right < arr.length) {
            if (set.contains(arr[right])) {
                set.remove(arr[left++]);
            } else {
                set.add(arr[right++]);
                max = Math.max(max, set.size());
            }
        }
        return max;
    }



}
