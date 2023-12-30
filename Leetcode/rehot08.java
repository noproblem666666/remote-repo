package Leetcode;

import java.util.*;

//三数之和
public class rehot08 {
    //使用哈希表，时间复杂度为o（n*n），但是仍然超时（关键在于如何快速去重）
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //负责去重，记住加入前先排序
        HashSet<List<Integer>> set = new HashSet<>();

        HashMap<Integer,List<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i<nums.length;i++) {
            List<Integer> list;
            if(!hashMap.containsKey(nums[i])){
                list = new ArrayList<>();
            }else{
                list = hashMap.get(nums[i]);
            }
            list.add(i);
            hashMap.put(nums[i],list);
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int target = -(nums[i]+nums[j]);
                if(hashMap.containsKey(target)){
                    List<Integer> list = hashMap.get(target);
                    for (Integer integer : list) {
                        if(integer!=i&&integer!=j){
                            List<Integer> temp = new ArrayList<>();
                            temp.add(nums[i]);
                            temp.add(nums[j]);
                            temp.add(nums[integer]);
                            Collections.sort(temp);
                            set.add(temp);
                        }
                    }
                }
            }
        }
        res.addAll(set);
        return res;
    }


    //排序加双指针
    public List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }


}
