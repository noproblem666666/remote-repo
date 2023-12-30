package Leetcode;

import java.util.*;

public class hot19 {
    public static void main(String[] args) {
        int[] candidates ={2,3,6,7};
        int target = 7;
        hot19 s =new hot19();

        System.out.println(s.combinationSum(candidates,target));
    }
    List<List<Integer>> resList =new ArrayList<>();
    List<Integer> list =new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);//从小到大排序
        int index = 0;
        for (int i = 0; i < candidates.length; i++) {
            if(candidates[i]>target){
                index=i-1;
                break;
            }
        }
        if(index<0){
            return new ArrayList<>();
        }
        combination(candidates,target,0);
        return resList;
    }
    //自己写的回溯递归算法，速度和占用空间都较差（重点在于减少重复）
    public void  combination(int[] candidates,int left,int begin){ //已经改进，重点在于用begin记录当前进行到的阶段，使得每次找下一结点时至少找大于等于当前结点的
        for (int i=begin;i<candidates.length;i++) {
            if (candidates[i] > left) {
                break;
            } else if (candidates[i]  == left) {
                //System.out.println(candidate+"添加了！");
                list.add(candidates[i] );                    //List<Integer> addList =list;    没啥用！！！
                List<Integer> addList = new ArrayList<>(list);   //浅拷贝，add和remove不影响，但是修改其中的数据还是会影响哦
                //System.out.println("已存入");
                Collections.sort(addList);
                if(!resList.contains(addList)){   //去掉重复的结果
                    resList.add(addList);            //必须存入一个复制的链表，不然对list操作也会影响resList中的数据,直接赋值法没用，还是同一个哦！！！
                }

                //System.out.println(resList);
                list.remove(list.size() - 1);    //避免脏数据
            } else {
                //System.out.println(candidate+"添加了！");
                list.add(candidates[i] );
                combination(candidates, left - candidates[i],i );
                list.remove(list.size() - 1);
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {   //回溯剪枝
        //定义一个返回结果的集合
        List<List<Integer>> res =new ArrayList<>();
        //定义一个存储树路径上的节点值
        Deque<Integer> path = new ArrayDeque<>();
        //定义一个表示数组的长度的变量
        int len = candidates.length;
        //深度搜索
        dfs(candidates, len,0, target, path,res);
        //返回结果
        return res;
    }
    public void dfs(int[] candidates, int len,int begin,int target, Deque<Integer> path, List<List<Integer>> res ){ //注意begin的作用
        //如果此时目标元素经过几次深度递归，减值，
        //就说明，数组中不存在能相加等于目标数组的元素集合
        if(target < 0){
            return;
        }
        //如果刚好减到0，说明此时路径上的元素，相加等于目标元素。
        //此时路径上的元素就符合条件，将他们加入返回结果中，并退出此次递归
        if(target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        //遍历元素，这里的i 必须要跟递归层数保持一致，要不要剪枝时，会照成重复元素
        for(int i = begin; i<len; i++){
            //将路径上的元素加入结果集合中
            path.add(candidates[i]);
            //在进行一轮剪枝到根节点的时候，下一轮的搜索的启点就不能包括上一次搜索的下标了
            //此时在拼接重复元素的时候，起点只能是大于等于当前元素的下标。
            dfs(candidates, len,i, target-candidates[i],path,res);
            //将元素进行删除，也叫剪枝，
            //这里必须从队列的尾部开始删除，这样才能达到从底层逐层删除
            path.removeLast();
        }
    }
}
