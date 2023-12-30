package Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//合并区间
public class hot26 {
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals,(a,b)->a[0]==b[0]?b[1]-a[1]:a[0]-b[0]);    //二维数组排序，第一维升序，第二维降序

        //不停的用相同起始坐标中最大的区间去比较，只要有第一维小于等于自身第二维的就尝试合并并记录最大值，直到第一维大于自身第二维度，将其记录进答案数组

        int temp0 = intervals[0][0];
        int temp1 = intervals[0][1];
        if(intervals.length==1){
            return new int[][]{{temp0,temp1}};
        }
        HashMap<Integer,Integer> resMap = new HashMap<>();      //暂时存储最大的区间
        for (int i = 1; i < intervals.length; ) {     //这里把i++去掉不然不好控制循环逻辑
            while(i<intervals.length && intervals[i][0]<=temp1){
                temp1 = Math.max(temp1,intervals[i][1]);
                i++;
            }
            resMap.put(temp0,temp1);               //把找到的最大区间放入map
            if(i<intervals.length){                //开始一个新的区间比较
                temp0 = intervals[i][0];
                temp1 = intervals[i][1];
            }
        }
        int[][] res = new int[resMap.size()][2];
        int index = 0;
        for(Map.Entry<Integer,Integer> map : resMap.entrySet()){    //遍历map，把结果放进二维数组
            temp0 = map.getKey();
            temp1 = map.getValue();
            res[index][0] =temp0;
            res[index][1] =temp1;
            index++;
        }

//        for (int[] interval : res) {
//            System.out.println(interval[0] + "," + interval[1]);
//        }
        return res;
    }

    public int[][] merge2(int[][] intervals) {      //更简洁的答案
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval: intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;        //这里是把一个数组放入了
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);       //返回一个数组，去掉冗余的长度
    }



}
