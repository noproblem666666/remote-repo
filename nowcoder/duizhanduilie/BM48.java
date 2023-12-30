package nowcoder.duizhanduilie;

import java.util.ArrayList;
import java.util.PriorityQueue;

//数据流中的中位数
public class BM48 {
    //插入排序
    //java链表有定点插入功能
    private ArrayList<Integer> val = new ArrayList<Integer>();
    public void Insert(Integer num) {
        if(val.isEmpty())
            //val中没有数据，直接加入
            val.add(num);
            //val中有数据，需要插入排序
        else{
            int i = 0;
            //遍历找到插入点
            for(; i < val.size(); i++){
                if(num <= val.get(i))
                    break;
            }
            //插入相应位置
            val.add(i, num);
        }
    }

    public Double GetMedian() {
        int n = val.size();
        //奇数个数字
        if(n % 2 == 1)
            //类型转换
            return (double)val.get(n / 2);
            //偶数个数字
        else{
            double a = val.get(n / 2);
            double b = val.get(n / 2 - 1);
            return (a + b) / 2;
        }
    }


    //堆排序（比较巧妙的方法）
    //小顶堆，元素数值都比大顶堆大
    private PriorityQueue<Integer> max = new PriorityQueue<>();
    //大顶堆，元素数值较小
    private PriorityQueue<Integer> min = new PriorityQueue<>((o1, o2)->o2.compareTo(o1));
    //维护两个堆，取两个堆顶部即与中位数相关
    public void Insert2(Integer num) {
        //先加入较小部分
        min.offer(num);
        //将较小部分的最大值取出，送入到较大部分
        max.offer(min.poll());
        //平衡两个堆的数量
        if(min.size() < max.size())
            min.offer(max.poll());
    }

    public Double GetMedian2() {
        //奇数个
        if(min.size() > max.size())
            return (double)min.peek();
        else
            //偶数个
            return (double)(min.peek() + max.peek()) / 2;
    }
}
