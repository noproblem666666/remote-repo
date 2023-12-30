package Leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//子集
public class rehot33 {
    //有个用例无法通过，奇怪
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        HashSet<Integer> set = new HashSet<>();
        HashSet<List<Integer>> resSet = new HashSet<>();
        resSet.add(new ArrayList<>());
        sub(nums,resSet,set,0);
        return new ArrayList<>(resSet);
    }

    public void sub(int[] nums,HashSet<List<Integer>> resSet,HashSet<Integer> set ,int n){
        //传递n进行剪枝
        for (int i = n; i < nums.length; i++) {
            set.add(nums[i]);
            resSet.add(new ArrayList<>(set));
            sub(nums,resSet,set,n+1);
            set.remove(nums[i]);
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
        //拷贝
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {   //这样也不会出现重复的集合
            tmp.add(nums[j]);
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }


    //迭代枚举
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets3(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }

}
