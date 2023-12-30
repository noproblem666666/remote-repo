package nowcoder.tanxin;

import java.util.*;

//主持人调度（二）
public class BM96 {
    //Todo：集合提前分配大小，用hashset去重而不是if判断存在，list直接用set初始化，会节省很多时间
    public int minmumNumberOfHost (int n, int[][] startEnd) {
        // write code here
        if(startEnd.length==0||n==0){
            return 0;
        }

        HashMap<Integer,Integer> start = new HashMap<>(n);
        HashMap<Integer,Integer> end = new HashMap<>(n);

        HashSet<Integer> startSet = new HashSet<>(n);
        HashSet<Integer> endSet = new HashSet<>(n);

        SortedSet<Integer> startSorted = new TreeSet();
        SortedSet<Integer> endSorted = new TreeSet();


        for (int[] ints : startEnd) {
            start.put(ints[0],start.getOrDefault(ints[0],0)+1);
            startSorted.add(ints[0]);
            startSet.add(ints[0]);
//            if(!startList.contains(ints[0])){
//                startList.add(ints[0]);
//            }
            end.put(ints[1],end.getOrDefault(ints[1],0)+1);
            endSorted.add(ints[1]);
            endSet.add(ints[1]);
//            if(!endList.contains(ints[1])){
//                endList.add(ints[1]);
//            }
        }
        ArrayList<Integer> startList = new ArrayList<>(startSet);
        ArrayList<Integer> endList = new ArrayList<>(endSet);
        Collections.sort(startList);
        Collections.sort(endList);
        int max = 0;
        int temp = 0;
        int startIndex = 0;
        int endIndex = 0;
        while(startIndex<startList.size()&&endIndex<endList.size()){
            if(startList.get(startIndex)<endList.get(endIndex)){
                temp += start.get(startList.get(startIndex));
                max = Math.max(max,temp);
                startIndex++;
            }else if(startList.get(startIndex)>endList.get(endIndex)){
                temp -= end.get(endList.get(endIndex));
                endIndex++;
            }else{
                temp += start.get(startList.get(startIndex));
                temp -= end.get(endList.get(endIndex));
                max = Math.max(max,temp);
                startIndex++;
                endIndex++;
            }
        }
        Iterator<Integer> startIterator = startSorted.iterator();
        Iterator<Integer> endIterator = endSorted.iterator();
        //start肯定先遍历完，之后都是减少不需要遍历了
        return max;
    }

    //答案解法，用数组查询排序，即可通过
    public int minmumNumberOfHost2 (int n, int[][] startEnd) {
        int[] start = new int[n];
        int[] end = new int[n];
        //分别得到活动起始时间
        for(int i = 0; i < n; i++){
            start[i] = startEnd[i][0];
            end[i] = startEnd[i][1];
        }
        //单独排序
        Arrays.sort(start, 0, start.length);
        Arrays.sort(end, 0, end.length);
        int res = 0;
        int j = 0;
        for(int i = 0; i < n; i++){
            //新开始的节目大于上一轮结束的时间，主持人不变
            if(start[i] >= end[j])
                j++;
            else
                //主持人增加
                res++;
        }
        return res;
    }

    //优先队列，小顶堆
    public int minmumNumberOfHost3 (int n, int[][] startEnd) {
        int A[] = new int[n];
        int k = 0;
        //按列排序，按开始时间递增排
        Arrays.sort(startEnd, new Comparator<Object>() {
            public int compare(Object o1,Object o2) {
                int[] one = (int[]) o1;
                int[] two = (int[]) o2;
                if(one[0]>two[0]) return 1;
                if(one[0]==two[0]) return 0;
                else return -1;
            }
        });
        //小顶堆
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        //可能有负区间
        q.offer(Integer.MIN_VALUE);
        for(int i = 0; i < n; i++){
            //最近的活动结束时间小于当前活动开始时间
            if(q.peek() <= startEnd[i][0])
                q.poll();
            q.offer(startEnd[i][1]);
        }
        //剩余的活动等于主持人数
        return q.size();
    }
}
