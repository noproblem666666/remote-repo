package Leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

//合并区间
public class rehot26 {
    public int[][] merge(int[][] intervals) {
        if(intervals.length<2){
            return intervals;
        }
        //按照起始区间进行排列
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0]==0?o1[1]-o2[1]:o1[0]-o2[0];
            }
        });
        //使用栈方便进行删除和加入的操作
        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]);
        //每次都与栈顶，也就是上次合并或者加入的区间相比较
        for (int i = 1; i < intervals.length; i++) {
            //区间相交也要合并
            if(stack.peek()[1]>=intervals[i][0]){
                int[] temp = new int[2];
                int[] pop = stack.pop();
                temp[0]= pop[0];
                //结尾区间无法确定谁更大
                temp[1] = Math.max(pop[1],intervals[i][1]);
                stack.push(temp);
            }else{
                //如果不重叠则直接加入
                stack.push(intervals[i]);
            }
        }
        int[][] res = new int[stack.size()][2];
        int i = 0;
        while(!stack.isEmpty()){
            int[] pop = stack.pop();
            res[i][0] = pop[0];
            res[i][1] = pop[1];
            i++;
        }
        return res;
    }

    //不使用栈或者动态数组，直接在数组上进行合并
    public int[][] merge2(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval: intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }


}
