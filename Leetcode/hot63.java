package Leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

//数组中的第k个最大元素
public class hot63 {
    public int findKthLargest(int[] nums, int k) {     //暴力解法，先排序再找坐标，但是时间复杂度不符合
        int len = nums.length;
        Arrays.sort(nums);
        return nums[len - k];
    }


    private final static Random random = new Random(System.currentTimeMillis());

    public int findKthLargest2(int[] nums, int k) {      //减而治之，采用了快速排序的思想
        // 第 1 大的数，下标是 len - 1;
        // 第 2 大的数，下标是 len - 2;
        // ...
        // 第 k 大的数，下标是 len - k;
        int len = nums.length;
        int target = len - k;

        int left = 0;
        int right = len - 1;

        while (true) {
            int pivotIndex = partition(nums, left, right);      //每使用一次快排的过程，都可以确定一个数最终的位置
            if (pivotIndex == target) {
                return nums[pivotIndex];
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                // pivotIndex > target
                right = pivotIndex - 1;
            }
        }
    }

    private int partition(int[] nums, int left, int right) {
        int randomIndex = left + random.nextInt(right - left + 1);     //避免极端化数据（如正序或者倒序），每次随机选定要排序的数
        swap(nums, left, randomIndex);


        // all in nums[left + 1..le) <= pivot;
        // all in nums(ge..right] >= pivot;
        int pivot = nums[left];
        int le = left + 1;
        int ge = right;

        while (true) {
            while (le <= ge && nums[le] < pivot) {
                le++;
            }

            while (le <= ge && nums[ge] > pivot) {
                ge--;
            }

            if (le >= ge) {
                break;
            }
            swap (nums, le, ge);
            le++;
            ge--;
        }

        swap(nums, left, ge);
        return ge;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }


    public int findKthLargest3(int[] nums, int k) {     //优先队列（堆排序）
        int len = nums.length;
        // 使用一个含有 k 个元素的最小堆，PriorityQueue 底层是动态数组，为了防止数组扩容产生消耗，可以先指定数组的长度
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));  //使用java的优先队列构造出一个最小堆，自定义比较器
        // Java 里没有 heapify ，因此我们逐个将前 k 个元素添加到 minHeap 里
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        for (int i = k; i < len; i++) {
            // 看一眼，不拿出，因为有可能没有必要替换
            Integer topElement = minHeap.peek();
            // 只要当前遍历的元素比堆顶元素大，堆顶弹出，遍历的元素进去
            if (nums[i] > topElement) {
                // Java 没有 replace()，所以得先 poll() 出来，然后再放回去
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }

    public int findKthLargest4(int[] nums, int k) {     //优先队列（堆排序）  更简单的一种写法
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }


}
