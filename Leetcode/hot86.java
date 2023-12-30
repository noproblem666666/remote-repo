package Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//根据身高重建队列（先排序再插入）
public class hot86 {
    public int[][] reconstructQueue(int[][] people) {  //时间复杂度较差
        HashMap<Integer,Integer> hashMap = new HashMap<>();//用来统计身高为i的人目前队列中有几个身高大于或等于他的
        Arrays.sort(people,(a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);   //对二维数组进行排序，按照第一维进行排序
        List<Integer> list = new LinkedList<>();
        for (int[] person : people) {
            if (!hashMap.containsKey(person[0])) {
                hashMap.put(person[0], 0);
            }
            list.add(person[0]);
        }
        int index = 0;
        List<Integer> resList = new LinkedList<>();
        for (int i = 0; i < people.length; i++) {          //每次对people进行遍历（按照[0]的值从小到大），如果其中[1]的值小于等于hashmap中对应的值，就说明可以插入，符合规则
            for (int i1 = 0; i1 < people.length; i1++) {

            }
            for (Integer a : list) {    //每次插入一个值，都要在身高小于等于他的值对应的[1]上加一
                if(hashMap.get(a)<=index){
                    resList.add(a);
                    hashMap.put(a,hashMap.get(a)+1);
                    break;
                }
                hashMap.put(a,hashMap.get(a)+1);
            }
        }

        return new int[1][1];
    }

    /**
     * 解题思路：先排序再插入
     * 1.排序规则：按照先H高度降序，K个数升序排序
     * 2.遍历排序后的数组，根据K插入到K的位置上
     *
     * 核心思想：高个子先站好位，矮个子插入到K位置上，前面肯定有K个高个子，矮个子再插到前面也满足K的要求
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue2(int[][] people) {   //标准答案
        // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
        // 再一个一个插入。
        // [7,0]
        // [7,0], [7,1]
        // [7,0], [6,1], [7,1]
        // [5,0], [7,0], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        LinkedList<int[]> list = new LinkedList<>();
        for (int[] i : people) {
            list.add(i[1], i);
        }

        return list.toArray(new int[list.size()][2]);
    }


}
