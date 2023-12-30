package Thread;

import java.util.ArrayList;
import java.util.Collections;

public class MyThread2 extends Thread {
    public static ArrayList<Integer> arrayList;

    public MyThread2(ArrayList<Integer> arrayList) {
        MyThread2.arrayList = arrayList;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (MyThread2.class) {
                if(arrayList.size()==0){
                    break;
                }else{
                    Collections.shuffle(arrayList);    //打乱集合方便抽奖
                    int prize = arrayList.remove(0);
                    System.out.println(getName()+"抽到了"+prize+"奖品");
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
