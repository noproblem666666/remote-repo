package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.PriorityQueue;

public class hot69 {
    public int[] maxSlidingWindow(int[] nums, int k) {    //超出时间限制
        if (nums.length <= k) {
            int max = Integer.MIN_VALUE;
            for (int num : nums) {
                if (num > max) {
                    max = num;
                }
            }

            return new int[]{max};
        }
        HashMap<Integer,Integer> hashMap =new HashMap<>();    //用来统计窗口中的数字以及出现的次数
        int[] res = new int[nums.length-k+1];
        int max = Integer.MIN_VALUE;
        for(int i = 0;i < k;i++){
            max = Math.max(max,nums[i]);
            if(!hashMap.containsKey(nums[i])){
                hashMap.put(nums[i],1);
            }else{
                hashMap.put(nums[i],hashMap.get(nums[i])+1);
            }
        }
        res[0] = max;
        for(int i = k ;i<nums.length;i++){
            if(!hashMap.containsKey(nums[i])){
                hashMap.put(nums[i],1);
            }else{
                hashMap.put(nums[i],hashMap.get(nums[i])+1);
            }
            hashMap.put(nums[i-k],hashMap.get(nums[i-k])-1);
            if(hashMap.get(nums[i-k])==0){
                hashMap.remove(nums[i-k]);
            }
            if(!hashMap.containsKey(max)){
                max = Integer.MIN_VALUE;
                for (Integer integer : hashMap.keySet()) {
                    max = Math.max(max,integer);
                }
            }
            if(max<nums[i]){
                max = nums[i];
            }
            res[i-k+1]=max;
        }
        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {            //优先队列（大根堆）
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b)->b[1]-a[1]);     //存储的是数组，因为还需要保存数的下标
        int n = nums.length, m = n - k + 1, idx = 0;
        int[] ans = new int[m];
        for (int i = 0; i < n; i++) {
            q.add(new int[]{i, nums[i]});
            if (i >= k - 1) {
                while (q.peek()[0] <= i - k) q.poll();      //如果堆顶的元素下标以及移出窗口，那么就将其移除
                ans[idx++] = q.peek()[1];                   //将目前最大的数放入
            }
        }
        return ans;
    }

    public int[] maxSlidingWindow3(int[] nums, int k) {      //单调队列（双端队列）
        Deque<Integer> d = new ArrayDeque<>();
        int n = nums.length, m = n - k + 1;
        int[] ans = new int[m];
        for (int i = 0; i < n; i++) {
            //若同一时刻存在两个数 nums[j]nums[j]nums[j] 和
            // nums[i]nums[i]nums[i]（j<ij < ij<i）所在一个窗口内，
            // 下标更大的数会被更晚移出窗口，此时如果有 nums[j]<=nums[i]nums[j] <= nums[i]nums[j]<=nums[i]
            // 的话，可以完全确定 nums[j]nums[j]nums[j] 将不会成为后续任何一个窗口的最大值，
            // 此时可以将必然不会是答案的 nums[j]nums[j]nums[j] 从候选中进行移除。
            while (!d.isEmpty() && nums[d.peekLast()] <= nums[i]) d.pollLast();
            d.addLast(i);
            if (i >= k - 1) {
                while (!d.isEmpty() && d.peekFirst() <= i - k) d.pollFirst();
                ans[i - k + 1] = nums[d.peekFirst()];
            }
        }
        return ans;
    }


}
