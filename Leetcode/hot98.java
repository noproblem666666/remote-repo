package Leetcode;

import java.util.*;

//任务调度器（桶思想）
public class hot98 {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        for (char task : tasks) {
//            if(!hashMap.containsKey(task)){
//                hashMap.put(task,1);
//            }else{
//                hashMap.put(task,hashMap.get(task)+1);
//            }
            hashMap.put(task, hashMap.getOrDefault(task, 0) + 1);  //直接用一行代码即可完成

        }
        List<Integer> list = new LinkedList<>(hashMap.values());
        list.sort((s1, s2) -> s2 - s1);
        int[] task = new int[list.size()];
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            task[i] = list.get(i);
            sum += task[i];
        }

        int count = 0;                   //todo ： 如何控制这里的循环逻辑是一个难题
        int j = 0;
        while (sum > 0) {
            for (int i = j; i < j + n + 1; i++) {
                if (i < task.length) {
                    while (task[i] == 0) {
                        i++;
                    }
                    if(i < task.length){
                        if (task[i] > 0) {
                            task[i]--;
                            sum--;
                        }
                    }

                }
                count++;
                if (sum == 0) {
                    return count;
                }
                while (task[j] == 0) {
                    j++;
                }
            }

        }
        return count;
    }


    //画图便于理解
    //任务是大写字母，所以可以使用大小为26的数组做哈希表，存放任务和其对应的数量
    //我们需要记录最多任务数量 N，用于构建 N个桶
    //还需要记录最多任务数量的个数（有多个任务数量都最大且相同）count，用于标记最后一个桶的任务数。
    //知道了上述两个变量 N 和 count，则可以计算 time1 = (N - 1) * (n + 1) + count，这是存在空闲时间的情况
    //（当任务种类较少时，冷却时间够用来处理其他任务，冷却时间未被填满）。
    //time2 = tasks.length，这是不存在空闲时间的情况（当任务种类较多时，冷却时间不够用来处理其他任务，冷却时间已被填满）。
    //那么我们最后返回 time1 、time2 中较大值即可，因为存在空闲时间时，time1 大于 time2，不存在空闲时间时，time2 大于 time1
    public int leastInterval2(char[] tasks, int n) {
        int[] arr = new int[26];        // 任务和其数量的哈希表
        int N = 0;      // 最多任务数量
        int count = 0;      // 最多任务数量的个数
        for (char task : tasks) {
            arr[task - 'A']++;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] > N) {
                N = arr[i];
                count = 1;
            } else if (N == arr[i]) {
                count++;
            }
        }
        return Math.max(tasks.length, (N - 1) * (n + 1) + count);
    }
}
