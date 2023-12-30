package Leetcode;

import java.util.*;

//前k个高频元素
public class hot83 {
    public int[] topKFrequent(int[] nums, int k) {    //时间复杂度为o(n*log(n))
        HashMap<Integer,Integer> hashMap = new HashMap<>();      //用来统计一个数字出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<>((v1,v2) -> v2[1]-v1[1]);  //大根堆（实现堆排序）（优先队列默认是小根堆）
        for (int num : nums) {
            if(!hashMap.containsKey(num)){
                hashMap.put(num,1);
            }else{
                hashMap.put(num,hashMap.get(num)+1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            int[] count = new int[2];
            count[0] = entry.getKey();
            count[1] = entry.getValue();
            queue.offer(count);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {            //这样可以输出前k个出现次数最多的元素
            res[i] = queue.poll()[0];
        }
        return res;
    }

    public int[] topKFrequent2(int[] nums, int k) {   //时间复杂度为o(n*log(k))   满足题目要求
        //设置一个map集合,key存放数字,value存放出现次数
        Map<Integer,Integer> map = new HashMap<>();
        //统计出现次数
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //建立一个小根堆,用来存放key值,堆内元素按照key对应的value值从小到大排序
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer a,Integer b){
                return map.get(a) - map.get(b);      //这里对整数排序的规则是看他们对应map中的value的大小
            }
        });
        //将map中的数字,插入到小根堆中
        for(Integer key:map.keySet()){    //由于最后只求前k个大的元素，那么我们不停用最小堆保存k个元素，遇到大的就替换就好了
            if(queue.size() < k){
                queue.add(key);
            }else if(map.get(key) > map.get(queue.peek())){
                queue.poll();
                queue.add(key);
            }
        }
        //将大根堆中的k个数字放到数组中
        int [] res = new int[k];
        int index = 0;
        while(!queue.isEmpty()){
            res[index++] = queue.poll();
        }
        return res;
    }

    public int[] topKFrequent3(int[] nums, int k) {    //基于快速排序的思想
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }
        // 获取每个数字出现次数
        List<int[]> values = new ArrayList<int[]>();
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        int[] ret = new int[k];
        qsort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    public void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);

        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            // 使用双指针把不小于基准值的元素放到左边，
            // 小于基准值的元素放到右边
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        Collections.swap(values, start, index);

        if (k <= index - start) {
            // 前 k 大的值在左侧的子数组里
            qsort(values, start, index - 1, ret, retIndex, k);
        } else {
            // 前 k 大的值等于左侧的子数组全部元素
            // 加上右侧子数组中前 k - (index - start + 1) 大的值
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            if (k > index - start + 1) {
                qsort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }



}
