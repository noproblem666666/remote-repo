package nowcoder.shuangzhizhen;
//合并区间

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
//Todo:在循环中删除list中的值会让索引错误,新建一个链表用来返回
public class BM89 {
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<>();
        //去除特殊情况
        if(intervals.size() == 0)
            return res;
        //重载比较，按照区间首排序
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval o1, Interval o2){
                if(o1.start != o2.start)
                    return o1.start - o2.start;
                else
                    return o1.end - o2.end;
            }
        });
        //放入第一个区间
        res.add(intervals.get(0));
        int count = 0;
        //遍历后续区间，查看是否与末尾有重叠
        //每次与res里的区间进行比较而不是前一个，防止区间更新
        for(int i = 1; i < intervals.size(); i++){
            Interval o1 = intervals.get(i);
            Interval origin = res.get(count);
            if(o1.start > origin.end){
                res.add(o1);
                count++;
                //区间有重叠，更新结尾
            }else{
                res.remove(count);
                Interval s = new Interval(origin.start, o1.end);
                if(o1.end < origin.end)
                    s.end = origin.end;
                res.add(s);
            }
        }
        return res;
    }


}
