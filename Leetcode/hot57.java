package Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//多数元素
public class hot57 {             //nums不为空
    public int majorityElement(int[] nums) {   //Boyer-Moore 投票算法
        if (nums.length == 1) {
            return nums[0];
        }
        int temp = nums[0];
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (temp == nums[i]) {
                index++;
            } else {
                index--;
                if (index == 0) {
                    temp = nums[i];
                    index = 1;
                }
            }
        }
        return temp;
    }

    private Map<Integer, Integer> countNums(int[] nums) {       //哈希表统计
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }

    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }

    public int majorityElement3(int[] nums) {    //排序，众数一定会出现在中间位置
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


    private int randRange(Random rand, int min, int max) {    //随机挑选，直到选到众数
        return rand.nextInt(max - min) + min;
    }

    private int countOccurences(int[] nums, int num) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    public int majorityElement4(int[] nums) {
        Random rand = new Random();

        int majorityCount = nums.length / 2;

        while (true) {
            int candidate = nums[randRange(rand, 0, nums.length)];
            if (countOccurences(nums, candidate) > majorityCount) {
                return candidate;
            }
        }
    }


    private int countInRange(int[] nums, int num, int lo, int hi) {     //分治法，使用了递归算法
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    public int majorityElement5(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }




}
