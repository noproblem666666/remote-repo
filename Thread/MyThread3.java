package Thread;

import java.util.ArrayList;
import java.util.Collections;

public class MyThread3 extends Thread {
    public static ArrayList<Integer> arrayList;

    public MyThread3(ArrayList<Integer> arrayList) {
        MyThread3.arrayList = arrayList;
    }

    @Override
    public void run() {
        ArrayList<Integer> arr = new ArrayList<>();
        while (true) {
            synchronized (MyThread3.class) {
                if(arrayList.size()==0){
                    System.out.println(arr);
                    break;
                }else{
                    Collections.shuffle(arrayList);    //打乱集合方便抽奖
                    int prize = arrayList.remove(0);
                    arr.add(prize);
                }

            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
