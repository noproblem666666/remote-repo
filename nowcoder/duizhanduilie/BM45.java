package nowcoder.duizhanduilie;

import java.util.ArrayDeque;
import java.util.ArrayList;

//Todo:滑动窗口的最大值（双向队列，存储数组下标）
public class BM45 {
    public ArrayList<Integer> maxInWindows(int [] num, int size) {     //暴力法
        ArrayList<Integer> res = new ArrayList<Integer>();
        //窗口大于数组长度的时候，返回空
        if(size <= num.length && size != 0)
            //数组后面要空出窗口大小，避免越界
            for(int i = 0; i <= num.length - size; i++){
                //寻找每个窗口最大值
                int max = 0;
                for(int j = i; j < i + size; j++){
                    if(num[j] > max)
                        max = num[j];
                }
                res.add(max);
            }
        return res;
    }

    public ArrayList<Integer> maxInWindows2(int [] num, int size) {     //双向队列，存储的是数组下标（利用了数组的随机访问特性，方便找到离开的数）
        ArrayList<Integer> res = new ArrayList<Integer>();
        //窗口大于数组长度的时候，返回空
        if(size <= num.length && size != 0){
            //双向队列
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            //先遍历一个窗口
            for(int i = 0; i < size; i++){
                //去掉比自己先进队列的小于自己的值（因为这些值肯定没用了）
                while(!dq.isEmpty() && num[dq.peekLast()] < num[i])
                    dq.pollLast();
                dq.add(num[i]);   //add默认为addlast
            }
            //遍历后续数组元素
            for(int i = size; i < num.length; i++){
                //取窗口内的最大值
                res.add(num[dq.peekFirst()]);
                while(!dq.isEmpty() && dq.peekFirst() < (i - size + 1))   //注意操作的先后次序，先去掉弹出的数组下标
                    //弹出窗口移走后的值
                    dq.pollFirst();
                //加入新的值前，去掉比自己先进队列的小于自己的值
                while(!dq.isEmpty() && num[dq.peekLast()] < num[i])
                    dq.pollLast();
                dq.add(i);
            }
            res.add(num[dq.pollFirst()]);    //最后再加入一个
        }
        return res;
    }
}
