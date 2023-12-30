package Thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    ArrayList<Integer> arrayList;

    public MyCallable(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }


    @Override
    public Integer call() throws Exception {
        ArrayList<Integer> arr = new ArrayList<>();
        while (true) {
            synchronized (MyCallable.class) {

                if (arrayList.size() == 0) {    //用静态会报错
                    System.out.println(Thread.currentThread().getName() + arr);
                    break;
                } else {
                    Collections.shuffle(arrayList);    //打乱集合方便抽奖
                    int prize = arrayList.remove(0);
                    arr.add(prize);
                }

            }

            Thread.sleep(10);


        }
        if (arr.size() == 0) {
            return null;
        } else {
            return Collections.max(arr);
        }

    }
}
