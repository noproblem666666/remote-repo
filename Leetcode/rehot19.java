package Leetcode;

import java.util.*;

//组合总数（回溯加剪枝）
public class rehot19 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //先对其从小到大排序
        Arrays.sort(candidates);
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> temp = new ArrayList<>();
        combination(candidates,target,0,0,temp,res);
        List<List<Integer>> ans = new ArrayList<>(res);
        return ans;
    }
    //Todo:注意，由于我是把删除temp中元素的代码写在了函数返回处，因此不只是不符合时要删除，添加成功和遍历完成返回时都要删除，不能遗漏
    //Todo：排序后使用lastNum记录上一次匹配的最后一个数字，可以避免很多重复的情况，不会再重复的去加更小的数
    public void combination(int[] nums,int target, int sum,int lastNum,List<Integer> temp,Set<List<Integer>> res){
//        System.out.println(sum);
//        System.out.println(temp);
        if(sum==target){
            //Collections.sort(temp);
            res.add(new ArrayList<>(temp));
            //System.out.println(res);
            if(!temp.isEmpty()){
                temp.remove(temp.size()-1);
            }
            return;
        }
        if(sum>target){
            temp.remove(temp.size()-1);
            return;
        }
        //Todo：最简单的方法是将删除temp中的元素放循环里，这样就不容易遗漏了
        for (int i = lastNum; i < nums.length; i++) {
            temp.add(nums[i]);
            combination(nums,target,sum+nums[i],i,temp,res);
        }
        if(!temp.isEmpty()){
            temp.remove(temp.size()-1);
        }
    }
}
