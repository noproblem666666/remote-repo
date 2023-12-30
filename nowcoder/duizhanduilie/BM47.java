package nowcoder.duizhanduilie;

import java.util.Arrays;
import java.util.PriorityQueue;

//寻找第k大（快排，堆）
public class BM47 {
    public int findKth (int[] a, int n, int K) {  //Todo：这种写法有漏洞，不标准，用答案给的标准写法
        // write code here
        //保证答案存在

        int low = 0;
        int Low = 0;
        int high = a.length-1;
        int High = a.length-1;
        while(true){
            int temp = a[low];
            while(low<high){
                while(a[high]>=temp&&low<high){   //注意是大于等于和小于等于，不然会陷入死循环
                    high--;
                }
                a[low] = a[high];
                while(a[low]<=temp&&low<high){
                    low++;
                }
                a[high] =a[low];
            }
            a[Low] = a[low];
            a[low] =temp;
            if(low==n-K){      //第K大的数，数组是按升序排的，应该从右往左
                return a[low];
            }else if(low<n-K){
                low=low+1;
                Low= low+1;
                high=High;
            }else{
                high = high-1;
                High = high-1;
                low = Low;
            }
        }
    }

    public int findKth2(int[] a, int n, int K) {    //内置排序
        Arrays.sort(a);
        return a[n-K];
    }

    public int findKth3(int[] a, int n, int K){     //维护一个k大小的小根堆，遇到大的就加入替换，最后堆顶就是第K大的数
        // 暂存K个较大的值，优先队列默认是自然排序（升序），队头元素（根）是堆内的最小元素，也就是小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(K);
        // 遍历每一个元素，调整小根堆
        for (int num : a) {
            // 对于小根堆来说，只要没满就可以加入（不需要比较）；如果满了，才判断是否需要替换第一个元素
            if (queue.size() < K) {
                queue.add(num);
            } else {
                // 在小根堆内，存储着K个较大的元素，根是这K个中最小的，如果出现比根还要大的元素，说明可以替换根
                if (num > queue.peek()) {
                    queue.poll(); // 高个中挑矮个，矮个淘汰
                    queue.add(num);
                }
            }
        }
        return queue.isEmpty() ? 0 : queue.peek();
    }

    public int findKth4(int[] a, int n, int K) {   //借鉴快排的写法
        return quickSort(a, 0, a.length - 1, K);
    }

    private int quickSort(int[] arr, int left, int right, int k){    //递归
        int p = partition(arr, left, right);
        // 改进后，很特殊的是，p是全局下标，只要p对上topK坐标就可以返回
        if (p == arr.length - k) {
            return arr[p];
        }else if (p < arr.length - k) {
            // 如果基准在左边，这在右边找
            return quickSort(arr, p + 1, right,k);
        }else {
            return quickSort(arr, left, p - 1,k);
        }
    }

    private int partition(int[] arr, int left, int right) {
        // 可优化成随机，或中位数
        int key = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= key) right--;
            arr[left] = arr[right];
            while (left < right && arr[left] <= key) left++;
            arr[right] = arr[left];
        }
        arr[left] = key;
        return left;
    }
}
