package Leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//全排列（注意和rehot 19区分）
public class rehot21 {
    public List<List<Integer>> permute(int[] nums) {
        if(nums.length==0){
            return new ArrayList<>();
        }
        List<Integer> temp = new ArrayList<>();
        //用set去重
        Set<List<Integer>> set = new HashSet<>();
        per(nums,temp,set);
        return new ArrayList<>(set);
    }
    public void per(int[] nums,List<Integer> temp,Set<List<Integer>> set){
        if(temp.size()==nums.length){
            set.add(new ArrayList<>(temp));
            return;
        }
        for (int num : nums) {
            //Todo：没有重复数字的话可以这样检查，但是如果有重复数字，就可以使用used数组，记录数组中每个数是否使用
            if(!temp.contains(num)){
                temp.add(num);
                per(nums,temp,set);
                temp.remove(temp.size()-1);
            }
        }
    }
}
