package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//返回所有子集
public class hot33 {
    List<List<Integer>> resList=new ArrayList<>();
    List<Integer> addList = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {     //回溯递归法  ,时间复杂度不够  ,必须使用带有状态记忆的递归来降低时间复杂度
        resList.add(new ArrayList<>());
        if(nums==null||nums.length==0){
            return resList;
        }
        int n=nums.length;
        while(n>0){
            sets(n,nums);
            n--;
        }
        //System.out.println(resList);
        return resList;
    }
    public void sets(int n,int[] nums){
        if(n==0){
            List<Integer> list = new ArrayList<>(addList);
            Collections.sort(list);      //直接对拷贝的数组排序，防止删除最后一位时不对

            if(!resList.contains(list)){
                resList.add(new ArrayList<>(list));    //浅拷贝
            }
        }else{
            for(int i=0;i< nums.length;i++){
                if(!addList.contains(nums[i])){
                    addList.add(nums[i]);
                    sets(n-1,nums);
                    addList.remove(addList.size()-1); //由于之前对addList排序过，可能删除的不是这次加上的数字哦！！！
                }
            }
        }
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res, new ArrayList<Integer>());
        return res;

    }

    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        //i来记录状态，当进行到第i步时，不用再从0开始，直接继续往后找
        //tmp，res直接记录状态，不用设置全局变量
        res.add(new ArrayList<>(tmp));     //每一步都把集合加进去
        for (int j = i; j < nums.length; j++) {   //这样也不会出现重复的集合
            tmp.add(nums[j]);
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

}
