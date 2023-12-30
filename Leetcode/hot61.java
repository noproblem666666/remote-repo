package Leetcode;

import java.util.*;

//课程表(拓扑排序)
public class hot61 {
    HashMap<Integer,Integer> hashMap = new HashMap<>();
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean index =true;

        for (int[] prerequisite : prerequisites) {
            hashMap.put(prerequisite[0],prerequisite[1]);        //hashmap会覆盖相同的key   ！！！
        }

        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            HashSet<Integer> hashSet = new HashSet<>();
            int i = entry.getKey();
            int j =entry.getValue();
            hashSet.add(i);
            hashSet.add(j);
            while(hashMap.containsKey(j)){
                j = hashMap.get(j);
                if(hashSet.contains(j)){
                    return false;
                }else{
                    hashSet.add(hashMap.get(j));
                }
            }
        }
        return true;
    }
    public boolean lookFor(int compare , int key){
        if(hashMap.containsKey(key)){
            int i = hashMap.get(key);
            if(i==compare){
                return true;
            }else{
                return lookFor(compare,i);
            }
        }else{
            return false;
        }
    }


    public boolean canFinish2(int numCourses, int[][] prerequisites) {   //答案，用了拓扑排序（广度优先遍历）
        // 1.课号和对应的入度
        // 节点的入度: 使用数组保存每个节点的入度,
        Map<Integer, Integer> inDegree = new HashMap<>();
        // 将所有的课程先放入
        for (int i = 0; i < numCourses; i++) {
            inDegree.put(i, 0);
        }
        // 2.依赖关系, 依赖当前课程的后序课程
        Map<Integer, List<Integer>> adj = new HashMap<>();     //因为相同的key会覆盖，所以value值用list存储



        // 初始化入度和依赖关系
        for (int[] relate : prerequisites) {
            // (3,0), 想学3号课程要先完成0号课程, 更新3号课程的入度和0号课程的依赖(邻接表)
            int cur = relate[1];
            int next = relate[0];
            // 1.更新入度
            inDegree.put(next, inDegree.get(next) + 1);
            // 2.当前节点的邻接表
            if (!adj.containsKey(cur)) {
                adj.put(cur, new ArrayList<>());        //在存入之前要先初始化链表数据
            }
            adj.get(cur).add(next);         //将其存入对应的链表
        }

        // 3.BFS, 将入度为0的课程放入队列, 队列中的课程就是没有先修, 可以学的课程
        Queue<Integer> q = new LinkedList<>();
        for (int key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                q.offer(key);
            }
        }
        // 取出一个节点, 对应学习这门课程.
        // 遍历当前邻接表, 更新其入度; 更新之后查看入度, 如果为0, 加入到队列
        while (!q.isEmpty()) {
            int cur = q.poll();
            // 遍历当前课程的邻接表, 更新后继节点的入度
            if (!adj.containsKey(cur)) {
                continue;
            }
            List<Integer> successorList = adj.get(cur);

            for (int k : successorList) {
                inDegree.put(k, inDegree.get(k) - 1);        //将其对应的入度全部去掉
                if (inDegree.get(k) == 0) {                  //如果入度为0则放入继续
                    q.offer(k);
                }
            }
        }

        // 4.遍历入队, 如果还有课程的入度不为0, 返回fasle
        for (int key : inDegree.keySet()) {
            if (inDegree.get(key) != 0) {
                return false;
            }
        }
        return true;

    }

    public boolean canFinish3(int numCourses, int[][] prerequisites) {      //拓扑排序（深度优先遍历）
        // 和我的思路相同，但是需要学习他的存储数据结构和标记思路以及递归套路
        List<List<Integer>> adjacency = new ArrayList<>();        //双重链表，用来存储每个课程的依赖
        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        int[] flags = new int[numCourses];                        //用来标记每个课程
        for(int[] cp : prerequisites)
            adjacency.get(cp[1]).add(cp[0]);
        for(int i = 0; i < numCourses; i++)
            if(!dfs(adjacency, flags, i)) return false;
        return true;
    }
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        if(flags[i] == 1) return false;                    //在本次被遍历到了，说明有环，直接返回false
        if(flags[i] == -1) return true;                    //说明被其他结点遍历了
        flags[i] = 1;
        for(Integer j : adjacency.get(i))
            if(!dfs(adjacency, flags, j)) return false;
        flags[i] = -1;
        return true;
    }


}
