package Leetcode;

import java.util.ArrayList;
import java.util.List;
                                 //回溯算法

/*回溯法要思考的问题：
分支如何产生；
题目需要的解在哪里？是在叶子结点、还是在非叶子结点、还是在从跟结点到叶子结点的路径？
哪些搜索会产生不需要的解的？例如：产生重复是什么原因，如果在浅层就知道这个分支不能产生需要的结果，应该提前剪枝，剪枝的条件是什么，代码怎么写？
* */

public class hot21 {
    List<List<Integer>> resList = new ArrayList<>();
    List<Integer> tempList = new ArrayList<>();
    public static void main(String[] args) {
        int[] nums ={1};
        hot21 s =new hot21();
        System.out.println(s.permute(nums));
    }
    public List<List<Integer>> permute(int[] nums) {
        if(nums==null||nums.length==0){
            return new ArrayList<>();
        }
        int[] index= new int[nums.length];      //用来记录哪个数字已经被用过了
        per(nums,index);
        return  resList;
    }
    public void per(int[] nums,int[] index){         //递归,速度较慢
        for (int i = 0; i < nums.length; i++) {

            if(index[i]==0){
                tempList.add(nums[i]);
                index[i]=1;
                if(tempList.size()== nums.length && !resList.contains(tempList)){
                    List<Integer> copyList=new ArrayList<>(tempList);    //浅拷贝，这样不会让增删影响加入的链表
                    resList.add(copyList);
                }else{
                    per(nums,index);
                }
                index[i]=0;
                tempList.remove(tempList.size()-1);
            }
        }
    }
    public List<List<Integer>> permute2(int[] nums) {
        // 首先是特判
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();

        if (len == 0) {
            return res;
        }

        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth,          //通过保存额外的状态来降低时间复杂度
                     List<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        if (depth == len) {
            // 3、不用拷贝，因为每一层传递下来的 path 变量都是新建的
            res.add(path);
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                // 1、每一次尝试都创建新的变量表示当前的"状态"
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(nums[i]);

                boolean[] newUsed = new boolean[len];
                System.arraycopy(used, 0, newUsed, 0, len);
                newUsed[i] = true;

                dfs(nums, len, depth + 1, newPath, newUsed, res);
                // 2、无需回溯
            }
        }
    }
}
