package nowcoder.duizhanduilie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

//最小的k个数
public class BM46 {
    //使用优先队列实现小根堆
    public ArrayList<Integer> GetLeastNumbers_Solution (int[] input, int k) {
        // write code here
        if(input.length==0||k==0||k>input.length){
            return new ArrayList<>();
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1,o2)->o1-o2);//优先队列默认小根堆,可以传递比较器
        ArrayList<Integer> res = new ArrayList<>();
        for (int i : input) {
            priorityQueue.offer(i);
        }
        for (int i = 0; i < k; i++) {
            res.add(priorityQueue.poll());
        }
        return res;
    }

    //构建一个k大小的大根堆，每次遇到比堆顶小的就弹出堆顶并加入当前数，最后剩下的k个数就是最小的k个数
    public ArrayList<Integer> GetLeastNumbers_Solution2(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        //排除特殊情况
        if(k == 0 || input.length == 0)
            return res;
        //大根堆
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2)->o2.compareTo(o1));
        //构建一个k个大小的堆
        for(int i = 0; i < k; i++)
            q.offer(input[i]);
        for(int i = k; i < input.length; i++){
            //较小元素入堆
            if(q.peek() > input[i]){
                q.poll();
                q.offer(input[i]);
            }
        }
        //堆中元素取出入数组
        for(int i = 0; i < k; i++)
            res.add(q.poll());
        return res;
    }

    //直接使用java内置的排序方法
    public ArrayList<Integer> GetLeastNumbers_Solution3 (int[] input, int k) {
        if(input.length==0||k==0||k>input.length){
            return new ArrayList<>();
        }
        ArrayList<Integer> res = new ArrayList<>();
        Arrays.sort(input);
        for (int i = 0; i < k ; i++) {
            res.add(input[i]);
        }
        return res;
    }
}
